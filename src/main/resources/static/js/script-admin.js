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

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      var myObj = this.responseText;
      var parser = new DOMParser();
      var form_html = parser.parseFromString(myObj, "text/html");

      if(id) {
        var nav_tab = document.getElementById(target).cloneNode(true);
        var tab_pane = document.getElementById(target + '-tab').cloneNode(true);

        nav_tab.setAttribute('id', target + '-tab-' + id);
        nav_tab.setAttribute('href', target + '-' + id);
        nav_tab.setAttribute('aria-controls', target + '-' + id);
        tab_pane.setAttribute('id', target + '-' + id);
        tab_pane.setAttribute('aria-labelledby', target + '-tab-' + id);

        nav_tab.parentElement.style.display = "block";
        tab_pane.innerHTML = form_html.getElementById('container').innerHTML;

        $(nav_tab).tab('show');
      } else {
        document.getElementById(target + '-tab').parentElement.style.display = "block";
        document.getElementById(target).innerHTML = form_html.getElementById('container').innerHTML;

        $('#' + target + '-tab').tab('show');
      }
    }
  };
  xmlhttp.open("GET", url, true);
  xmlhttp.send();
}

function close_tab() {
  //
}

function submit() {
  //
}

function select(element) {
  //
}

function upload(element) {
  //
}
