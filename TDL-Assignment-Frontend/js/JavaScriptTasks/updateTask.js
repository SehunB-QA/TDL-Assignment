'use strict'


//Read
function getTaskDetails()
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
                let failedText = document.querySelector("#failedGetTaskIDText").innerHTML = `Failed to retrieve task ID:  ${response.status}`;
            }
            else
            {
           

               response.json().then((data) => {
                 let taskIDInput = document.querySelector("#retrievedTaskID");
                taskIDInput.value = data.id;

                let tasknameInput = document.querySelector("#updatedTaskNameInput");
                tasknameInput.value = data.name;
               
                let taskColourInput = document.querySelector("#updatedTaskColourInput");
                taskColourInput.value = data.colour;

                let taskDescriptionInput = document.querySelector("#updatedTaskDescriptionInput");
                taskDescriptionInput.value = data.description;
              
});
             
                  
                console.log("Task ID retrieved!");

                
                  //Display a success message on page if it's gone through :)
                let successText = document.querySelector("#gotIDSuccessText").innerHTML = "Got Task ID !";

            }
           
        
       
       
    }) 
  
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedDisplayListText").innerHTML = `Failed to delete list:  Error:  ${error}`; 
      

      });


}


function updateTask()
{
    let newTaskName = document.querySelector("#updatedTaskNameInput").value;
    let newTaskColour = document.querySelector("#updatedTaskColourInput").value;
    let newDescription = document.querySelector("#updatedTaskDescriptionInput").value;
    
    let gottenTaskID = document.querySelector("#retrievedTaskID").value;
  
    const url = "http://localhost:9094/tasks/update/" + gottenTaskID;

    //Turn all of the input to lowercase
    let finalTaskNameString = newTaskName.toLowerCase();
    //Remove all whitespaces/ spacing from input string
    finalTaskNameString = finalTaskNameString.replace(/\s+/g, '');

     //Turn all of the input to lowercase
     let finalTaskColourString = newTaskColour.toLowerCase();
     //Remove all whitespaces/ spacing from input string
     finalTaskColourString = finalTaskColourString.replace(/\s+/g, '');

    let dataToSend = {
        name: finalTaskNameString,
        colour: finalTaskColourString,
        description: newDescription
    }

    let request = new Request(url, {
        method: "PUT",
        body: JSON.stringify(dataToSend),
        headers: {
            "Content-type": "application/json",
        },
    });

    fetch(request)
        .then(function (response) {
                 //Response from API
                 if (response.status !== 202)
                 {
                       //Display error on page if there's a failure
                       console.log(`Request failed ${error}`);
                       let failedText = document.querySelector("#failedUpdateIDText").innerHTML = `Failed to update task:  ${response.status}`;
                 }
                 else
                 {
                     console.log("List name updated!");
                    let successText = document.querySelector("#updatedIDSuccessText").innerHTML = "Task updated!";
     
                 }
        //Response from API
        
         //Display a sucess message on page if it's gone through :)
      
    }) 
    //Display error on page if there's a failure
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedUpdateIDText").innerHTML = `Failed to update task:  Error:  ${error}`; 

      });
}





















document.querySelector("button#getTaskIDButton").addEventListener("click", getTaskDetails);
document.querySelector("button#updateTaskButton").addEventListener("click", updateTask);









