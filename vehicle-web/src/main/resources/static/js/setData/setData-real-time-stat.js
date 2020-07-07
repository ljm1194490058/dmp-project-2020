function loadRealTimeStat() {
    var web_socket = new WebSocket('ws://localhost:8080/vehicle/visualize/real_time_stat')

    web_socket.onerror = function (event) {
        onError(event)
    };
    web_socket.onopen = function (event) {
        onOpen(event)
    };
    web_socket.onmessage = function (event) {//event中包含的是服务端向客户端发送过来的数据
        setRealTimeStat(event)
    };

    function setRealTimeStat(event) {
        $("#id_total_vehicle").empty();
        $("#id_online_vehicle").empty();
        $("#id_active_vehicle").empty();
        $("#id_active_percent").empty();
        var total_div = document.getElementById("id_total_vehicle");//获得id为sum的document对象
        var online_div = document.getElementById("id_online_vehicle");//获得id为sum的document对象
        var active_div = document.getElementById("id_active_vehicle");//获得id为sum的document对象
        var active_percent_div = document.getElementById("id_active_percent");//获得id为sum的document对象
        //后台传输过来的数据格式是一个json
        var json = JSON.parse(event.data);//转化为json对象
        total_div.innerText = numFormat(json.total);
        online_div.innerText = numFormat(json.online);
        active_div.innerText = numFormat(json.active);
        active_percent_div.innerText = json.activeRate;
    }

    function onOpen(event) {
        /**webSocket open之后，发送消息给message 服务并保持长链接的消息输出**/
        web_socket.send("real_time_stat");
    }

    function onError(event) {
        alert(event.data);
    }
}
