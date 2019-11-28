function load_content() {
  var json = document.getElementById("table").dataset.json;

  if(json != null) {
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
          var tr = document.createElement("tr");

          var td1 = document.createElement("td");
          var data = myObj[x];
          var id = data['id'];
          td1.textContent = id;
          tr.appendChild(td1);

          for(var y in columns) {
            var td = document.createElement("td");
            var data = myObj[x];
            var str = data[columns[y]];
            td.textContent = str;
            tr.appendChild(td);
          }

          var td2 = document.createElement("td");
          var btn = document.getElementById('buttons').cloneNode(true);
          for (var i = 0; i < btn.children.length; i++)
            if(btn.children[i].hasAttribute('data-url'))
              btn.children[i].setAttribute('data-id', id);
          btn.removeAttribute('style');
          td2.appendChild(btn);
          tr.appendChild(td2);

          document.getElementById("table-body").appendChild(tr);
        }
      }
    };

    xmlhttp.open("GET", json, true);
    xmlhttp.send();
  }
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

        $('#' + target + '-tab-' + id).tab('show');
      } else {
        document.getElementById(target + '-tab').parentElement.style.display = "block";
        document.getElementById(target + '-pane').innerHTML = form_html.getElementById('container').innerHTML;

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
    load_content();

    var tab_pane = document.getElementById(target + '-pane-' + id);
    tab_pane.parentElement.removeChild(tab_pane);

    var nav_tab = document.getElementById(target + '-tab-' + id);
    nav_tab.parentElement.parentElement.removeChild(nav_tab.parentElement);
  } else {
    $('#home-tab').tab('show');

    document.getElementById('table-body').innerHTML = '';
    load_content();

    var tab_pane = document.getElementById(target + '-pane');
    tab_pane.innerHTML = '';

    var nav_tab = document.getElementById(target + '-tab');
    nav_tab.parentElement.style.display = 'none';
  }
}

function submit() {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);

  xhr.onload = function(event) {
    event.preventDefault();
  };

  xhr.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      var result = this.responseText;
      if(result === '')
        document.getElementById('ok').style.display = 'block';
      else
        document.getElementById('error').style.display = 'block';
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
