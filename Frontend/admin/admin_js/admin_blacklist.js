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
        var responseContent = document.getElementById("create-blacklist-response");
    if (resp.status === 201) {
        responseContent.textContent = "The BlackList item " + blackListInput + " was successfully created!";
        console.log("BlackList item was successfully created!")
        findAllBlackListItems(e)

    } else {
        responseContent.textContent = "The BlackList item couldn't be created! Invalid format or already blacklisted.";
        console.log("BlackList not created")
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
        var responseContent = document.getElementById("delete-blacklist-response");
    if (resp.status === 200) {
        responseContent.textContent = "The BlackList item " + blackListDeleteInput + " was successfully deleted!";
        console.log("BlackList item was successfully deleted!")
        findAllBlackListItems(e)

    } else {
        responseContent.textContent = "The BlackList item couldn't be deleted! Invalid format or url not blacklisted.";
        console.log("BlackList not deleted")
    }
})
}


