function register() {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);
  xhr.onload = function(event) {
    event.preventDefault();
    var result = this.responseText;
    if(result == "")
      document.getElementById("ok").style.display = 'block';
    else
      document.getElementById("error").style.display = 'block';
  };
  xhr.send(formData);
}

function show_cart_size() {
  var cart_size = document.getElementById('cart_size');
  var span_size = document.getElementById('size');
  if(cart_size !== null) {
    var cliente = cart_size.dataset.cliente;
    var url = cart_size.dataset.url;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        var size = xhr.responseText;
        if(cart_size) {
          cart_size.innerHTML = size;
          if(size > 0)
            cart_size.removeAttribute('style');
          else
            cart_size.setAttribute('style', 'display: none;');
        }
        if(span_size)
          span_size.innerHTML = size;
        return size;
      }
    };
    var formData = new FormData();
    formData.append('cliente', cliente);
    xhr.send(formData);
  }
}

function show_cart_total() {
  var cart_total = document.getElementById('cart_total');
  var span_total = document.getElementById('total');
  if(cart_total !== null) {
    var cliente = cart_total.dataset.cliente;
    var url = cart_total.dataset.url;
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState == 4 && xhr.status == 200) {
        var total = xhr.responseText;
        var currency = new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(total);
        if(cart_total)
          cart_total.innerHTML = currency;
        if(span_total)
          span_total.innerHTML = currency;
        return currency;
      }
    };
    var formData = new FormData();
    formData.append('cliente', cliente);
    xhr.send(formData);
  }
}

function add_to_cart(btn) {
  var cliente = btn.dataset.cliente;
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
  formData.append('cliente', cliente);
  formData.append('produto', produto);
  xhr.send(formData);
}

function remove_from_cart(btn) {
  var cliente = btn.dataset.cliente;
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

      if(size == 0) {
        document.getElementById("empty_cart").style.display = 'block';
        document.getElementById("cart").style.display = 'none';
        document.getElementById("table").style.display = 'none';
      }
    }
  };
  var formData = new FormData();
  formData.append('cliente', cliente);
  formData.append('produto', produto);
  xhr.send(formData);
}

function save_topic(element) {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);
  xhr.onload = function(event) {
    event.preventDefault();
    var result = this.responseText;
    if(result == "") {
      document.getElementById("ok").style.display = 'block';
      $('#novo_topico').modal('hide');
      document.location.reload();
    } else {
      document.getElementById("error").style.display = 'block';
    }
  };
  xhr.send(formData);
}
