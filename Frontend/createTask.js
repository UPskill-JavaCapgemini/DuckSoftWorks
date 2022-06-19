function MyFunction(e) {
    e.preventDefault();
    console.log("hi there");
    var link = document.getElementById("link").value
    var category = document.getElementById("category")
    var categoryOption = category.options[category.selectedIndex].value
    var timeLimit = document.getElementById("timeLimit")
    var timeLimitOption = timeLimit.options[timeLimit.selectedIndex].value
  //  document.getElementById("demo").innerHTML = link + " " + category + " " + timeLimit;
    
  var stringified = JSON.stringify({ url : link, category : categoryOption, timeOut : timeLimitOption })
  console.log(stringified);
  fetch('http://localhost:8080/LanguageDetection', {
      method: 'POST',
      mode: 'no-cors', // no-cors, *cors, same-origin
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify({ url : link, category : categoryOption, timeOut: timeLimitOption })
    }).then(res => res.json())
  }