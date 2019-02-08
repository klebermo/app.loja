var btn_comprar = document.getElementByIdName("comprar");
btn_comprar.addEventListener("click", add_to_cart, false);

var btn_remover = document.getElementsByClassName("remover");
for (i = 0; i < btn_remover.length; i++)
  btn_remover[i].addEventListener("click", remove_from_cart, false);

function add_to_cart() {
  var btn = this;
  var usuario = btn.dataset.usuario;
  var produto = btn.dataset.produto;
  console.log('adicionar produto: '+produto+' do usuario '+usuario);
}

function remove_from_cart() {
  var btn = this;
  var usuario = btn.dataset.usuario;
  var produto = btn.dataset.produto;
  console.log('remover produto: '+produto+' do usuario '+usuario);
}
