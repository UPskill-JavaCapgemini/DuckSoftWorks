
function cancelTask(e) {
    e.preventDefault();
    var cancelOption = document.getElementById("cancel_task_input").value
   
  var stringified = JSON.stringify({ id : cancelOption})
  console.log(stringified);
  fetch('http://localhost:8080/LanguageDetection/cancel', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ id : cancelOption}),
      credentials:"include"
    }).then(resp => {
        var responseContent = document.getElementById("cancel-tasks-response");
    if (resp.status === 200) {
        responseContent.textContent = "The task " + cancelOption + " was successfully canceled!";
        console.log("task " + cancelOption + " was successfully canceled!") 
        populateCategories();
  
    } else {
        responseContent.textContent = "The task couldn't be canceled.";
        console.log("task not canceled")
    }
  })
  }