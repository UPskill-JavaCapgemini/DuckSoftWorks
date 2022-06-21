window.onload = populateCategories;

function createNewTask(e) {
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
      body: JSON.stringify({ url : link, category : categoryOption, timeOut: timeLimitOption }),
      credentials:"include"
    }).then(resp => {
        var responseContent = document.getElementById("create-category-response");
    if (resp.status === 201) {
        responseContent.textContent = "The category was successfully created!";
        console.log("Category was successfully created!")
    } else {
        responseContent.textContent = "The category couldn't be created! Invalid format.";
        console.log("Category not created")
    }
})
}


function populateCategories(){

    let dropdown = document.getElementById('category-dropdown');
    dropdown.length = 0;
    
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