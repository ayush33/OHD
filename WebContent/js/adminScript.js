
let username = prompt('enter username','username');
let password = prompt('enter password','password');
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp.open('POST', 'http://localhost:8080/OHD/loginAdmin');
    xmlhttp.setRequestHeader('Content-Type', 'application/json');
    let obj={
    		'name' : username,
    		'password' : password
    };
    console.log(obj);
    xmlhttp.send(JSON.stringify(obj));

    xmlhttp.onreadystatechange = function(){
        
           if (xmlhttp.readyState == 4  ){
              // Javascript function JSON.parse to parse JSON data
              var jsonObj = JSON.parse(xmlhttp.responseText);
                console.log(jsonObj);
                if(jsonObj.message=='success'){
                Cookies.set('sess',jsonObj.sess);
                }
                else
                {
                    alert('Incorrect Details');
                    window.location.href='Admin.html';
                  
                }
           }
        }

function userForm(){
    let data = "<div class='form-group'>"+
    "<table >"+
    "<tr><td style='width: 100px;'><label for='name'>Id:</label></td>"+
    "<td style='width: 300px;'><input type= 'text' class='form-control' id='id'></td></tr>"+
     
    " <tr><td><label for='name'>Name:</label></td>"+
   " <td><input type= 'text' class='form-control' id='name'></td></tr>  "+
    "<tr><td><label for='email'>Email:</label></td>"+
    "<td><input type= 'text' class='form-control' id='email'></td></tr>"+
     
     "<tr><td><label for='name'>Phone:</label></td>"+
    "<td><input type= 'text' class='form-control' id='phone'></td></tr>"+
     
     "<tr><td><label for='name'>Password:</label></td>"+
    "<td><input type= 'password' class='form-control' id='password'></td></tr> "+
 "<tr>   <td><label for='name'>Type:</label></td>"+
   " <td> " +
               " <select class='form-control' id='sel1'>"+
                  "<option value='student'>Student</option>"+
                  "<option value='faculty'>Faculty</option>"+
                "</select>"+
     " </td>"+
      "</tr>"+
      " </table>"+
       "<button type='button' class='btn btn-success' onclick='submitUser()'>Submit</button>"+
    "</div>";
    document.getElementById("myid1").innerHTML=data;
    document.getElementById('myid').innerHTML = "";
}

function submitUser(){
    let id=parseInt(document.getElementById("id").value);
    let name=document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let pass = document.getElementById("password").value;
    let type = document.getElementById("sel1").value;
    var sess =  Cookies.get('sess');
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp.open("POST", "http://localhost:8080/OHD/user");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.setRequestHeader("sess",sess);
    let obj = {
      'id' : id,
      'name' :  name,
      'email' : email,
      'phone' :  phone,
      'password' :  pass,
      'type' :  type
    };
    console.log(obj);
    xmlhttp.send(JSON.stringify(obj));
    
        xmlhttp.onreadystatechange = function(){
            
               if (xmlhttp.readyState == 4  ){
                  // Javascript function JSON.parse to parse JSON data
                  var jsonObj = JSON.parse(xmlhttp.responseText);
                    console.log(jsonObj);
                    if(jsonObj.message=="success"){
                    // Cookies.set('sess',jsonObj.sess);
                    alert("User succesfully Generated");
                    document.getElementById("myid1").innerHTML="";
                    }
                    else if(jsonObj.message=="illegal")
                    {
                        alert("Illegal access");
                     window.location.href="index.html";
                    }
                    else{
                        window.location.href="Admin.html";
                    }
               }
            }
}

function viewUsers(){
    var sess =  Cookies.get('sess');
    console.log(sess);
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp.open("GET", "http://localhost:8080/OHD/user",true);
    xmlhttp.setRequestHeader("sess",sess);
    xmlhttp.send();
    xmlhttp.onreadystatechange = function(){
     
        if (xmlhttp.readyState == 4  ){
           // Javascript function JSON.parse to parse JSON data
           let jsonObj = JSON.parse(xmlhttp.responseText);
             console.log(jsonObj);
             let inHtml="";
             for(i=0;i<jsonObj.length;i++){
               inHtml+="<button type='button' class='btn btn-primary btn-block' data-toggle='collapse' data-target='#"+jsonObj[i].ID+"'>Id : "+jsonObj[i].ID+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Name : "+jsonObj[i].NAME+"</button>"+
               "<div id='"+jsonObj[i].ID+"' class='collapse'>"+jsonObj[i].EMAIL+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Phone : "+jsonObj[i].PHONE+
                "    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' class='btn btn-info' onclick=deleteUser('"+jsonObj[i].ID+"')>Delete User</button>"+
               "</div>";
             }
             document.getElementById("myid").innerHTML="";
             document.getElementById("myid1").innerHTML=inHtml;
             
        }
     }
  }

