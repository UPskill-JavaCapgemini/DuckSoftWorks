window.onload = findAllBlackListItems;

function createNewBlackListItem(e) {
    e.preventDefault();
    console.log("createBlackListInitiated");
    var blackListInput = document.getElementById("BlackListItemInput").value

  var stringified = JSON.stringify({ url : blackListInput})
  console.log(stringified);
  fetch('http://localhost:8080/BlackList', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ url : blackListInput}),
      credentials:"include"
    }).then(resp => {
    if (resp.status === 201) {
        let messageToAlert = "The BlackList item " + blackListInput + " was successfully created!";
        createAlert(messageToAlert, "success");
        findAllBlackListItems(e)

    } else {
        let messageToAlert = "The BlackList item " + blackListInput + " couldn't be created! Invalid format or already blacklisted.";
        createAlert(messageToAlert, "danger");
    }
})
}


function findAllBlackListItems(e) {
    e.preventDefault();
    console.log("hi there");
    let blackListTable = document.getElementById('html-blacklist-table');
  fetch( 'http://localhost:8080/BlackList', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
          },
          credentials:"include"
      } ).then(resp => {

    if (resp.status === 200) {
        console.log("The blackList returned")
        
        resp.json().then(function(data) {  
          blackListTable.innerHTML = ""

          if(data.length === 0){
            let row = blackListTable.insertRow(0);
            row.innerHTML = "The Blacklist is empty!"
          }
      
        for (let i = 0; i < data.length; i++) {

            let row = blackListTable.insertRow(i);
            let cell1 = row.insertCell(0);
            cell1.innerHTML = data[i].url;
        }    
        });

    } else {
        console.log("Nope!")
    }
})
}

function deleteBlackListInitiated(e) {
    e.preventDefault();
    var blackListDeleteInput = document.getElementById("deleteBlackListInputEntry").value
    console.log("deleteBlackListInitiated")
  
  var stringified = JSON.stringify({ url : blackListDeleteInput })
  console.log(stringified);
  fetch('http://localhost:8080/BlackList', {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ url : blackListDeleteInput}),
      credentials:"include"
    }).then(resp => {
    if (resp.status === 200) {
        let messageToAlert = "The BlackList item " + blackListDeleteInput + " was successfully deleted!";
        createAlert(messageToAlert, "success");
        findAllBlackListItems(e)

    } else {
        let messageToAlert = "The URL "  + blackListDeleteInput + " couldn't be deleted! Invalid format or url not blacklisted.";
        createAlert(messageToAlert, "danger");
    }
})
}

function createAlert(alelertToGive, kindOfAlert){
    let elementToapend = document.getElementById("alert-area");
    elementToapend.innerHTML = ""
    let alert = document.createRange().createContextualFragment("<div class='alert alert-" + kindOfAlert + " alert-dismissible fade show' role='alert'>" + alelertToGive + "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button></div>");
    elementToapend.prepend(alert);
  }

