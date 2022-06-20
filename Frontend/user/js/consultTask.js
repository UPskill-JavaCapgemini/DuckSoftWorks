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
    
    fetch(categoriesURL)  
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
              }
          } ).then(resp => {
         //   var responseContent = document.getElementById("create-task-response");
        if (resp.status === 200) {
            console.log("The filter is working")
        } else {
            console.log("Nope!")
        }
    })
    }


