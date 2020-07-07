function loadMonthMileageTop() {
    var web_socket = new WebSocket('ws://localhost:8080/vehicle/visualize/monthMileageTop')

    web_socket.onerror = function (event) {
        onError(event)
    };
    web_socket.onopen = function (event) {
        onOpen(event)
    };
    web_socket.onmessage = function (event) {//event中包含的是服务端向客户端发送过来的数据
        setMonthMileageTop(JSON.parse(event.data))
    };

    function onOpen(event) {
        /**webSocket open之后，发送消息给message 服务并保持长链接的消息输出**/
        web_socket.send("all_vehicle");
    }

    function onError(event) {
        alert(event.data);
    }

    function setMonthMileageTop(jsonData) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('id_month_mileage_top'));
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b}: <br/>  {c} ({d}%)"
            },

            toolbox: {
                show : false,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {
                        show: true,
                        type: ['pie', 'funnel']
                    },
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            series : [
                {
                    name:'排名',
                    type:'pie',
                    color: ['#33b565', '#20cc98', '#20b9cf', '#2089cf', '#205bcf'],
                    radius : [20, 70],
                    center : ['50%', '50%'],
                    roseType : 'area',
                    /*
                        [
                        {value:70, name:'NO.4'},
                        {value:90, name:'NO.3'},
                        {value:110, name:'NO.2'},
                        {value:150, name:'NO.1'},
                        {value:40, name:'NO.5'}
                        ]
                     */
                    data: jsonData
                }
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }
}