function roundToPIPScale(number) {
  return Math.round(number * 1000000) / 1000000
}

function drawLine(context,sourceX,sourceY,destnationX,destnationY) {
  context.beginPath();
  context.moveTo(sourceX, sourceY);
  context.lineTo(destnationX, destnationY);
  context.stroke();
}

function calcScale(data,boxSize) {
  var result = new Object();
  result.stepInPixel = boxSize / data.length;

  var min = Number.POSITIVE_INFINITY;
  var max = Number.NEGATIVE_INFINITY;
  for(var i = 0; i < data.length;i++){
    if(data[i] < min){
      min = data[i];
    }
    if(data[i] > max){
      max = data[i];
    }

  }

  var delta = max - min;

  result.offsetY = min;
  result.multiplicatorY = ((boxSize / delta) / 100) *90;
  return result;
}


function drawGraphAxis(context,data,boxSize) {
  var labelCount = 10;
  var stepSize = boxSize / labelCount;

  var min = Number.POSITIVE_INFINITY;
  var max = Number.NEGATIVE_INFINITY;
  for(var i = 0; i < data.length;i++){
    if(data[i] < min){
      min = data[i];
    }
    if(data[i] > max){
      max = data[i];
    }
  }

  for(var i = 0; i <= labelCount;i++){
    var delta = max - min;
    var currentScale = (1 / labelCount) * i;
    var label = roundToPIPScale(min + (delta*currentScale));
    context.fillText( label, boxSize + 5, ((stepSize * i) * -1 ) + boxSize ) ;
  }
}

function drawGraph(context,canvas,data,maxValueCount) {
  context.clearRect(0, 0, canvas.width, canvas.height);
  var boxSize = canvas.height;

  drawLine(context,0,0,0,boxSize);
  drawLine(context,0,0,boxSize,0);
  drawLine(context,boxSize,0,boxSize,boxSize);
  drawLine(context,0,boxSize,boxSize,boxSize);

  var scale = calcScale(data,boxSize);

  var stepInPixel = scale.stepInPixel;
  var multiplicatorY = scale.multiplicatorY;
  var offsetY = scale.offsetY;

  var offset = 0;
  var lastY = 0;
  for(var i = 0; i < data.length;i++){
    var currentY =  ((data[i] * multiplicatorY) * -1) + (offsetY* multiplicatorY)   + boxSize ;
    if(i == 0){
      lastY = currentY;
    }

    drawLine(context,offset,lastY,offset+stepInPixel,currentY)
    offset += stepInPixel;
    lastY = currentY;

  }
  drawGraphAxis(context,data,boxSize);
}

function draw_graph_pro() {
  var g_pro = document.getElementById('canvas_pro');
  var ctx_pro = g_pro.getContext('2d');

  var chartData = [];

  for(var i=0; i<100; i++) {
    var randomPrice =  (Math.random()*0.0001) +1.25;
    chartData.push(randomPrice);
    if(chartData.length > 100) {
     chartData.splice(0, 1);
    }
    drawGraph(ctx_pro,g_pro,chartData);
  }
}

function draw_graph_lite() {
  var g_lite = document.getElementById('canvas_lite');
  var ctx_lite = g_lite.getContext('2d');

  var chartData = [];

  for(var i=0; i<100; i++) {
    var randomPrice =  (Math.random()*0.0001) +1.25;
    chartData.push(randomPrice);
    if(chartData.length > 100){
     chartData.splice(0, 1);
    }
    drawGraph(ctx_lite,g_lite,chartData);
  }
}
