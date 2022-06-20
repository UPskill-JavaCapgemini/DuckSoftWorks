
function logIn(e) {
    e.preventDefault();
    console.log("hi there");
    var link = document.getElementById("link").value
    var category = document.getElementById("category-dropdown")
    var categoryOption = category.options[category.selectedIndex].value
    var timeLimit = document.getElementById("timeLimit")
    var timeLimitOption = timeLimit.options[timeLimit.selectedIndex].value

  var stringified = JSON.stringify({ url : link, category : categoryOption, timeOut : timeLimitOption })
  console.log(stringified);
  fetch('http://localhost:8080/LanguageDetection', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ url : link, category : categoryOption, timeOut: timeLimitOption })
    }).then(resp => {
        var responseContent = document.getElementById("create-task-response");
    if (resp.status === 201) {
        responseContent.textContent = "The task was successfully created!";
        console.log("Task was successfully created!")
    } else {
        responseContent.textContent = "The task couldn't be created!";
        console.log("Task was successfully created!")
    }
})
}