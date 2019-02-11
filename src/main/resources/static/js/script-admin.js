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
        document.getElementById('tab-insert').removeAttribute('style');

        document.getElementById("listagem-tab").classList.remove('show');
        document.getElementById("listagem-tab").classList.remove('active');
        document.getElementById("listagem").classList.remove('show');
        document.getElementById("listagem").classList.remove('active');

        document.getElementById("insert-tab").classList.add('show');
        document.getElementById("insert-tab").classList.add('active');
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
  var id = element.dataset.id;

  if(document.getElementById('tab_content').contains(document.getElementById("credencial-"+id))) {
    document.getElementById("listagem-tab").classList.remove('show');
    document.getElementById("listagem-tab").classList.remove('active');
    document.getElementById("listagem").classList.remove('show');
    document.getElementById("listagem").classList.remove('active');

    document.getElementById("tab-credencial-"+id).classList.add('show');
    document.getElementById("tab-credencial-"+id).classList.add('active');
    document.getElementById('credencial-'+id).classList.add('show');
    document.getElementById('credencial-'+id).classList.add('active');
  } else {
    var url = element.dataset.url;
    url = url + '?id=' + id;

    var tab_credencial = document.getElementById('tab-credencial').cloneNode(true);
    tab_credencial.setAttribute('id', 'tab-credencial-'+id);
    tab_credencial.removeAttribute('style');

    var panel_credencial = document.getElementById('credencial').cloneNode(true);
    panel_credencial.setAttribute('id', 'credencial-'+id);

    document.getElementById("tabset").appendChild(tab_credencial);
    document.getElementById("tab_content").appendChild(panel_credencial);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');

        document.getElementById('credencial-'+id).innerHTML = form_html.innerHTML;

        document.getElementById("listagem-tab").classList.remove('show');
        document.getElementById("listagem-tab").classList.remove('active');
        document.getElementById("listagem").classList.remove('show');
        document.getElementById("listagem").classList.remove('active');

        document.getElementById("tab-credencial-"+id).classList.add('show');
        document.getElementById("tab-credencial-"+id).classList.add('active');
        document.getElementById('credencial-'+id).classList.add('show');
        document.getElementById('credencial-'+id).classList.add('active');
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function submit_insert() {
  var formData = new FormData(document.getElementById("form"));
  var url = document.getElementById("form").action;

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);
  xhr.onload = function(event) {
    event.preventDefault();
    document.getElementById('insert').innerHTML = '';
    document.getElementById('tab-insert').setAttribute('style', 'display: none;');

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

/*function toggle_credencial(element) {
  var usuario_id = element.dataset.usuario;
  var credencial_id = element.getAttribute('id');
  var xhr = new XMLHttpRequest();
  xhr.open("POST", url);
  xhr.onload = function(event) {
    if(element.checked == false) {
        element.checked = true;
    }
    else {
        if(element.checked == true) {
            element.checked = false;
         }
    }
  };
  FormData formData = new FormData();
  formData.append('usuario_id', usuario_id);
  formData.append('credencial_id', credencial_id);
  xhr.send(formData);
}*/

function submit_setting(element) {
  var form = document.getElementById('form');
  var url = form.action;
  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var result = this.responseText;
      if(result === '')
        document.getElementById('sucess').removeAttribute('style');
      else
        document.getElementById('error').removeAttribute('style');
    }
  };
  xhr.send(new FormData(form));
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

function cancel_credencial(element) {
  var id = element.dataset.id;

  document.getElementById('credencial-'+id).remove();
  document.getElementById('tab-credencial-'+id).remove();

  document.getElementById("listagem-tab").classList.add('show');
  document.getElementById("listagem-tab").classList.add('active');
  document.getElementById("listagem").classList.add('show');
  document.getElementById("listagem").classList.add('active');
}

var tab_panel = document.getElementsByClassName("tab-content");

for (i = 0; i < tab_panel.length; i++) {
  // Select the node that will be observed for mutations
  var targetNode = tab_panel[i];

  // Options for the observer (which mutations to observe)
  var config = { attributes: true, childList: true, subtree: true };

  // Callback function to execute when mutations are observed
  var callback = function(mutationsList, observer) {
      for(var mutation of mutationsList) {
        if (mutation.type == 'childList')
            detect_uploader();
        else if (mutation.type == 'attributes')
            detect_uploader();
        else
          detect_uploader();
      }
  };

  // Create an observer instance linked to the callback function
  var observer = new MutationObserver(callback);

  // Start observing the target node for configured mutations
  observer.observe(targetNode, config);
}

/*for (i = 0; i < tab_panel.length; i++)
  tab_panel[i].addEventListener("DOMSubtreeModified", detect_uploader, false);*/

function detect_uploader() {
  var image_uploader = document.getElementsByClassName("image-uploader");
  for (i = 0; i < image_uploader.length; i++)
    image_uploader[i].addEventListener("change", image_upload, false);

  var file_uploader = document.getElementsByClassName("file-uploader");
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
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var id = xhr.responseText;
            var input = document.createElement("input");
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
      var formData = new FormData();
      formData.append('bytes', bytes);
      formData.append('type', ext);
      xhr.send(formData);
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
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var id = xhr.responseText;
            var input = document.createElement("input");
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
      var formData = new FormData();
      formData.append('bytes', bytes);
      formData.append('type', ext);
      xhr.send(formData);
    }
    reader.readAsDataURL(file);
  }
}
