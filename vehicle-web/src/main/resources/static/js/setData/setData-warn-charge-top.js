function loadWarnChargeTop() {
    var web_socket = new WebSocket('ws://localhost:8080/vehicle/visualize/warnChargeTop')

    web_socket.onerror = function (event) {
        onError(event)
    };
    web_socket.onopen = function (event) {
        onOpen(event)
    };
    web_socket.onmessage = function (event) {//event中包含的是服务端向客户端发送过来的数据
        setWarnChargeTop(JSON.parse(event.data))
    };

    function onOpen(event) {
        /**webSocket open之后，发送消息给message 服务并保持长链接的消息输出**/
        web_socket.send("warnChargeTop");
    };

    function onError(event) {
        alert(event.data);
    };

    function setWarnChargeTop(jsonData) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('id_warn_charge_top'));

        var data = {
            "chart": jsonData/*[
                {
                    vehicle: "NO.1",
                    value: 10,
                },
                {
                    vehicle: "NO.2",
                    value: 8.7,
                }

            ]*/
        }


        var xAxisVehicle = [],
            barData = [],
            lineData = [];
        for (var i = 0; i < data.chart.length; i++) {
            xAxisVehicle.push(data.chart[i].vehicle);
            barData.push({
                "name": xAxisVehicle[i],
                "value": data.chart[i].value
            });
            lineData.push({
                "name": xAxisVehicle[i],
                "value": data.chart[i].ratio
            });
        }

        option = {
            // backgroundColor: "#020d22",
            title: '',
            grid: {
                top: '10%',
                left: '30',
                bottom: '0',
                right:'10',
                containLabel: true
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'none'
                },
                formatter: function(params) {
                    return params[0]["data"].name + "<br/>" + '报警次数: ' + params[1]["data"].value+'次' ;
                }
            },
            xAxis: [{
                type: 'category',
                show: false,
                data: xAxisVehicle,
                axisLabel: {
                    textStyle: {
                        color: '#b6b5ab'
                    }
                }
            },
                {
                    type: 'category',
                    position: "bottom",
                    data: xAxisVehicle,
                    boundaryGap: true,
                    // offset: 40,
                    splitLine: {
                        show: false,
                        lineStyle: {
                            color: 'rgba(255,255,255,0.2)'
                        }
                    },
                    axisTick: {
                        show: false
                    },
                    axisLine: {
                        show: true,
                        color: "rgba(255,255,255,0.2)"
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#b6b5ab'
                        }
                    }
                }

            ],
            yAxis: [{
                show: true,
                offset: 15,
                splitLine: {
                    show: false,
                    lineStyle: {
                        color: "rgba(255,255,255,0.1)"
                    }
                },

                axisTick: {
                    show: false
                },
                axisLine: {
                    show: true,
                    color: "rgba(255,255,255,0.1"
                },
                axisLabel: {
                    show: true,
                    color: '#b6b5ab'
                }
            }, {
                show: false,
                type: "value",
                // name: "合格率(%)",
                nameTextStyle: {
                    color: '#ccc'
                },
                axisLabel: {
                    color: '#ccc'
                },
                splitLine: {
                    show: false
                },
                axisLine: {
                    show: true
                },
                axisTick: {
                    show: true
                }
            }],
            color: ['#e54035'],
            series: [{
                name: '训练人次',
                type: 'pictorialBar',
                xAxisIndex: 1,
                barCategoryGap: '-40%',
                // barCategoryGap: '-5%',
                symbol: 'path://d="M150 50 L130 130 L170 130  Z"',
                itemStyle: {
                    normal: {
                        color: function(params) {
                            var colorList = [
                                'rgba(13,177,205,0.8)', 'rgba(29,103,182,0.6)',
                                'rgba(13,177,205,0.8)', 'rgba(29,103,182,0.6)',
                                'rgba(13,177,205,0.8)', 'rgba(29,103,182,0.6)'
                            ];
                            return colorList[params.dataIndex];
                        }
                    },
                    emphasis: {
                        opacity: 1
                    }
                },
                data: barData,
            },
                {
                    symbol: 'image://data:image/png;base64,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',
                    symbolSize: 42,

                    type: "line",
                    yAxisIndex: 1,
                    data: lineData,
                    itemStyle: {
                        normal: {
                            borderWidth: 5,
                            color: {
                                colorStops: [{
                                    offset: 0,
                                    color: '#821eff'
                                },

                                    {
                                        offset: 1,
                                        color: '#204fff'
                                    }
                                ],
                            }
                        }
                    }
                }
            ]
        }


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }
}