function loadChargingTimePeak() {
    var web_socket = new WebSocket('ws://localhost:8080/vehicle/visualize/chargingTimePeak')

    web_socket.onerror = function (event) {
        onError(event)
    };
    web_socket.onopen = function (event) {
        onOpen(event)
    };
    web_socket.onmessage = function (event) {//event中包含的是服务端向客户端发送过来的数据
        setChargingTimePeak(JSON.parse(event.data))
    };

    function onOpen(event) {
        /**webSocket open之后，发送消息给message 服务并保持长链接的消息输出**/
        web_socket.send("chargingTimePeak");
    }

    function onError(event) {
        alert(event.data);
    }
    function setChargingTimePeak(jsonData) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('id_charging_time_peak'));
        var xAxisData = jsonData.time //['星期一','星期二 ','星期三 ','星期四','星期五','星期六','星期天']
        var yAxisData = jsonData.data //[580, 490, 700,450, 550, 660, 540]
        option = {
            tooltip : {
                trigger: 'axis'
            },

            grid: {
                left: '3%',
                right: '5%',
                top:'8%',
                bottom: '5%',
                containLabel: true
            },
            color:['#a4d8cc','#25f3e6'],
            toolbox: {
                show : false,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },

            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    axisTick:{show:false},
                    boundaryGap : false,
                    axisLabel: {
                        textStyle:{
                            color: 'rgba(255,255,255,.6)',
                            fontSize:'12'
                        },
                        lineStyle:{
                            color:'rgba(255,255,255,.1)',
                        },
                        interval: {default: 0},
                        //   rotate:50,
                        formatter : function(params){
                            var newParamsName = "";// 最终拼接成的字符串
                            var paramsNameNumber = params.length;// 实际标签的个数
                            var provideNumber = 4;// 每行能显示的字的个数
                            var rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
                            /**
                             * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
                             */
                            // 条件等同于rowNumber>1
                            if (paramsNameNumber > provideNumber) {
                                /** 循环每一行,p表示行 */
                                var tempStr = "";
                                tempStr=params.substring(0,4);
                                newParamsName = tempStr+"...";// 最终拼成的字符串
                            } else {
                                // 将旧标签的值赋给新标签
                                newParamsName = params;
                            }
                            //将最终的字符串返回
                            return newParamsName
                        }

                    },
                    data: xAxisData
                }
            ],
            yAxis : {
                min:300,
                type : 'value',
                axisLabel: {
                    textStyle: {
                        color: '#ccc',
                        fontSize:'12',
                    }
                },
                axisLine: {
                    lineStyle:{
                        color:'rgba(160,160,160,0.2)',
                    }
                },
                splitLine: {
                    lineStyle:{
                        color:'rgba(160,160,160,0.2)',
                    }
                },

            },

            series : [
                {
                    // name:'简易程序案件数',
                    lineStyle:{
                        color:'#72b0f9',
                    },

                    type:'line',
                    areaStyle: {

                        normal: {type: 'default',
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 0.8, [{
                                offset: 0,
                                color: 'rgba(129,197,255,.6)'
                            }, {
                                offset: 1,
                                color: 'rgba(129,197,255,.0)'
                            }], false)
                        }
                    },
                    smooth:true,
                    itemStyle: {
                        normal: {areaStyle: {type: 'default'}}
                    },
                    data: yAxisData
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