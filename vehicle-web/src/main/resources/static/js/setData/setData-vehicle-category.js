function loadVehicleCategory() {
    var web_socket = new WebSocket('ws://localhost:8080/vehicle/visualize/vehicleCategory')

    web_socket.onerror = function (event) {
        onError(event)
    };
    web_socket.onopen = function (event) {
        onOpen(event)
    };
    web_socket.onmessage = function (event) {//event中包含的是服务端向客户端发送过来的数据
        setVehicleCategory(JSON.parse(event.data))
    };

    function onOpen(event) {
        /**webSocket open之后，发送消息给message 服务并保持长链接的消息输出**/
        web_socket.send("vehicleCategory");
    };

    function onError(event) {
        alert(event.data);
    };
    function setVehicleCategory(dataArr) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('id_vehicle_category'));
        //['车型一', '车型二', '车型三', '车型四']
        var vehicleCategory = dataArr.vCategory
        var vCategoryData = dataArr.vCategoryData
        option = {
            backgroundColor: 'rgba(0,0,0,0)',
            tooltip: {
                trigger: 'item',
                formatter: "{b}  <br/>{c}辆"
            },
            legend: {
                x: 'center',
                y: '2%',
                data: vehicleCategory,
                icon: 'circle',
                textStyle: {
                    color: '#fff',
                }
            },
            calculable: true,
            series: [{
                name: '车型',
                type: 'pie',
                //起始角度，支持范围[0, 360]
                startAngle: 0,
                //饼图的半径，数组的第一项是内半径，第二项是外半径
                radius: [51, 80],
                //支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度
                center: ['50%', '20%'],
                //是否展示成南丁格尔图，通过半径区分数据大小。可选择两种模式：
                // 'radius' 面积展现数据的百分比，半径展现数据的大小。
                //  'area' 所有扇区面积相同，仅通过半径展现数据大小
                roseType: 'area',
                //是否启用防止标签重叠策略，默认开启，圆环图这个例子中需要强制所有标签放在中心位置，可以将该值设为 false。
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: true,
                        formatter: '{c}辆'
                    },
                    emphasis: {
                        show: true
                    }
                },
                labelLine: {
                    normal: {
                        show: true,
                        length2: 1,
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: vCategoryData
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }
}

