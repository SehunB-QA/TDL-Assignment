'use strict'

let taskColourInput = document.querySelector ("#taskColourInput");
let displayTaskButton = document.querySelector("#displayTaskButton");
displayTaskButton.disabled = true;
taskColourInput.addEventListener("change",checkDisplayTaskColourInput);

let listTable = document.querySelector("listTable");

function checkDisplayTaskColourInput()
{


    // Check input is not empty, if empty disable button
    if(document.querySelector("#taskColourInput").value === "")
    {
        displayTaskButton.disabled = true;
    } 
    
    else
    {
        displayTaskButton.disabled = false;
    }
}



//Read
function displayTaskByColour()
{
    let taskColour = document.querySelector("#taskColourInput").value;
     //Turn all of the input to lowercase
     let finalTaskColourString = taskColour.toLowerCase();
     //Remove all whitespaces/ spacing from input string
     finalTaskColourString = finalTaskColourString.replace(/\s+/g, '');

 
    const url = "http://localhost:9094/tasks/findbycolourcode/" + finalTaskColourString; 

    let request = new Request(url, {
        method: "GET",  
        headers: {
            "Content-type": "application/json",
        },
    });

    fetch(request)
        .then(function (response) {
             //Response from API
            if (response.status !== 200)
            {
                  //Display error on page if there's a failure
                console.log(`Request failed ${response.status}`);
                let failedText = document.querySelector("#failedDisplayTaskText").innerHTML = `Failed to retrieve task:  ${response.status}`;
            }
            else
            {
             
               response.json().then((data) => {
               
              
              //Make Table

              let table = document.createElement("table");
              let row;
              let cellA; 
              let cellB;
              document.querySelector("#displayTable").appendChild(table); 
              for(let key in data)
              {
               
                  row = document.createElement("tr");
                  cellA = document.createElement("td");
                  cellB = document.createElement("td");
                  

                  cellA.innerHTML = key;
                  cellB.innerHTML = data[key];
                  
                  table.appendChild(row);
                  row.appendChild(cellA);
                  row.appendChild(cellB);

                 

              }


            



              
});
             
                console.log("Tasks found!");

                
                  //Display a success message on page if it's gone through :)
                let successText = document.querySelector("#displayTaskSuccessText").innerHTML = `Task(s) of ${finalTaskColourString} found!`;

            }
           
        
       
       
    }) 
  
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedDisplayTaskText").innerHTML = `Failed to find task:  Error:  ${error}`; 
      

      });


}










function clearDisplayedTask()
{
    
    let taskTable = document.querySelector("#displayTable");
   
   if(taskTable.firstChild)
    {

        taskTable.removeChild(taskTable.firstChild);
    }
  
}




document.querySelector("button#displayTaskButton").addEventListener("click", displayTaskByColour);
document.querySelector("button#clearDisplayedTaskButton").addEventListener("click", clearDisplayedTask);

