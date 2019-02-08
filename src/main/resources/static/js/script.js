function add_to_cart(btn) {
  var usuario = btn.dataset.usuario;
  var produto = btn.dataset.produto;
  var url = btn.dataset.url;
  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
      //
    }
  };
  var formData = new FormData();
  formData.append('usuario', usuario);
  formData.append('produto', produto);
  xhr.send(formData);
}

function remove_from_cart(btn) {
  var usuario = btn.dataset.usuario;
  var produto = btn.dataset.produto;
  var url = btn.dataset.url;
  console.log('remover produto: '+produto+' do usuario '+usuario);
}

function checkout(btn) {
  var usuario = btn.dataset.usuario;
  var url = btn.dataset.url;
  console.log('finalizar compra do usuario '+usuario);
}
