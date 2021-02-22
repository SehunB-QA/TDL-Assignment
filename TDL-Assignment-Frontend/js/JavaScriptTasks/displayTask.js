'use strict'

let taskIDInput = document.querySelector ("#taskIDInput");
let displayTaskButton = document.querySelector("#displayTaskButton");
displayTaskButton.disabled = true;
taskIDInput.addEventListener("change",checkDisplayTaskIDInput);

let taskTable = document.querySelector("#displayTaskTable");

function checkDisplayTaskIDInput()
{


    // Check input is not empty, if empty disable button
    if(document.querySelector("#taskIDInput").value === "")
    {
        displayTaskButton.disabled = true;
    } 
    
    else
    {
        displayTaskButton.disabled = false;
    }
}




//Read
function displayTask()
{
    let taskID = document.querySelector("#taskIDInput").value;
 
    const url = "http://localhost:9094/tasks/read/" + taskID; 

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
              //  response.json().then((data) => console.info(data));
               // console.log(response);

               response.json().then((data) => {
               //Make Table

               let table = document.createElement("table");
               let row;
               let cellA; 
               let cellB;
               document.querySelector("#displayTaskTable").appendChild(table); 
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
             
              
                console.log("Task retrieved!");

                
                  //Display a success message on page if it's gone through :)
                let successText = document.querySelector("#displayTaskSuccessText").innerHTML = "Task Displayed!";

            }
           
        
       
       
    }) 
  
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedDisplayTaskText").innerHTML = `Failed to retrive task:  Error:  ${error}`; 
      

      });


}


function clearDisplayedTasks()
{
   
    let taskTable = document.querySelector("#displayTaskTable");
    
   if(taskTable.firstChild)
    {

        taskTable.removeChild(taskTable.firstChild);
    }
  
}




document.querySelector("button#displayTaskButton").addEventListener("click", displayTask);
document.querySelector("button#clearDisplayedTasksButton").addEventListener("click", clearDisplayedTasks);

