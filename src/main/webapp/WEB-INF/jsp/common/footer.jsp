</div>

<script src="https://code.jquery.com/jquery-2.2.4.min.js"
    integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<script src="/js/uikit.min.js"></script>
<script src="/js/uikit-icons.min.js"></script>


<script>
    var stompClient = null;

    function connect() {
        var socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            id = $("#simulationId").text();
            console.log('Connected: ' + frame);
            stompClient.subscribe('/report/' + id, function (greeting) {
                update(greeting.body);
            });
            stompClient.subscribe('/topic/' + id, function (greeting) {
                streamPosition(greeting.body);
            });
        });
    }

    function streamPosition(message) {
        console.log(message);
        var div = "<div>" + message + "</div>";
        $("#positions").append(div);
        var height = $("#positions > div").height(); console.log("height", height);

        if(height > 10) $("#positions > div")[0].remove();
    }

    function update(message) {
        var obj = JSON.parse(message);
        $("#qty-positions").text(obj.positions);
        $("#qty-vehicles").text(obj.vehicles);
        $(".online").text(obj.online);
        $(".offline").text(obj.offline);
    }

    $(function () { connect() });
</script>

</body>

</html>