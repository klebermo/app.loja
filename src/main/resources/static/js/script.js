
function show_cart_size() {
  var cart_size = document.getElementById('cart_size');
  if(cart_size !== null) {
    var usuario = cart_size.dataset.usuario;
    var url = cart_size.dataset.url;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        var size = xhr.responseText;
        cart_size.innerHTML = size;
        var style = window.getComputedStyle(cart_size);
        if(size > 0)
          cart_size.removeAttribute('style');
        else
          cart_size.setAttribute('style', 'display: none;');
        return size;
      }
    };
    var formData = new FormData();
    formData.append('usuario', usuario);
    xhr.send(formData);
  }
}

function show_cart_total() {
  var cart_total = document.getElementById('cart_total');
  if(cart_total !== null) {
    var usuario = cart_total.dataset.usuario;
    var url = cart_total.dataset.url;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        var total = xhr.responseText;
        var currency = new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(total)
        cart_total.innerHTML = currency;
        return currency;
      }
    };
    var formData = new FormData();
    formData.append('usuario', usuario);
    xhr.send(formData);
  }
}

function add_to_cart(btn) {
  var usuario = btn.dataset.usuario;
  var produto = btn.dataset.produto;
  var url = btn.dataset.url;
  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var size = show_cart_size();
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
  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var target = document.getElementById('produto_'+produto);
      target.remove();
      var size = show_cart_size();
      var total = show_cart_total();
      var x = document.getElementsByTagName("tfoot");
      x[0].setAttribute("style", "display: none.");
    }
  };
  var formData = new FormData();
  formData.append('usuario', usuario);
  formData.append('produto', produto);
  xhr.send(formData);
}

var editor = document.getElementsByClassName("summernote");
for (i = 0; i < editor.length; i++) {
  $(editor).summernote({height: 300});
}
