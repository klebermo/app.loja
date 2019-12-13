function search(e) {
  var value = e.value;
  var url = e.dataset.search;

  var keys = document.getElementById('table-header').querySelectorAll('.search');
  for (var i = 0; i < keys.length; i++) {
    var text = keys[i].innerText;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        console.log('search for \"'+text+'\" = \"'+value+'\"');
      }
    };
    var search_url = url + '?key=' + text + '&value=' + value + '&pagina=' + getPagina() + '&itemsPorPagina=' + getItemPorPagina();
    xmlhttp.open("GET", search_url, true);
    xmlhttp.send();
  }
}

function getItemPorPagina() {
  var porPagina = document.getElementById("itemPorPagina");
  return porPagina.querySelector(".active").innerText;
}

function setItemsPorPagina(value) {
  var current = getItemPorPagina();
  if(value !== current) {
    var list = document.getElementById('itemPorPagina');
    for(var i=0; i<list.children.length; i++)
      if(list.children[i].classList.contains('active'))
        list.children[i].classList.remove('active');
    for(var i=0; i<list.children.length; i++)
      if(list.children[i].innerText == value)
        list.children[i].classList.add('active');
  }
  clear_content();
  load_content();
}

function getPagina() {
  var pagination = document.getElementById("pagination");
  return pagination.dataset.pagina;
}

function setPagina(value) {
  var current = getPagina();
  var pagination = document.getElementById('pagination');
  var page_items = pagination.querySelectorAll('.page-item');
  if(value !== current) {
    for(var i=0; i<page_items.length; i++)
      if(page_items[i].classList.contains('active'))
        page_items[i].classList.remove('active');
    for(var i=0; i<page_items.length; i++)
      if(page_items[i].querySelector('.page-link').innerText == value)
        page_items[i].classList.add('active');
    pagination.setAttribute('data-pagina', value);
  }
  clear_content();
  load_content();
}

function getPrevious() {
  var current = getPagina();
  setPagina(current - 1);
}

function getNext() {
  var current = getPagina();
  setPagina(current + 1);
}

function add_row(data) {
  var tr = document.createElement("tr");
  document.getElementById("table-body").appendChild(tr);
  return tr;
}

function read_object(data) {
  if(data == null) {
    return "...";
  } else {
    for(var z in data) {
      if(data[z] == null)
        return "...";

      var object = data[z];
      if(object['idioma']) {
        var lang = navigator.language;
        var idioma = object['idioma'].substring(0, 5);
        if(lang === idioma) return object['conteudo'];
      }

      if(z == 'username')
        return data['firstName'] + ' ' + data['lastName'];
    }
  }
  return data;
}

function add_col(row, data) {
  var td = document.createElement("td");
  if(typeof data === 'object') {
    td.textContent = read_object(data);
  } else {
    td.textContent = data;
  }
  row.appendChild(td);
}

function add_col_first(row, data) {
  var td = document.createElement("th");
  td.setAttribute('scope', 'row');
  td.textContent = data['id'];
  row.appendChild(td);
}

function add_col_last(row, data) {
  var td = document.createElement("td");
  var btn = document.getElementById('buttons').cloneNode(true);
  for (var i = 0; i < btn.children.length; i++)
    if(btn.children[i].hasAttribute('data-url'))
      btn.children[i].setAttribute('data-id', data['id']);
  btn.removeAttribute('style');
  td.appendChild(btn);
  row.appendChild(td);
}

function clear_content() {
  document.getElementById('table-body').innerHTML = '';
  document.getElementById('pagination').innerHTML = '';
}

