'use strict'

let taskIDInput = document.querySelector ("#taskIDInput");
let taskIDButton = document.querySelector("#removeTaskFromListButton");
taskIDButton.disabled = true;
taskIDInput.addEventListener("change",checkRemoveTaskFromListInput);

function checkRemoveTaskFromListInput()
{


    // Check input is not empty, if empty disable button
    if(document.querySelector("#taskIDInput").value === "")
    {
        taskIDButton.disabled = true;
    } 
    
    else
    {
        taskIDButton.disabled = false;
    }
}









//Delete
function removeTaskFromList()
{
    let taskID = document.querySelector("#taskIDInput").value;
 
    const url = "http://localhost:9094/tasks/removefromlist/" + taskID; 

    let request = new Request(url, {
        method: "PUT",  
        headers: {
            "Content-type": "application/json",
        },
    });

    fetch(request)
        .then(function (response) {
             //Response from API
            if (response.status !== 204)
            {
                  //Display error on page if there's a failure
                console.log(`Request failed ${response.status}`);
                let failedText = document.querySelector("#failedRemoveTaskText").innerHTML = `Failed to remove task from list:  ${response.status}`;
            }
            else
            {
              
                console.log("Task removed from list!");
                  //Display a success message on page if it's gone through :)
                let successText = document.querySelector("#RemoveTaskSuccessText").innerHTML = "Task removed from list!";

            }
           
        
       
       
    }) 
  
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedRemoveTaskText").innerHTML = `Failed to remove task from list:  Error:  ${error}`; 
      

      });

}

document.querySelector("button#removeTaskFromListButton").addEventListener("click", removeTaskFromList);