function login(){
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp.open("POST", "http://localhost:8080/OHD/login");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    let cid=document.getElementsByName("id")[0].value;
    let pass=document.getElementsByName("password")[0].value;
    let obj={
    		id : cid,
    		password : pass
    };
    console.log(obj);
    xmlhttp.send(JSON.stringify(obj));

    xmlhttp.onreadystatechange = function(){
        
           if (xmlhttp.readyState == 4  ){
              // Javascript function JSON.parse to parse JSON data
              var jsonObj = JSON.parse(xmlhttp.responseText);
                console.log(jsonObj);
                if(jsonObj.message=="success"){
                document.cookie="sess="+jsonObj.sess;
                window.location.href="User.html";
                }
                else{
                    
                }

              // jsonObj variable now contains the data structure and can
              // be accessed as jsonObj.name and jsonObj.country.
            //   document.getElementById("Name").innerHTML = jsonObj.name;
            //   document.getElementById("Country").innerHTML = jsonObj.country;
           }
        }
}