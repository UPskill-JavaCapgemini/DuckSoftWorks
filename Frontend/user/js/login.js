
function logIn(e) {
    e.preventDefault();
    console.log("hi there login");
    var userInput = document.getElementById("userInput").value
    var passwordInput = document.getElementById("passwordInput").value

  var stringified = JSON.stringify({ username : userInput, password : passwordInput })

  console.log(stringified);

  fetch('http://localhost:8080/LanguageDetection/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username : userInput, password : passwordInput }),
      credentials:"include"
    }).then(resp => {
       // var responseContent = document.getElementById("create-task-response");
    if (resp.status === 200) {
        console.log("Login OK")
        resp.json().then(function(data) {  
            let role = data.roles;
            if(role == "ROLE_USER"){
                window.open("http://localhost:5500/user/home_user.html", "_self")
            } else{
                window.open("http://localhost:5500/admin/home_admin.html", "_self")
            }
          });
    } else {
        var responseContent = document.getElementById("create-logIn-response");
        responseContent.textContent = "FAIL TO LOGIN, PLEASE CHECK YOUR CREDENTIALS!";
         console.log("Login NOK")
    }
})
}