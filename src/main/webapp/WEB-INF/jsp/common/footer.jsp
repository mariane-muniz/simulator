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
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/' + $("#simulationId").text(), function (greeting) {
                update(greeting.body);
            });
        });
    }

    function update(message) {
        var obj = JSON.parse(message);
        console.log(obj);
        $("#qty-positions").text(obj.positions);
        $("#qty-vehicles").text(obj.vehicles);
    }

    $(function () { connect() });
</script>

</body>

</html>