window.onload = populateCategories;


//CREATE CATEGORY

function createNewCategory(e) {
    e.preventDefault();
    console.log("createCategoryInitiated");
    var categoryName = document.getElementById("createCategoryInput").value

  var stringified = JSON.stringify({ category : categoryName})
  console.log(stringified);
  fetch('http://localhost:8080/Category', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ category : categoryName}),
      credentials:"include"
    }).then(resp => {
        var responseContent = document.getElementById("create-category-response");
    if (resp.status === 201) {
        responseContent.textContent = "The category " + categoryName + " was successfully created!";
        console.log("Category was successfully created!")
        populateCategories();

    } else {
        responseContent.textContent = "The category couldn't be created! Invalid format.";
        console.log("Category not created")
    }
})
}

//DELETE CATEGORY

function deleteCategory(e) {
  e.preventDefault();
  var category = document.getElementById("category-dropdown")
  var categoryOption = category.options[category.selectedIndex].value

var stringified = JSON.stringify({ category : categoryOption})
console.log(stringified);
fetch('http://localhost:8080/Category', {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ category : categoryOption}),
    credentials:"include"
  }).then(resp => {
      var responseContent = document.getElementById("create-category-response");
  if (resp.status === 200) {
      responseContent.textContent = "The category " + categoryOption + " was successfully deleted!";
      console.log("Category " + categoryOption + " was successfully deleted!") 
      populateCategories();

  } else {
      responseContent.textContent = "The category couldn't be deleted.";
      console.log("Category not deleted")
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