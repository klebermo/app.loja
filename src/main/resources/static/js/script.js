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

function recover() {
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

function update_cart() {
  var cart_size = document.getElementById('cart_size');
  var cart_status = document.getElementById('cart_status');
  var cart_total = document.getElementById('cart_total');

  var size, total, currency;

  var promise1 = new Promise(function(resolve, reject) {
    if(cart_size !== null) {
      var cliente = cart_size.dataset.cliente;
      var url = cart_size.dataset.url;

      var xhr = new XMLHttpRequest();
      xhr.open("POST", url, true);
      xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
          resolve(xhr.responseText);
        }
      };
      var formData = new FormData();
      formData.append("cliente", cliente);
      xhr.send(formData);
    }
  });

  var promise2 = new Promise(function(resolve, reject) {
    if(cart_total !== null) {
      var cliente = cart_total.dataset.cliente;
      var url = cart_total.dataset.url;

      var xhr = new XMLHttpRequest();
      xhr.open("POST", url, true);
      xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
          resolve(xhr.responseText);
        }
      };
      var formData = new FormData();
      formData.append("cliente", cliente);
      xhr.send(formData);
    }
  });

  var promise3 = new Promise(function(resolve, reject) {
    if(cart_status !== null) {
      resolve();
    }
  });

  promise1.then(function(value) {
    size = value;
    cart_size.innerHTML = size;
    if(size == 0)
      cart_size.style.display = 'none';
    else
      cart_size.style.display = 'block';

    promise2.then(function(value) {
      total = value;
      currency = new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(total);
      cart_total.innerHTML = currency;

      promise3.then(function() {
        switch(size) {
          case '0':
            cart_status.innerHTML = 'A cesta de compras está vazia.';
            document.getElementById('table').style.display = 'none';
            break;
          case '1':
            cart_status.innerHTML = size + ' produto adicionado na cesta; valor total ' + currency;
            document.getElementById('table').style.display = 'block';
            break;
          default:
            cart_status.innerHTML = size + ' produtos adicionados na cesta; valor total ' + currency;
            document.getElementById('table').style.display = 'block';
            break;
        }
      });
    });
  });
}

function add_to_cart(btn) {
  var produto = btn.dataset.produto;
  var url = btn.dataset.url;
  var url_cart = btn.dataset.cart;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
      update_cart();
      document.location.href =  url_cart
    }
  };

  var formData = new FormData();
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
      update_cart();
    }
  };

  var formData = new FormData();
  formData.append('cliente', cliente);
  formData.append('produto', produto);
  xhr.send(formData);
}

function submit_topic(e) {
  var url = document.getElementById("form").action;
  var url_destino = e.dataset.destination;

  var titulo = document.querySelector('input[name="titulo"]').value;
  var descricao = document.querySelector('textarea').value;
  var dataPublicacao = document.querySelector('input[name="dataPublicacao"]').value;
  var autor = document.querySelector('input[name="autor"]').value;
  var forum = document.querySelector('input[name="forum"]').value;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function()  {
    if (xhr.readyState == 4 && xhr.status == 200) {
      document.location.href =  url_destino;
    }
  };
  var formData = new FormData();
  formData.append('titulo', titulo);
  formData.append('descricao', descricao);
  formData.append('dataPublicacao', dataPublicacao);
  formData.append('autor', autor);
  formData.append('forum', forum);
  xhr.send(formData);
}
