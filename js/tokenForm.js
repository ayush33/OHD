function tokenForm(){
    let data = "<div class='myButton3 dropdown'><button class='btn btn-default dropdown-toggle' style='width: 300px;' type='button' id='menu1' data-toggle='dropdown'>Choose Block<span class='caret'></span></button>"+
    "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>"+
      "<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>HTML</a></li>"+
      "<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>CSS</a></li>"+
      "<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>JavaScript</a></li>"+
      "<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>About Us</a></li>"+
   "</ul>"+
  "</div>"+
  "<div class='myButton1 dropdown'>"+
    "<button class='btn btn-default dropdown-toggle' style='width: 300px;' type='button' id='menu1' data-toggle='dropdown'>Choose Place"+
    "<span class='caret'></span></button>"+
    "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>"+
     " <li role='presentation'><a role='menuitem' tabindex='-1' href='#'>HTML</a></li>"+
      "<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>CSS</a></li>"+
     " <li role='presentation'><a role='menuitem' tabindex='-1' href='#'>JavaScript</a></li>"+
      "<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>About Us</a></li>"+
    "</ul>"+
  "</div>"+
"  <div class='myButton2 dropdown'>"+
    "<button class='btn btn-default dropdown-toggle' style='width: 300px;' type='button' id='menu1' data-toggle='dropdown'>Choose Problem Type"+
    "<span class='caret'></span></button>"+
    "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>"+
      "<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>HTML</a></li>"+
     " <li role='presentation'><a role='menuitem' tabindex='-1' href='#'>CSS</a></li>"+
  " <li role='presentation'><a role='menuitem' tabindex='-1' href='#'>JavaScript</a></li>"+
      "<li role='presentation'><a role='menuitem' tabindex='-1' href='#'>About Us</a></li>"+
" </ul>"+
  "</div>"+
"<div class='container'>"+

"<label for='description comment' class='Description'>Description:</label>"+
" <div class='textarea form-group'>"+
  "<textarea class=' form-control' rows='7' id='comment'></textarea>"+
" <button type='button' class='Button btn btn-warning' style = 'width: 290px;' >Submit</button>"+
  "</div>";
  document.getElementById("myid").innerHTML=data;
}