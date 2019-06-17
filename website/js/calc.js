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
    display(data)
  } else if (message.startsWith("eval:")) {
    var data = message.replace("eval:", "")
    display(data)
  }
};

function sign(operator) {
  if (operator == "π") {
    var num = document.getElementById("num").value;
    socket.send("num:" + num)
    socket.send("num:" + Math.PI)
    display(Math.PI.toFixed(2))
  } else if (operator == "√") {
    var num = document.getElementById("num").value;
    socket.send("num:" + num)
    socket.send("operator:" + operator)
    eval()
  } else {
    var num = document.getElementById("num").value;
    socket.send("num:" + num)
    socket.send("operator:" + operator)
    display('')
  }
}

function display(output) {
  if (output == '') {
    document.getElementById("num").value = output;
    return
  }
  console.log("isNaN(" + output + ") == " + isNaN(output))
  if (!isNaN(output)) {
    split = output.toString().split(".", "")
    console.log(split)
    if (split.length > 1 && split[0].length > 9 && split[1].length > 2) {
      output = output.toExponential()
    }
    if (output instanceof Number) {
      output = output.toFixed(2).toString()
    }
    // todo finish exponents
  }
  document.getElementById("num").value = (output.toString().replace(/(\.[0-9]*[1-9])0+$|\.0*$/,'$1'));
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
  display('')
  socket.send("clear")
}

function input(val) {
  document.getElementById("num").value+=val;
}

function eval() {
  var num = document.getElementById("num").value;
  socket.send("num:" + num)
  socket.send("eval")
}
