function load_content() {
  var json = document.getElementById("table").dataset.json;

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

function insert_data() {
  if(document.getElementById('insert-tab').parentNode.style.display === 'none') {
    var url = document.getElementById('insert').dataset.url;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');

        document.getElementById('insert').innerHTML = form_html.innerHTML;
        document.getElementById('insert-tab').parentNode.removeAttribute('style');

        document.getElementById("listagem-tab").classList.remove('show');
        document.getElementById("listagem-tab").classList.remove('active');
        document.getElementById("listagem").classList.remove('show');
        document.getElementById("listagem").classList.remove('active');

        document.getElementById("tab-insert").classList.add('show');
        document.getElementById("tab-insert").classList.add('active');
        document.getElementById('insert').classList.add('show');
        document.getElementById('insert').classList.add('active');
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function update_data(element) {
  var id = element.dataset.id;

  if(document.getElementById('tab_content').contains(document.getElementById("update-"+id))) {
    document.getElementById("listagem-tab").classList.remove('show');
    document.getElementById("listagem-tab").classList.remove('active');
    document.getElementById("listagem").classList.remove('show');
    document.getElementById("listagem").classList.remove('active');

    document.getElementById("tab-update-"+id).classList.add('show');
    document.getElementById("tab-update-"+id).classList.add('active');
    document.getElementById('update-'+id).classList.add('show');
    document.getElementById('update-'+id).classList.add('active');
  } else {
    var url = element.dataset.url;
    url = url + '?id=' + id;

    var tab_update = document.getElementById('tab-update').cloneNode(true);
    tab_update.setAttribute('id', 'tab-update-'+id);
    tab_update.removeAttribute('style');

    var panel_update = document.getElementById('update').cloneNode(true);
    panel_update.setAttribute('id', 'update-'+id);

    document.getElementById("tabset").appendChild(tab_update);
    document.getElementById("tab_content").appendChild(panel_update);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');

        document.getElementById('update-'+id).innerHTML = form_html.innerHTML;

        document.getElementById("listagem-tab").classList.remove('show');
        document.getElementById("listagem-tab").classList.remove('active');
        document.getElementById("listagem").classList.remove('show');
        document.getElementById("listagem").classList.remove('active');

        document.getElementById("tab-update-"+id).classList.add('show');
        document.getElementById("tab-update-"+id).classList.add('active');
        document.getElementById('update-'+id).classList.add('show');
        document.getElementById('update-'+id).classList.add('active');
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function delete_data(element) {
  var id = element.dataset.id;

  if(document.getElementById('tab_content').contains(document.getElementById("delete-"+id))) {
    document.getElementById("listagem-tab").classList.remove('show');
    document.getElementById("listagem-tab").classList.remove('active');
    document.getElementById("listagem").classList.remove('show');
    document.getElementById("listagem").classList.remove('active');

    document.getElementById("tab-delete-"+id).classList.add('show');
    document.getElementById("tab-delete-"+id).classList.add('active');
    document.getElementById('delete-'+id).classList.add('show');
    document.getElementById('delete-'+id).classList.add('active');
  } else {
    var url = element.dataset.url;
    url = url + '?id=' + id;

    var tab_update = document.getElementById('tab-delete').cloneNode(true);
    tab_update.setAttribute('id', 'tab-delete-'+id);
    tab_update.removeAttribute('style');

    var panel_update = document.getElementById('delete').cloneNode(true);
    panel_update.setAttribute('id', 'delete-'+id);

    document.getElementById("tabset").appendChild(tab_update);
    document.getElementById("tab_content").appendChild(panel_update);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');

        document.getElementById('delete-'+id).innerHTML = form_html.innerHTML;

        document.getElementById("listagem-tab").classList.remove('show');
        document.getElementById("listagem-tab").classList.remove('active');
        document.getElementById("listagem").classList.remove('show');
        document.getElementById("listagem").classList.remove('active');

        document.getElementById("tab-delete-"+id).classList.add('show');
        document.getElementById("tab-delete-"+id).classList.add('active');
        document.getElementById('delete-'+id).classList.add('show');
        document.getElementById('delete-'+id).classList.add('active');
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function credencial_data(element) {
  //
}

function toggle_credencial() {
  //
}

function cancel_credencial() {
  //
}

function submit_insert() {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);
  xhr.onload = function(event) {
    event.preventDefault();
    document.getElementById('insert').innerHTML = '';

    document.getElementById("listagem-tab").classList.add('show');
    document.getElementById("listagem-tab").classList.add('active');
    document.getElementById("listagem").classList.add('show');
    document.getElementById("listagem").classList.add('active');

    document.getElementById('table-body').innerHTML = '';
    load_content();
  };
  xhr.send(formData);
}

function submit_update(element) {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);
  xhr.onload = function(event){
    event.preventDefault();
    var id = document.getElementById('id').value;
    document.getElementById('tab-update-'+id).remove();
    document.getElementById('update-'+id).remove();

    document.getElementById("listagem-tab").classList.add('show');
    document.getElementById("listagem-tab").classList.add('active');
    document.getElementById("listagem").classList.add('show');
    document.getElementById("listagem").classList.add('active');

    document.getElementById('table-body').innerHTML = '';
    load_content();
  };
  xhr.send(formData);
}

function submit_delete(element) {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("DELETE", url);
  xhr.onload = function(event) {
    var id = document.getElementById('id').value;
    document.getElementById('tab-delete-'+id).remove();
    document.getElementById('delete-'+id).remove();

    document.getElementById("listagem-tab").classList.add('show');
    document.getElementById("listagem-tab").classList.add('active');
    document.getElementById("listagem").classList.add('show');
    document.getElementById("listagem").classList.add('active');

    document.getElementById('table-body').innerHTML = '';
    load_content();
  };
  xhr.send(formData);
}

function cancel_insert() {
  document.getElementById('insert').innerHTML = '';
  document.getElementById('insert-tab').parentNode.setAttribute('style', 'display: none;');

  document.getElementById("listagem-tab").classList.add('show');
  document.getElementById("listagem-tab").classList.add('active');
  document.getElementById("listagem").classList.add('show');
  document.getElementById("listagem").classList.add('active');
}

function cancel_update(element) {
  var id = element.dataset.id;

  document.getElementById('update-'+id).remove();
  document.getElementById('tab-update-'+id).remove();

  document.getElementById("listagem-tab").classList.add('show');
  document.getElementById("listagem-tab").classList.add('active');
  document.getElementById("listagem").classList.add('show');
  document.getElementById("listagem").classList.add('active');
}

function cancel_delete(element) {
  var id = element.dataset.id;

  document.getElementById('delete-'+id).remove();
  document.getElementById('tab-delete-'+id).remove();

  document.getElementById("listagem-tab").classList.add('show');
  document.getElementById("listagem-tab").classList.add('active');
  document.getElementById("listagem").classList.add('show');
  document.getElementById("listagem").classList.add('active');
}

var tab_panel = document.getElementsByClassName("tab-panel");

for (i = 0; i < tab_panel.length; i++)
  tab_panel[i].addEventListener("DOMSubtreeModified", detect_uploader, false);

function detect_uploader() {
  var image_uploader = this.getElementsByClassName("image-uploader");
  for (i = 0; i < image_uploader.length; i++)
    image_uploader[i].addEventListener("change", image_upload, false);

  var file_uploader = this.getElementsByClassName("file-uploader");
  for (i = 0; i < file_uploader.length; i++)
    file_uploader[i].addEventListener("change", file_upload, false);
}

function image_upload() {
  var file_input = this;
  var name = file_input.getAttribute("id");
  var url = file_input.dataset.url;

  for(var i = 0; i<this.files.length; i++) {
    var file =  this.files[i];
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var id = xhr.responseText;
            var input = createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", name);
            input.setAttribute("value", id);
            file_input.after(input);
        }
    };
    var reader  = new FileReader();
    reader.onloadend = function() {
      var bytes = reader.result;
      var ext = file.name.split(".").pop();
      xhr.send("bytes="+window.btoa(bytes)+"&type="+ext);
    }
    reader.readAsBinaryString(file);
  }
}

function file_upload() {
  var file_input = this;
  var name = file_input.getAttribute("id");
  var url = file_input.dataset.url;

  for(var i = 0; i<this.files.length; i++) {
    var file = this.files[i];
    // This code is only for demo ...
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var id = xhr.responseText;
            var input = createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", name);
            input.setAttribute("value", id);
            file_input.after(input);
        }
    };
    var reader  = new FileReader();
    reader.onloadend = function() {
      var bytes = reader.result;
      var ext = file.name.split(".").pop();
      xhr.send("bytes="+bytes+"&type="+ext);
    }
    reader.readAsDataURL(file);
  }
}
