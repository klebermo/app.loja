var img_file = document.getElementById('container').dataset.map;

var compras = document.getElementById('map_compras');
var ctx_compras = compras.getContext('2d');
var img_compras = new Image();
img_compras.onload = function() {
  ctx_compras.drawImage(img_compras, 0, 0, compras.width, compras.height);
}
img_compras.src = img_file;

var acessos = document.getElementById('map_acessos');
var ctx_acessos = acessos.getContext('2d');
var img_acessos = new Image();
img_acessos.onload = function() {
  ctx_acessos.drawImage(img_acessos, 0, 0, acessos.width, acessos.height);
}
img_acessos.src = img_file;
