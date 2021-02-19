'use strict'

let taskIDInput = document.querySelector ("#taskIDInput");
let taskIDButton = document.querySelector("#deleteTaskButton");
taskIDButton.disabled = true;
taskIDInput.addEventListener("change",checkDeleteTaskTextInput);

function checkDeleteTaskTextInput()
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
function deleteTask()
{
    let taskID = document.querySelector("#taskIDInput").value;
 
    const url = "http://localhost:9094/tasks/delete/" + taskID; 

    let request = new Request(url, {
        method: "DELETE",  
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
                let failedText = document.querySelector("#failedDeleteTaskText").innerHTML = `Failed to delete task:  ${response.status}`;
            }
            else
            {
              
                console.log("Task deleted!");
                  //Display a success message on page if it's gone through :)
                let successText = document.querySelector("#deletedTaskSuccessText").innerHTML = "Task Deleted!";

            }
           
        
       
       
    }) 
  
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedDeleteTaskText").innerHTML = `Failed to delete task:  Error:  ${error}`; 
      

      });

}

document.querySelector("button#deleteTaskButton").addEventListener("click", deleteTask);