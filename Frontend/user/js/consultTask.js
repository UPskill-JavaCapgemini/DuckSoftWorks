window.onload = populateCategories;

function populateCategories(){

    let dropdown = document.getElementById('category-dropdown');
    dropdown.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'No Category chosen';
    defaultOption.value = '';

dropdown.add(defaultOption);
dropdown.selectedIndex = 0;
    
    const categoriesURL = 'http://localhost:8080/Category';
    
    fetch(categoriesURL,{credentials:"include"})  
      .then(  
        function(response) {  
          if (response.status !== 200) {  
            console.warn('Looks like there was a problem. Status Code: ' + 
              response.status);  
            return;  
          }
    
          // Examine the text in the response  
          response.json().then(function(data) {  
            let option;
        
            for (let i = 0; i < data.length; i++) {
              option = document.createElement('option');
                option.text = data[i].category;
                option.value = data[i].category;
                dropdown.add(option);
            }    
          });  
        }  
      )  
      .catch(function(err) {  
        console.error('Fetch Error -', err);  
      });
    }

    function filterTasksStatusCategory(e) {
        e.preventDefault();
        console.log("hi there");

        var category = document.getElementById("category-dropdown")
        var categoryOption = category.options[category.selectedIndex].value
        var status = document.getElementById("status-dropdown")
        var statusOption = status.options[status.selectedIndex].value
        var urlToRequest = "http://localhost:8080/LanguageDetection" + "?" + "categoryName=" + categoryOption + "&" + "status=" + statusOption
       // http://localhost:8080/LanguageDetection?categoryName=Economics&status=Processing
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
          let table = document.getElementById("consult-task-data-table");   
          table.innerHTML = ""

        for (let i = 0; i < data.length; i++) {

            let row = table.insertRow(i);
            let cell1 = row.insertCell(0);
            let cell2 = row.insertCell(0);
            let cell3 = row.insertCell(0);
            let cell4 = row.insertCell(0);
            let cell5 = row.insertCell(0);
            let cell6 = row.insertCell(0);
            let cell7 = row.insertCell(0);
            cell1.innerHTML = data[i].id;
            cell2.innerHTML = data[i].date;
            cell3.innerHTML = data[i].inputUrl;
            //cell4.innerHTML = data[i].taskResult.language;
            let dataForLanguage = data[i].taskResult.language
            if( dataForLanguage === null){
              cell4.innerHTML = "   ";
            } else {
              cell4.innerHTML = dataForLanguage;
            }
            cell5.innerHTML = data[i].currentStatus;
            cell6.innerHTML = data[i].timeOut;
            cell7.innerHTML = data[i].category;
        }    
        });

        } else {
            console.log("Nope!")
        }
      })
    }