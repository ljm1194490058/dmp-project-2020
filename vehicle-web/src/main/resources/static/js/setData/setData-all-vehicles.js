function loadRealTimeAllVehicles() {
    var web_socket = new WebSocket('ws://localhost:8080/vehicle/visualize/all_vehicle')

    web_socket.onerror = function (event) {
        onError(event)
    };
    web_socket.onopen = function (event) {
        onOpen(event)
    };
    web_socket.onmessage = function (event) {//event中包含的是服务端向客户端发送过来的数据
        setTotalVehicle(event)
    };

    function setTotalVehicle(event) {
        $("#num").empty();
        var all_vehicle_div = document.getElementById("num");//获得id为num的document对象
        var nums = numFormat(event.data).split('')
        var str = ""
        for(var i = 0; i < nums.length; i++) {
            str += "<span>" + nums[i] + "</span>"
        }

        all_vehicle_div.innerHTML = str;
    }

    function onOpen(event) {
        /**webSocket open之后，发送消息给message 服务并保持长链接的消息输出**/
        web_socket.send("all_vehicle");
    }

    function onError(event) {
        alert(event.data);
    }
}