var socket = new WebSocket("ws://localhost:8080")

socket.onopen = function(e) {
  socket.send("open");
};

socket.onclose = function(event) {};

socket.onerror = function(error) {};

socket.onmessage = function(event) {
  var message = event.data;
  if (message.startsWith("mrc:")) {
    var data = message.replace("mrc:", "")
    document.getElementById("num").value = data;
  } else if (message.startsWith("eval:")) {
    var data = message.replace("eval:", "")
    document.getElementById("num").value = data;
  }
  return false;
};

function sign(operator) {
  var num = document.getElementById("num").value;
  socket.send("num:" + num)
  socket.send("operator:" + operator)
  clr()
}

function store_memory() {
  socket.send("m+")
}

function clear_memory() {
  socket.send("m-")
}

function read_memory() {
  socket.send("mrc");
}

function bck() {
  var num = document.getElementById("num").value;
  document.getElementById("num").value = num.substring(0, num.length-1)
}

function clr() {
  document.getElementById("num").value = '';
}

function c(val) {
  document.getElementById("num").value=val;
}

function input(val) {
  document.getElementById("num").value+=val;
}

function eval() {
  var num = document.getElementById("num").value;
  socket.send("num:" + num)
  socket.send("eval")
}
