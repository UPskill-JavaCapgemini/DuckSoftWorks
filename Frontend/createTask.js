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
     /* mode: 'cors', // no-cors, *cors, same-origin
      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      credentials: 'include', // include, *same-origin, omit
      redirect: 'follow', // manual, *follow, error
      referrerPolicy: 'no-referrer',*/
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ url : link, category : categoryOption, timeOut: timeLimitOption })
    }).then(res => console.log(res))
  }