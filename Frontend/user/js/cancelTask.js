
// allow execution of the refresh for the tasks in process on window loading 
window.onload = filterTasksByProcessing;
setInterval(filterTasksByProcessing, 3000);


// method that allow the request for the cancel of the task and depending on the request result show message
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
    if (resp.status === 202) {
      let messageToAlert = "The task with ID " + cancelOption + " was successfully canceled!";
      createAlert(messageToAlert, "success");
      filterTasksByProcessing();
  
    } else {
      let messageToAlert = "The task with ID " + cancelOption + " couldn't be canceled.";
      createAlert(messageToAlert, "danger");
      filterTasksByProcessing();
    }
  })
  }


  // method that alter the DOM to show the requested canceled tasks in the respective table
function filterTasksByProcessing(e) {
    console.log("started filterTasksByProcessing");

    var urlToRequest = "http://localhost:8080/LanguageDetection?status=Processing";

  fetch(urlToRequest, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
          },
          credentials:"include"
      } ).then(resp => {
     //   var responseContent = document.getElementById("create-task-response");
    if (resp.status === 200) {
        console.log("The filter is working")
        
 resp.json().then(function(data) {  
      let yourTasks = document.getElementById("tasks-processing-h2");
      yourTasks.innerHTML = "Your Processing Tasks"

      let table = document.getElementById("cancel-task-data-table");   
      table.innerHTML = ""
      let TaskIdHead = document.createElement("th");
      TaskIdHead.innerHTML = "Task ID";
       let DateHead = document.createElement("th")
       DateHead.innerHTML = "Date";
      let TextCategoryHead = document.createElement("th")
      TextCategoryHead.innerHTML = "Text Category";
      let URLHead = document.createElement("th")
      URLHead.innerHTML = "URL";      
      let TextLanguageHead = document.createElement("th")
      TextLanguageHead.innerHTML = "Text Language";
      let TaskStatusHead = document.createElement("th")
      TaskStatusHead.innerHTML = "Task Status";
      let TimeHead = document.createElement("th")
      TimeHead.innerHTML = "Time";

    table.appendChild(TaskIdHead);
    table.appendChild(DateHead);
    table.appendChild(TextCategoryHead);
    table.appendChild(URLHead);
    table.appendChild(TextLanguageHead);
    table.appendChild(TaskStatusHead);
    table.appendChild(TimeHead); 

    if(data.length === 0){
      let row = table.insertRow(0);
      row.innerHTML = "No tasks are being processed at the moment!"
    }

    for (let i = 0; i < data.length; i++) {

        let row = table.insertRow(i);
        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(0);
        let cell3 = row.insertCell(0);
        let cell4 = row.insertCell(0);
        let cell5 = row.insertCell(0);
        let cell6 = row.insertCell(0);
        let cell7 = row.insertCell(0);
        
        cell7.innerHTML = data[i].id;
        cell6.innerHTML = data[i].date;
        cell5.innerHTML = data[i].category;
        cell4.innerHTML = data[i].inputUrl;            
        let dataForLanguage = data[i].taskResult
        if( dataForLanguage == null){
          cell3.innerHTML = "  ";
        } else {
          cell3.innerHTML = data[i].taskResult.language;
        }
        cell2.innerHTML = data[i].currentStatus;
        cell1.innerHTML = data[i].timeOut;
       
    }    
    });

    } else {
        console.log("Nope!")
    }
  })
}

// method to show alert on DOM depending on the kind
function createAlert(alelertToGive, kindOfAlert){
  let elementToapend = document.getElementById("alert-area");
  elementToapend.innerHTML = ""
  let alert = document.createRange().createContextualFragment("<div class='alert alert-" + kindOfAlert + " alert-dismissible fade show' role='alert'>" + alelertToGive + "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button></div>");
  elementToapend.prepend(alert);
}
