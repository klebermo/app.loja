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

      var buttons = [];
      var foo2 = document.getElementById('buttons');
      for (var i = 0; i < foo2.children.length; i++)
        if(foo2.children[i].hasAttribute('class'))
          buttons.push(foo2.children[i]);

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

        for(var i=0; i<buttons.length; i++) {
          var b = buttons[i];

          var btn = document.createElement("a");
          btn.setAttribute("href", "#");
          btn.setAttribute("class", "myButton");
          btn.setAttribute("data-id", id);
          btn.setAttribute("onclick", b.dataset.action+"_data("+id+");");
          var img = document.createElement("img");
          img.setAttribute("src", b.dataset.url);
          img.setAttribute("width", "16px");
          img.setAttribute("height", "16px");
          img.setAttribute("alt", b.dataset.action);
          btn.appendChild(img);

          td2.appendChild(btn);
        }

        tr.appendChild(td2);
        document.getElementById("table-body").appendChild(tr);
      }
    }
  };

  xmlhttp.open("GET", json, true);
  xmlhttp.send();
}

function insert_data() {
  if(document.getElementById('tab-insert').style.display === 'none') {
    var url = document.getElementById('insert').dataset.url;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');

        document.getElementById('tab-insert').removeAttribute('style');
        document.getElementById('label-insert').removeAttribute('style');
        document.getElementById('insert').removeAttribute('style');

        document.getElementById('form-insert').innerHTML = form_html.innerHTML;

        document.getElementById('tab-insert').checked = true;
        document.getElementById('tab-listagem').checked = false;
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function update_data(data_id) {
  if(document.getElementById('tab-panel').contains(document.getElementById("update-"+data_id))) {
    document.getElementById('tab-update-'+data_id).setAttribute('checked', 'checked');
    document.getElementById('tab-listagem').removeAttribute('checked');
  } else {
    var url = document.getElementById('update').dataset.url;
    url = url + '?id=' + data_id;

    var tab_update = document.getElementById('tab-update').cloneNode(true);
    var label_update = document.getElementById('label-update').cloneNode(true);
    var panel_update = document.getElementById('update').cloneNode(true);

    tab_update.setAttribute('id', 'tab-update-'+data_id);
    tab_update.setAttribute('aria-controls', 'update-'+data_id);
    label_update.setAttribute('id', 'label-update-'+data_id);
    label_update.setAttribute('for', 'tab-update-'+data_id);
    panel_update.setAttribute('id', 'update-'+data_id);

    var parent = document.getElementById("button").parentNode;
    var child = document.getElementById("button");
    parent.insertBefore(tab_update, child);
    parent.insertBefore(label_update, child);
    document.getElementById("tab-panel").appendChild(panel_update);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');

        panel_update.innerHTML = form_html.innerHTML;

        tab_update.removeAttribute('style');
        label_update.removeAttribute('style');
        panel_update.removeAttribute('style');

        tab_update.checked = true;
        document.getElementById('tab-listagem').checked = false;
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function delete_data(data_id) {
  if(document.getElementById('tab-panel').contains(document.getElementById("delete-"+data_id))) {
    document.getElementById('tab-delete-'+data_id).setAttribute('checked', 'checked');
    document.getElementById('tab-listagem').removeAttribute('checked');
  } else {
    var url = document.getElementById('delete').dataset.url;
    url = url + '?id=' + data_id;

    var tab_delete = document.getElementById('tab-delete').cloneNode(true);
    var label_delete = document.getElementById('label-delete').cloneNode(true);
    var panel_delete = document.getElementById('delete').cloneNode(true);

    tab_delete.setAttribute('id', 'tab-delete-'+data_id);
    tab_delete.setAttribute('aria-controls', 'delete-'+data_id);
    label_delete.setAttribute('id', 'label-delete-'+data_id);
    label_delete.setAttribute('for', 'tab-delete-'+data_id);
    panel_delete.setAttribute('id', 'delete-'+data_id);

    var parent = document.getElementById("button").parentNode;
    var child = document.getElementById("button");
    parent.insertBefore(tab_delete, child);
    parent.insertBefore(label_delete, child);
    document.getElementById("tab-panel").appendChild(panel_delete);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');

        panel_delete.innerHTML = form_html.innerHTML;

        tab_delete.removeAttribute('style');
        label_delete.removeAttribute('style');
        panel_delete.removeAttribute('style');

        tab_delete.checked = true;
        document.getElementById('tab-listagem').checked = false;
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function credencial_data(data_id) {
  if(document.getElementById('tab-panel').contains(document.getElementById("credencial-"+data_id))) {
    document.getElementById('tab-credencial-'+data_id).setAttribute('checked', 'checked');
    document.getElementById('tab-listagem').removeAttribute('checked');
  } else {
    var url = document.getElementById('credencial').dataset.url;
    url = url + '?id=' + data_id;

    var tab_credencial = document.getElementById('tab-credencial').cloneNode(true);
    var label_credencial = document.getElementById('label-credencial').cloneNode(true);
    var panel_credencial = document.getElementById('credencial').cloneNode(true);

    tab_credencial.setAttribute('id', 'tab-credencial-'+data_id);
    tab_credencial.setAttribute('aria-controls', 'credencial-'+data_id);
    label_credencial.setAttribute('id', 'label-credencial-'+data_id);
    label_credencial.setAttribute('for', 'tab-credencial-'+data_id);
    panel_credencial.setAttribute('id', 'credencial-'+data_id);

    var parent = document.getElementById("button").parentNode;
    var child = document.getElementById("button");
    parent.insertBefore(tab_credencial, child);
    parent.insertBefore(label_credencial, child);
    document.getElementById("tab-panel").appendChild(panel_credencial);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');

        panel_credencial.innerHTML = form_html.innerHTML;

        tab_credencial.removeAttribute('style');
        label_credencial.removeAttribute('style');
        panel_credencial.removeAttribute('style');

        tab_credencial.checked = true;
        document.getElementById('tab-listagem').checked = false;
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function toggle_credencial() {
  //
}

function cancel_credencial() {
  var id = document.getElementById('id').value;

  var parent = document.getElementById('tabset');
  parent.removeChild(document.getElementById('tab-credencial-'+id));
  parent.removeChild(document.getElementById('label-credencial-'+id));
  parent = document.getElementById('tab-panel');
  parent.removeChild(document.getElementById('credencial-'+id));

  document.getElementById('tab-credencial').checked = false;
  document.getElementById('tab-listagem').checked = true;
}

function submit_insert() {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);
  xhr.onload = function(event) {
    document.getElementById('tab-insert').setAttribute('style', 'display: none;');
    document.getElementById('label-insert').setAttribute('style', 'display: none;');
    document.getElementById('insert').setAttribute('style', 'display: none;');

    document.getElementById('form-insert').innerHTML = '';

    document.getElementById('tab-insert').checked = false;
    document.getElementById('tab-listagem').checked = true;
    document.getElementById('table-body').innerHTML = '';
    load_content();
  };
  xhr.send(formData);
}

function cancel_insert() {
  document.getElementById('tab-insert').setAttribute('style', 'display: none;');
  document.getElementById('label-insert').setAttribute('style', 'display: none;');
  document.getElementById('insert').setAttribute('style', 'display: none;');

  document.getElementById('form-insert').innerHTML = '';

  document.getElementById('tab-insert').checked = false;
  document.getElementById('tab-listagem').checked = true;
}

function submit_update() {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);
  xhr.onload = function(event){
    event.preventDefault();
    var id = document.getElementById('id').value;

    var parent = document.getElementById('tabset');
    parent.removeChild(document.getElementById('tab-update-'+id));
    parent.removeChild(document.getElementById('label-update-'+id));
    parent = document.getElementById('tab-panel');
    parent.removeChild(document.getElementById('update-'+id));

    document.getElementById('tab-update').checked = false;
    document.getElementById('tab-listagem').checked = true;
    document.getElementById('table-body').innerHTML = '';
    load_content();
  };
  xhr.send(formData);
}

function cancel_update() {
  var id = document.getElementById('id').value;

  var parent = document.getElementById('tabset');
  parent.removeChild(document.getElementById('tab-update-'+id));
  parent.removeChild(document.getElementById('label-update-'+id));
  parent = document.getElementById('tab-panel');
  parent.removeChild(document.getElementById('update-'+id));

  document.getElementById('tab-update').checked = false;
  document.getElementById('tab-listagem').checked = true;
}

function submit_delete() {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("DELETE", url);
  xhr.onload = function(event) {
    var id = document.getElementById('id').value;

    var parent = document.getElementById('tabset');
    parent.removeChild(document.getElementById('tab-delete-'+id));
    parent.removeChild(document.getElementById('label-delete-'+id));
    parent = document.getElementById('tab-panel');
    parent.removeChild(document.getElementById('delete-'+id));

    document.getElementById('tab-delete').checked = false;
    document.getElementById('tab-listagem').checked = true;
    document.getElementById('table-body').innerHTML = '';
    load_content();
  };
  xhr.send(formData);
}

function cancel_delete() {
  var id = document.getElementById('id').value;

  var parent = document.getElementById('tabset');
  parent.removeChild(document.getElementById('tab-delete-'+id));
  parent.removeChild(document.getElementById('label-delete-'+id));
  parent = document.getElementById('tab-panel');
  parent.removeChild(document.getElementById('delete-'+id));

  document.getElementById('tab-delete').checked = false;
  document.getElementById('tab-listagem').checked = true;
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
      xhr.send("bytes="+bytes+"&type="+ext);
    }
    reader.readAsDataURL(file);
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