function load_pagination() {
  var page_item = document.getElementById('pagination');

  if(page_item) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var size = this.responseText;

        var pagina = getPagina();
        var porPagina = getItemPorPagina();
        var total = Math.ceil(size / porPagina);

        var previous = document.createElement('li');
        previous.classList.add('page-item');
        var a_previous = document.createElement('a');
        a_previous.classList.add('page-link');
        a_previous.setAttribute('href', '#');
        if(pagina == 1) {
          previous.classList.add('disabled');
          a_previous.setAttribute('tabindex', '-1');
          a_previous.setAttribute('aria-disabled', 'true');
        }
        a_previous.setAttribute('onclick', 'getPrevious();');
        a_previous.innerText = 'Previous';
        previous.appendChild(a_previous);
        page_item.appendChild(previous);

        for(var i=0; i<total; i++) {
          var li = document.createElement('li');
          li.classList.add('page-item');
          if(pagina == i+1)
            li.classList.add('active');

          var a = document.createElement('a');
          a.classList.add('page-link');
          a.setAttribute('href', '#');
          a.setAttribute('onclick', 'setPagina('+(i+1)+')');
          a.innerText = i+1;

          li.appendChild(a);
          page_item.appendChild(li);
        }

        var next = document.createElement('li');
        next.classList.add('page-item');
        var a_next = document.createElement('a');
        a_next.classList.add('page-link');
        a_next.setAttribute('href', '#');
        if(pagina == total) {
          next.classList.add('disabled');
          a_next.setAttribute('tabindex', '-1');
          a_next.setAttribute('aria-disabled', 'true');
        }
        a_next.setAttribute('onclick', 'getPrevious();');
        a_next.innerText = 'Next';
        next.appendChild(a_next);
        page_item.appendChild(next);
      }
    };
    xmlhttp.open("GET", page_item.dataset.size, true);
    xmlhttp.send();
  }
}

function load_content() {
  var table = document.getElementById("table");

  if(table) {
    var json = table.dataset.json;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = JSON.parse(this.responseText);

        var columns = [];
        var foo = document.getElementById('table-header');
        for (var i = 0; i < foo.children.length; i++)
          if(foo.children[i].hasAttribute('class'))
            columns.push(foo.children[i].textContent);

        for(var x in myObj) {
          var data = myObj[x];
          var row = add_row(data);
          add_col_first(row, x);
          for(var y in columns) {
            var col = columns[y];
            var data1 = data[col];
            add_col(row, data1);
          }
          add_col_last(row, data);
        }
      }
    };
    var url = json + '?pagina=' + getPagina() + '&itemsPorPagina=' + getItemPorPagina();
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
  load_pagination();
}

function open_tab(e) {
  var url = e.dataset.url;
  var id = e.dataset.id;
  var target = e.id;

  if(id)
    url = url + '?id=' + id;

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      var myObj = this.responseText;
      var parser = new DOMParser();
      var form_html = parser.parseFromString(myObj, "text/html");

      if(id) {
        var nav_tab = document.getElementById(target + '-tab').cloneNode(true);
        var tab_pane = document.getElementById(target + '-pane').cloneNode(true);

        nav_tab.setAttribute('id', target + '-tab-' + id);
        nav_tab.setAttribute('href', '#' + target + '-pane-' + id);
        nav_tab.setAttribute('aria-controls', target + '-pane-' + id);

        tab_pane.setAttribute('id', target + '-pane-' + id);
        tab_pane.setAttribute('aria-labelledby', target + '-tab-' + id);
        tab_pane.innerHTML = form_html.getElementById('container').innerHTML;
        document.getElementById('tab-content').appendChild(tab_pane);

        var li = document.createElement('li');
        li.setAttribute('class', 'nav-item');
        li.appendChild(nav_tab);
        document.getElementById('myTab').appendChild(li);

        var textareas = tab_pane.querySelectorAll('.summernote');
        for(var i=0; i<textareas.length; i++)
          $(textareas[i]).summernote({height: 300});

        $('#' + target + '-tab-' + id).tab('show');
      } else {
        document.getElementById(target + '-tab').parentElement.style.display = "block";
        document.getElementById(target + '-pane').innerHTML = form_html.getElementById('container').innerHTML;

        var tab_pane = document.getElementById(target + '-pane');

        var textareas = tab_pane.querySelectorAll('.summernote');
        for(var i=0; i<textareas.length; i++)
          $(textareas[i]).summernote({height: 300});

        $('#' + target + '-tab').tab('show');
      }
    }
  };
  xmlhttp.open("GET", url, true);
  xmlhttp.send();
}

