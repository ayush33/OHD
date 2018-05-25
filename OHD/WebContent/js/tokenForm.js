function tokenForm(){
    let data =  "<div class='form-group'>"+
    "<select class='form-control' id='block' onchange='getRooms(this.value)'>"+
      "<option>Select Block</option>"+
    "</select>"+
    
    "<select class='form-control' id='room'>"+
      "<option>Select Room</option>"+
    "</select>"+
    
   " <select class='form-control' id='facility'>"+
      "<option>Select Facility</option>"+
    "</select>"+
    "<br>"+
    "<label for='description'>Description:</label>"+
    "<textarea class='form-control' rows='5' id='description'></textarea>"+
    "<br>"+
    "<button type='button' class='btn btn-success' onclick='submitToken()'>Submit</button>"+
  "</div>";
  document.getElementById("myid1").innerHTML="";
 document.getElementById('myid').innerHTML = data;
 var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
 xmlhttp.open("GET", "http://localhost:8080/OHD/blocks",true);
 xmlhttp.send();
 xmlhttp.onreadystatechange = function(){
  
     if (xmlhttp.readyState == 4  ){
        // Javascript function JSON.parse to parse JSON data
        let jsonObj = JSON.parse(xmlhttp.responseText);
          console.log(jsonObj);
          let inHtml="<option>Select Block</option>";
          for(i=0;i<jsonObj.length;i++){
            inHtml+="<option value="+jsonObj[i].ID+">"+jsonObj[i].NAME+"</option>";
          }
          document.getElementById("block").innerHTML=inHtml;
          
     }
  }

  var xmlhttp1 = new XMLHttpRequest();   // new HttpRequest instance 
  xmlhttp1.open("GET", "http://localhost:8080/OHD/facilities",true);
  xmlhttp1.send();
  xmlhttp1.onreadystatechange = function(){
   
      if (xmlhttp1.readyState == 4  ){
         // Javascript function JSON.parse to parse JSON data
         let jsonObj = JSON.parse(xmlhttp1.responseText);
           console.log(jsonObj);
           let inHtml="<option>Select Facility</option>";
           for(i=0;i<jsonObj.length;i++){
             inHtml+="<option value="+jsonObj[i].ID+">"+jsonObj[i].NAME+"</option>";
           }
           document.getElementById("facility").innerHTML=inHtml;
           
      }
   }
}

function getRooms(x){
  var xmlhttp1 = new XMLHttpRequest();   // new HttpRequest instance 
  xmlhttp1.open("GET", "http://localhost:8080/OHD/rooms?id="+x,true);
  xmlhttp1.send();
  xmlhttp1.onreadystatechange = function(){
   
      if (xmlhttp1.readyState == 4  ){
        let jsonObj = JSON.parse(xmlhttp1.responseText);
        console.log(jsonObj);
        let inHtml="<option>Select Room</option>";
        for(i=0;i<jsonObj.length;i++){
          inHtml+="<option value="+jsonObj[i].ID+">"+jsonObj[i].ID+"</option>";
        }
        document.getElementById("room").innerHTML=inHtml;
      }
}
}

function submitToken(){
  let roomid=document.getElementById("room").value;
  let facilityid=parseInt(document.getElementById("facility").value);
  let description = document.getElementById("description").value;
  var sess =  Cookies.get('sess');
  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
  xmlhttp.open("POST", "http://localhost:8080/OHD/token");
  xmlhttp.setRequestHeader("Content-Type", "application/json");
  xmlhttp.setRequestHeader("sess",sess);
  let obj = {
    facility_id : facilityid,
    room_id :  roomid,
    description : description
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
                  alert("Token succesfully generated");
                 window.location.href="User.html";
                  }
                  else
                  {
                   window.location.href="index.html";
                    
                  }
             }
          }
}

function viewToken(){
  var sess =  Cookies.get('sess');
  console.log(sess);
  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
  xmlhttp.open("GET", "http://localhost:8080/OHD/token",true);
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
              if(jsonObj[i].STATUS=="pending"){
              inHtml+="<button type='button' class='btn btn-info' onclick=reportIssue('"+jsonObj[i].ID+"') disabled>Report Issue</button>"+
             "</div>";}
             else{
              inHtml+="<button type='button' class='btn btn-info' onclick=reportIssue('"+jsonObj[i].ID+"')>Report Issue</button>"+
              "</div>";
             }
           }
           document.getElementById("myid").innerHTML="";
           document.getElementById("myid1").innerHTML=inHtml;
           
      }
   }
}


// "<button type='button' class='btn btn-primary btn-block' data-toggle='collapse' data-target='#demo'>Id : "+jsonObj[i].ID+"  Room No. : "+jsonObj[i].ROOM_ID+"  Status : "+jsonObj[i].STATUS+"</button>"+
//  "<div id='demo' class='collapse'>"+jsonObj[i].DESCRIPTION+"  Date : "+jsonObj[i].DATE+
//   "<button type='button' class='btn btn-info'>Button</button>"+
//  "</div>"

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

function reportIssue(x){
  var sess =  Cookies.get('sess');
  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
  xmlhttp.open("PUT", "http://localhost:8080/OHD/token");
  xmlhttp.setRequestHeader("Content-Type", "application/json");
  xmlhttp.setRequestHeader("sess",sess);
  let obj = {
    id : x,
    status :  "reported"
  };
  console.log(obj);
  xmlhttp.send(JSON.stringify(obj));
  
  
      xmlhttp.onreadystatechange = function(){
          
             if (xmlhttp.readyState == 4  ){
                // Javascript function JSON.parse to parse JSON data
                var jsonObj = JSON.parse(xmlhttp.responseText);
                  console.log(jsonObj);
                  if(jsonObj.message=="success")
                   window.location.href="User.html";
                  else{
                    window.location.href="index.html";
                  }
                  
             }
          }
}

function resolveIssue(x){
  var sess =  Cookies.get('sess');
  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
  xmlhttp.open("PUT", "http://localhost:8080/OHD/token");
  xmlhttp.setRequestHeader("Content-Type", "application/json");
  xmlhttp.setRequestHeader("sess",sess);
  let obj = {
    id : x,
    status :  "resolved"
  };
  console.log(obj);
  xmlhttp.send(JSON.stringify(obj));
  
  
      xmlhttp.onreadystatechange = function(){
          
             if (xmlhttp.readyState == 4  ){
                // Javascript function JSON.parse to parse JSON data
                var jsonObj = JSON.parse(xmlhttp.responseText);
                  console.log(jsonObj);
                  if(jsonObj.message=="success")
                   window.location.href="Facilitator.html";
                  else{
                    window.location.href="index.html";
                  }
                  
             }
          }
}