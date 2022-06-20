
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
      body: JSON.stringify({ username : userInput, password : passwordInput })
    }).then(resp => {
       // var responseContent = document.getElementById("create-task-response");
    if (resp.status === 200) {
        console.log("Login OK")
        console.log(resp.body)
    } else {
         console.log("Login NOK")
    }
})
}