function close_tab(e) {
  var id = document.getElementById('id').value;
  var target = e.id;

  if(id) {
    $('#home-tab').tab('show');

    document.getElementById('table-body').innerHTML = '';
    clear_content();
    load_content();

    var tab_pane = document.getElementById(target + '-pane-' + id);
    tab_pane.parentElement.removeChild(tab_pane);

    var nav_tab = document.getElementById(target + '-tab-' + id);
    nav_tab.parentElement.parentElement.removeChild(nav_tab.parentElement);
  } else {
    $('#home-tab').tab('show');

    document.getElementById('table-body').innerHTML = '';
    clear_content();
    load_content();

    var tab_pane = document.getElementById(target + '-pane');
    tab_pane.innerHTML = '';

    var nav_tab = document.getElementById(target + '-tab');
    nav_tab.parentElement.style.display = 'none';
  }
}

function submit() {
  var form = document.getElementById('form');
  var formData = new FormData(form);
  var url = form.action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);

  xhr.onload = function(event) {
    event.preventDefault();
  };

  xhr.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      var json = JSON.parse(this.responseText);
      if(json['erro']) {
        form.querySelector('#error').querySelector('#texto').innerText = json['message'];
        form.querySelector('#error').style.display = 'block';
      } else {
        switch(json['action']) {
          case 'insert':
          form.querySelector('#ok').querySelector('#texto').innerText = 'o cadastro de '+json['entity']+' foi efetivado com sucesso!';
          break;
          case 'update':
          form.querySelector('#ok').querySelector('#texto').innerText = 'a atualizacão de '+json['entity']+' foi efetivada com sucesso!';
          break;
          case 'delete':
          form.querySelector('#ok').querySelector('#texto').innerText = 'a remoção de '+json['entity']+' foi efetivada com sucesso!';
          break;
        }
        form.querySelector('#ok').style.display = 'block';
      }
    }
  };

  xhr.send(formData);
}

function toggle_credencial(element) {
  var usuario = element.dataset.usuario;
  var credencial = element.dataset.credencial;
  var url = element.dataset.url;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  var formData = new FormData();
  formData.append('usuario_id', usuario);
  formData.append('credencial_id', credencial);
  xhr.send(formData);
}

function select(element) {
  var target = element.dataset.target;
  document.getElementById(target).click();
}

function image_upload(file_input) {
  var name = file_input.dataset.target;
  var url = file_input.dataset.url;
  var path = file_input.dataset.path;

  for(var i = 0; i<file_input.files.length; i++) {
    var file =  file_input.files[i];
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var id = xhr.responseText;

            if(name === 'icone') {
              document.getElementById("empty_image").style.display = 'none';

              var input = document.createElement("input");
              input.setAttribute("type", "hidden");
              input.setAttribute("name", name);
              input.setAttribute("value", id);

              var img = document.createElement("img");
              img.setAttribute("class", "thumbnail");
              img.setAttribute("id", "single_image");
              img.setAttribute('src', path + '/' + id);
              img.setAttribute('width', '64');
              img.setAttribute('height', '64');

              file_input.parentElement.append(input);
              file_input.parentElement.append(img);
            }

            if(name === 'thumbnails') {
              var input = document.createElement("input");
              input.setAttribute("type", "hidden");
              input.setAttribute("name", name);
              input.setAttribute("value", id);

              var div = document.createElement("div");
              div.setAttribute("class", "col-lg-3 col-md-4 col-6");

              var a = document.createElement("a");
              a.setAttribute("href", "#");
              a.setAttribute("class", "d-block mb-4 h-100");

              var img = document.createElement("img");
              img.setAttribute("class", "thumbnail");
              img.setAttribute("id", "image_"+id);
              img.setAttribute('src', path + '/' + id);
              img.setAttribute('width', '64');
              img.setAttribute('height', '64');

              a.appendChild(img);
              div.appendChild(a);

              document.getElementById('gallery').append(input);
              document.getElementById('gallery').append(div);
            }
        }
    };
    var reader  = new FileReader();
    reader.onloadend = function() {
      var bytes = reader.result;
      var ext = file.name.split(".").pop();
      var formData = new FormData();
      formData.append('bytes', bytes);
      formData.append('type', ext);
      xhr.send(formData);
    }
    reader.readAsDataURL(file);
  }
}

