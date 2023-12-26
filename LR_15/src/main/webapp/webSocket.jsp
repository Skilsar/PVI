<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>WebSocket</title>
  <script>
    let socket;

    function startConnection() {
      socket = new WebSocket('ws://localhost:8086/HGG_15/websocket-endpoint');

      socket.onopen = function () {
        console.log('connect');
      };

      socket.onmessage = function (event) {
        document.getElementById('messages').innerHTML += '<p>' + event.data + '</p>';
      };
    }

    function stopConnection() {
      if (socket) {
        socket.close();
        console.log('connect end');
      }
    }
  </script>
</head>
<body>
<button onclick="startConnection()">Start</button>
<button onclick="stopConnection()">Stop</button>
<div id="messages"></div>
</body>
</html>
