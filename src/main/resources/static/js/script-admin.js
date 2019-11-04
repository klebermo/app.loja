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
  if(document.getElementById('insert-tab').style.display === 'none') {
    var url = document.getElementById('insert').dataset.url;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');
        document.getElementById('insert').innerHTML = form_html.innerHTML;
        document.getElementById('insert-tab').style.display = "block";
        openTab("insert-tab", "insert");
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function update_data(element) {
  var id = element.dataset.id;

  if(document.getElementById("update-"+id) != null) {
    openTab("update-tab-"+id, "update-"+id);
  } else {
    var url = element.dataset.url;
    url = url + '?id=' + id;

    var tab_update = document.getElementById('update-tab').cloneNode(true);
    tab_update.setAttribute('id', 'update-tab-'+id);
    tab_update.style.display = "block";

    var panel_update = document.getElementById('update').cloneNode(true);
    panel_update.setAttribute('id', 'update-'+id);

    var ref = document.querySelector('div.tab');
    document.getElementById("tab").appendChild(tab_update);
    document.getElementById("tab_content").appendChild(panel_update, ref);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');
        document.getElementById('update-'+id).innerHTML = form_html.innerHTML;
        openTab("update-tab-"+id, "update-"+id);
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function delete_data(element) {
  var id = element.dataset.id;

  if(document.getElementById("delete-"+id) != null) {
    openTab("delete-tab-"+id, "delete-"+id);
  } else {
    var url = element.dataset.url;
    url = url + '?id=' + id;

    var tab_update = document.getElementById('delete-tab').cloneNode(true);
    tab_update.setAttribute('id', 'delete-tab-'+id);
    tab_update.style.display = "block";

    var panel_update = document.getElementById('delete').cloneNode(true);
    panel_update.setAttribute('id', 'delete-'+id);

    var ref = document.querySelector('div.tab');
    document.getElementById("tab").appendChild(tab_update);
    document.getElementById("tab_content").appendChild(panel_update, tab);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');
        document.getElementById('delete-'+id).innerHTML = form_html.innerHTML;
        openTab("delete-tab-"+id, "delete-"+id);
      }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
  }
}

function credencial_data(element) {
  var id = element.dataset.id;

  if(document.getElementById("credencial-"+id) != null) {
    openTab("credencial-tab-"+id, "credencial-"+id);
  } else {
    var url = element.dataset.url;
    url = url + '?id=' + id;

    var tab_credencial = document.getElementById('credencial-tab').cloneNode(true);
    tab_credencial.setAttribute('id', 'credencial-tab-'+id);
    tab_credencial.style.display = "block";

    var panel_credencial = document.getElementById('credencial').cloneNode(true);
    panel_credencial.setAttribute('id', 'credencial-'+id);

    var ref = document.querySelector('div.tab');
    document.getElementById("tab").appendChild(tab_credencial);
    document.getElementById("tab_content").appendChild(panel_credencial, ref);

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var myObj = this.responseText;

        parser = new DOMParser();
        var doc = parser.parseFromString(myObj, "text/html");
        var form_html = doc.getElementById('form-container');
        document.getElementById('credencial-'+id).innerHTML = form_html.innerHTML;
        openTab("credencial-tab-"+id, "credencial-"+id);
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
    document.getElementById('table-body').innerHTML = '';
    openTab('listagem-tab', 'listagem');
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
    document.getElementById('table-body').innerHTML = '';
    openTab('listagem-tab', 'listagem');
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
    document.getElementById('delete-tab-'+id).remove();
    document.getElementById('delete-'+id).remove();
    document.getElementById('table-body').innerHTML = '';
    openTab('listagem-tab', 'listagem');
    load_content();
  };
  xhr.send(formData);
}

function toggle_credencial(element) {
  var usuario = element.dataset.usuario;
  var credencial = element.getAttribute('id');
  var url = element.dataset.url;
  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
      if(element.checked == false) {
          element.checked = true;
      }
      else {
          if(element.checked == true) {
              element.checked = false;
           }
      }
    }
  };
  var formData = new FormData();
  formData.append('usuario_id', usuario);
  formData.append('credencial_id', credencial);
  xhr.send(formData);
}

function submit_setting(element) {
  var form = document.getElementById('form');
  var url = form.action;
  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var result = this.responseText;
      if(result === '')
        document.getElementById('ok').style.display = block;
      else
        document.getElementById('error').style.display = block;
    }
  };
  xhr.send(new FormData(form));
}

function cancel_insert() {
  document.getElementById('insert').innerHTML = '';
  document.getElementById('insert-tab').style.display = "none";
  openTab('listagem-tab', 'listagem');
}

function cancel_update(element) {
  var id = element.dataset.id;
  console.log('cancel update - id:'+id);
  document.getElementById('update-'+id).remove();
  document.getElementById('update-tab-'+id).remove();
  openTab('listagem-tab', 'listagem');
}

function cancel_delete(element) {
  var id = element.dataset.id;
  document.getElementById('delete-'+id).remove();
  document.getElementById('delete-tab-'+id).remove();
  openTab('listagem-tab', 'listagem');
}

function cancel_credencial(element) {
  var id = element.dataset.id;
  document.getElementById('credencial-'+id).remove();
  document.getElementById('credencial-tab-'+id).remove();
  openTab('listagem-tab', 'listagem');
}

function openTab(tab, tabName) {
  // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  document.getElementById(tabName).style.display = "block";
  document.getElementById(tab).className += " active";
}

function openIdiomaTab(element) {
  // Declare all variables
  var id = element.dataset.id;
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent_idioma");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tabidioma");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  document.getElementById(id).style.display = "block";
  document.getElementById(id+'-tab').className += " active";
}

var tab_content = document.getElementsByClassName("tab-content");

for (i = 0; i < tab_content.length; i++) {
  // Select the node that will be observed for mutations
  var targetNode = tab_content[i];

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

var tab_pane = document.getElementsByClassName("tab-pane");

for (i = 0; i < tab_pane.length; i++) {
  // Select the node that will be observed for mutations
  var targetNode = tab_pane[i];

  // Options for the observer (which mutations to observe)
  var config = { attributes: true, characterData: true, childList: true, subtree: true, attributeOldValue: true, characterDataOldValue: true };

  // Callback function to execute when mutations are observed
  var callback = function(mutationsList, observer) {
      for(var mutation of mutationsList) {
        if (mutation.type == 'childList')
            detect_editor();
        if (mutation.type == 'subtree')
            detect_editor();
      }
  };

  // Create an observer instance linked to the callback function
  var observer = new MutationObserver(callback);

  // Start observing the target node for configured mutations
  observer.observe(targetNode, config);
}

function detect_editor() {
  var editor = document.getElementsByClassName("summernote");
  for (i = 0; i < editor.length; i++) {
    //
  }
}
