'use strict'


let listTaskNameInput = document.querySelector ("#taskNameInput");
let listColourInput = document.querySelector ("#taskColourInput");
let listTaskDescriptionInput = document.querySelector("#taskDescriptionTextStringBox");
let listTaskIDInput = document.querySelector ("#taskListIDInput");



let createTaskButton = document.querySelector("#createTaskButton");
createTaskButton.disabled = true;

listTaskNameInput.addEventListener("change", checkCreateTaskInputs);
listColourInput.addEventListener("change", checkCreateTaskInputs);
listTaskDescriptionInput.addEventListener("change", checkCreateTaskInputs);
listTaskIDInput.addEventListener("change", checkCreateTaskInputs);


function checkCreateTaskInputs()
{


    // Check input is not empty, if empty disable button
    if(document.querySelector("#taskNameInput").value === "")
    {
        createTaskButton.disabled = true;
    } 

    else if(document.querySelector("#taskColourInput").value === "")
    {
        createTaskButton.disabled = true;
    } 

    else if(document.querySelector("#taskDescriptionTextStringBox").value === "")
    {
        createTaskButton.disabled = true;
    } 

    else if(document.querySelector("#taskListIDInput").value === "")
    {
        createTaskButton.disabled = true;
    } 
    
    else
    {
        createTaskButton.disabled = false;
    }
}





// To Do List

//Create
function createTask()
{
    let taskNameInput = document.querySelector ("#taskNameInput").value;
    let colourInput = document.querySelector ("#taskColourInput").value;
    let taskDescriptionInput = document.querySelector("#taskDescriptionTextStringBox").value;
    let taskIDInput = document.querySelector ("#taskListIDInput").value;

    const url = "http://localhost:9094/tasks/create";

    //Turn all of the input to lowercase
    let finalTaskNameString = taskNameInput.toLowerCase();
    //Remove all whitespaces/ spacing from input string
    finalTaskNameString = finalTaskNameString.replace(/\s+/g, '');

     //Turn all of the input to lowercase
     let finalTaskColourString = colourInput.toLowerCase();
     //Remove all whitespaces/ spacing from input string
     finalTaskColourString = finalTaskColourString.replace(/\s+/g, '');


    let dataToSend = {
        colour: finalTaskColourString,
        description:  taskDescriptionInput,
        name: finalTaskNameString,
        todolist: 
        {
            id: taskIDInput
        }
    }

    let request = new Request(url, {
        method: "POST",
        body: JSON.stringify(dataToSend),
        headers: {
            "Content-type": "application/json",
        },
    });

    fetch(request)
        .then(function (response) {
                 //Response from API
                 if (response.status !== 201)
                 {
                       //Display error on page if there's a failure
                       console.log(`Request failed ${response.status}`);
                       let failedText = document.querySelector("#failedCreatedTaskText").innerHTML = `Failed to create task:  ${response.status}`;
                 }
                 else
                 {
                     console.log("Task created!");
                    let successText = document.querySelector("#createdTaskSuccessText").innerHTML = "Task Created!";
     
                 }
        //Response from API
        
         //Display a sucess message on page if it's gone through :)
      
    }) 
    //Display error on page if there's a failure
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedCreatedTaskText").innerHTML = `Failed to create task:  Error:  ${error}`; 

      });

}










document.querySelector("button#createTaskButton").addEventListener("click", createTask);