function file_upload(file_input) {
  var name = file_input.dataset.target;
  var url = file_input.dataset.url;
  var path = file_input.dataset.path;

  for(var i = 0; i<file_input.files.length; i++) {
    var file = file_input.files[i];
    let file_name = file.name;
    let ext = file.name.split('.').pop();
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var id = xhr.responseText;

            var input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", name);
            input.setAttribute("value", id);

            file_input.parentElement.append(input);
            document.getElementById(name + '_' + ext).style.filter = 'none';
        }
    };
    var reader  = new FileReader();
    reader.onloadend = function() {
      var bytes = reader.result;
      var formData = new FormData();
      formData.append('name', file_name);
      formData.append('bytes', bytes);
      formData.append('type', ext);
      xhr.send(formData);
    }
    reader.readAsDataURL(file);
  }
}

function insert_pedido(e) {
  var form = document.getElementById('form_pedido');
  var url = form.action;
  var cliente = e.dataset.cliente;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function()  {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var pedido_json = JSON.parse(xhr.responseText);
      var pedido = pedido_json['id'];
      var url_add_pedido = e.dataset.url;

      var xhr_add_pedido = new XMLHttpRequest();
      xhr_add_pedido.open("POST", url_add_pedido, true);
      xhr_add_pedido.onreadystatechange = function() {
        if (xhr_add_pedido.readyState == 4 && xhr_add_pedido.status == 200) {
          var json = JSON.parse(xhr_add_pedido.responseText);

          var tab_link = document.getElementById('novo-tab').cloneNode(true);
          tab_link.setAttribute('class', 'nav-link');
          tab_link.setAttribute('id', 'order-'+json['id']+'-tab');
          tab_link.setAttribute('href', '#order-'+json['id']);
          tab_link.setAttribute('aria-controls', 'order-'+json['id']);
          tab_link.setAttribute('aria-selected', 'false');
          var dataPedido = new Date(json['dataCompra']);
          tab_link.innerText = dataPedido.getDate() + '/' + dataPedido.getMonth() + '/' + dataPedido.getFullYear();

          var novo_tab = document.getElementById('novo').cloneNode(true);
          novo_tab.setAttribute('class', 'tab-pane fade');
          novo_tab.setAttribute('id', 'order-'+json['id']);
          novo_tab.setAttribute('aria-labelledby', 'order-'+json['id']+'-tab');
          novo_tab.removeChild(novo_tab.querySelector('#form_pedido'));
          novo_tab.removeChild(novo_tab.querySelector('#novo_pedido'));

          var novo_form = document.getElementById('form_pedido').cloneNode(true);
          var update_url = e.dataset.update;
          var delete_url = e.dataset.delete;

          novo_form.setAttribute('id', 'form_pedido_'+json['id']);
          novo_form.setAttribute('method', 'post');
          novo_form.setAttribute('action', update_url);

          novo_form.querySelector('input[name=transactionId]').value = json['transactionId'];
          novo_form.querySelector('input[name=metodoPagamento]').value = json['metodoPagamento'];
          var dataPedido = new Date(json['dataCompra']);
          novo_form.querySelector('input[name=dataCompra]').value = dataPedido.getDate() + '/' + dataPedido.getMonth() + '/' + dataPedido.getFullYear();

          var btn1 = document.createElement('button');
          btn1.setAttribute('type', 'button');
          btn1.setAttribute('class', 'btn btn-dark');
          btn1.setAttribute('data-url', update_url);
          btn1.setAttribute('data-cliente', cliente);
          btn1.setAttribute('data-pedido', json['id']);
          btn1.setAttribute('onclick', 'update_pedido(this)');
          btn1.innerHTML = '<i class="fas fa-edit"></i>';

          var btn2 = document.createElement('button');
          btn2.setAttribute('type', 'button');
          btn2.setAttribute('class', 'btn btn-dark');
          btn2.setAttribute('data-url', delete_url);
          btn2.setAttribute('data-cliente', cliente);
          btn2.setAttribute('data-pedido', json['id']);
          btn2.setAttribute('onclick', 'delete_pedido(this)');
          btn2.innerHTML = '<i class="fas fa-trash"></i>';

          novo_tab.appendChild(novo_form);
          novo_tab.appendChild(btn1);
          novo_tab.appendChild(btn2);

          document.getElementById('my_pedidos-tab').appendChild(tab_link);
          document.getElementById('my_pedidos').appendChild(novo_tab);
        }
      };
      var formData = new FormData();
      formData.append('cliente', cliente);
      formData.append('pedido', pedido);
      xhr_add_pedido.send(formData);
    }
  };
  var pedidoData = new FormData(form);
  xhr.send(pedidoData);
}