function deleteUser(x){
    var sess =  Cookies.get('sess');
    console.log(sess);
    var xmlhttp1 = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp1.open("DELETE", "http://localhost:8080/OHD/user?id="+x,true);
    xmlhttp1.setRequestHeader("sess",sess);
    xmlhttp1.send();
    xmlhttp1.onreadystatechange = function(){
     
        if (xmlhttp1.readyState == 4  ){
          let jsonObj = JSON.parse(xmlhttp1.responseText);
          console.log(jsonObj);
          if(jsonObj.message=="success"){
            // Cookies.set('sess',jsonObj.sess);
            alert("User succesfully Deleted");
            viewUsers();
            }
            else if(jsonObj.message=="illegal")
            {
                alert("Illegal access");
             window.location.href="index.html";
            }
            else{
                window.location.href="Admin.html";
            }
        }
  }
}

function reportedIssues(){
    var sess =  Cookies.get('sess');
    console.log(sess);
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp.open("GET", "http://localhost:8080/OHD/reported",true);
    xmlhttp.setRequestHeader("sess",sess);
    xmlhttp.send();
    xmlhttp.onreadystatechange = function(){
     
        if (xmlhttp.readyState == 4  ){
           // Javascript function JSON.parse to parse JSON data
           let jsonObj = JSON.parse(xmlhttp.responseText);
             console.log(jsonObj);
             let inHtml="";
             for(i=0;i<jsonObj.length;i++){
               inHtml+="<button type='button' class='btn btn-primary btn-block' data-toggle='collapse' data-target='#"+jsonObj[i].ID+"'>Id : "+jsonObj[i].ID+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Room No. : "+jsonObj[i].ROOM_ID+" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Status : "+jsonObj[i].STATUS+"</button>"+
               "<div id='"+jsonObj[i].ID+"' class='collapse'>"+jsonObj[i].DESCRIPTION+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Date : "+jsonObj[i].DATE+
                "    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                inHtml+="<button type='button' class='btn btn-info' onclick=warnFacilitator('"+jsonObj[i].ID+"')>send warning</button>"+
                "</div>";
             }
             document.getElementById("myid").innerHTML="";
             document.getElementById("myid1").innerHTML=inHtml;
             
        }
     }
}

function warnFacilitator(x){
    var sess =  Cookies.get('sess');
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp.open("POST", "http://localhost:8080/OHD/reported");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.setRequestHeader("sess",sess);
    let obj = {
      id : x,
      status :  "pending"
    };
    console.log(obj);
    xmlhttp.send(JSON.stringify(obj));
    
    
        xmlhttp.onreadystatechange = function(){
            
               if (xmlhttp.readyState == 4  ){
                  // Javascript function JSON.parse to parse JSON data
                  var jsonObj = JSON.parse(xmlhttp.responseText);
                    console.log(jsonObj);
                    if(jsonObj.message=="success")
                     reportedIssues();
                    else{
                      window.location.href="Admin.html";
                    }
                    
               }
            }
}
function logout(){
  var sess =  Cookies.get('sess');
  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
  xmlhttp.open("DELETE", "http://localhost:8080/OHD/login");
  xmlhttp.setRequestHeader("Content-Type", "application/json");
  xmlhttp.setRequestHeader("sess",sess);
  xmlhttp.send();
  
      xmlhttp.onreadystatechange = function(){
          
             if (xmlhttp.readyState == 4  ){
                // Javascript function JSON.parse to parse JSON data
                var jsonObj = JSON.parse(xmlhttp.responseText);
                  console.log(jsonObj);
                  Cookies.clear('sess');
                   window.location.href="index.html";
                    
                  
             }
          }
}