function update_pedido(e) {
  var url = e.dataset.url;
  var pedido = e.dataset.pedido;
  var cliente = e.dataset.cliente;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function()  {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var json = JSON.parse(xhr.responseText);
      var form = document.getElementById('form_pedido_'+pedido);
      if(json['erro'])
        form.querySelector('#error').style.display = 'block';
      else
        form.querySelector('#ok').style.display = 'block';
    }
  };
  var formData = new FormData();
  formData.append('cliente', cliente);
  formData.append('pedido', pedido);
  xhr.send(formData);
}

function delete_pedido(e) {
  var cliente = e.dataset.cliente;
  var pedido = e.dataset.pedido;
  var url = e.dataset.url;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function()  {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var json = JSON.parse(xhr.responseText);

      if(json['erro']) {
        //
      } else {
        var parent_tab = document.getElementById('my_pedidos-tab');
        var parent = document.getElementById('my_pedidos');
        parent_tab.removeChild(parent_tab.querySelector('#order-'+pedido+'-tab'));
        parent.removeChild(parent.querySelector('#order-'+pedido));
        var tab = parent_tab.querySelector('#novo');
        $(tab).tab('show');
      }
    }
  };
  var formData = new FormData();
  formData.append('cliente', cliente);
  formData.append('pedido', pedido);
  xhr.send(formData);
}

function insert_produto(e) {
  move_right();

  var pedido = document.getElementById('id').value;
  var produtos = document.getElementById('selectRight');
  var url = e.dataset.url;

  for (var i = 0; i < produtos.length; i = i + 1) {
      console.log('produto: ' + produtos.options[i].value);
      var xhr = new XMLHttpRequest();
      xhr.open("POST", url, true);
      xhr.onreadystatechange = function()  {
        if (xhr.readyState == 4 && xhr.status == 200) {
          var json = JSON.parse(xhr.responseText);
          console.log('json: '+json);
        }
      };
      var produto = produtos.options[i].value;
      var formData = new FormData();
      formData.append('pedido', pedido);
      formData.append('produto', produto);
      xhr.send(formData);
  }
}

function delete_produto(e) {
  move_left();

  var form = document.getElementById('form');
  var pedido = document.getElementById('id').value;
  var url = form.action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function()  {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var json = JSON.parse(xhr.responseText);
    }
  };
  var formData = new FormData(form);
  xhr.send(formData);
}

function move_right() {
  var m1 = document.getElementById('selectLeft');
  var m2 = document.getElementById('selectRight');
    m1len = m1.length ;
    for ( i=0; i<m1len ; i++){
        if (m1.options[i].selected == true ) {
            m2len = m2.length;
            var option = new Option(m1.options[i].text);
            option.value = m1.options[i].value;
            m2.options[m2len]= option;
        }
    }

    for ( i = (m1len -1); i>=0; i--){
        if (m1.options[i].selected == true ) {
            m1.options[i] = null;
        }
    }
}

function move_left() {
  var m1 = document.getElementById('selectLeft');
  var m2 = document.getElementById('selectRight');
    m2len = m2.length ;
        for ( i=0; i<m2len ; i++){
            if (m2.options[i].selected == true ) {
                m1len = m1.length;
                var option = new Option(m2.options[i].text);
                option.value = m2.options[i].value;
                m1.options[m1len]= new Option(m2.options[i].text);
            }
        }
        for ( i=(m2len-1); i>=0; i--) {
            if (m2.options[i].selected == true ) {
                m2.options[i] = null;
            }
        }
}
