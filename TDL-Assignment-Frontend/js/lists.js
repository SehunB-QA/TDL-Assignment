'use strict'

let listNameInput = document.querySelector ("#listnameInput");
let listNameButton = document.querySelector("#createListButton");
listNameButton.disabled = true;
listNameInput.addEventListener("change",checkCreateListTextInput);

function checkCreateListTextInput()
{


    // Check input is not empty, if empty disable button
    if(document.querySelector("#listnameInput").value === "")
    {
     listNameButton.disabled = true;
    } 
    
    else
    {
        listNameButton.disabled = false;
    }
}





// To Do List

//Create
function createList()
{
    let listName = document.querySelector("#listnameInput").value;
    const url = "http://localhost:9094/todolist/create";

    //Turn all of the input to lowercase
    let finalListNameString = listName.toLowerCase();
    //Remove all whitespaces/ spacing from input string
    finalListNameString = finalListNameString.replace(/\s+/g, '');

    let dataToSend = {
        name: finalListNameString
    }

    let request = new Request(url, {
        method: "POST",
        body: JSON.stringify(dataToSend),
        headers: {
            "Content-type": "application/json",
        },
    });

    fetch(request)
        .then(function () {
        //Response from API
        console.log("List created!");
         //Display a sucess message on page if it's gone through :)
        let successText = document.querySelector("#createdListSuccessText").innerHTML = "List Created!";
    }) 
    //Display error on page if there's a failure
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedCreatedListText").innerHTML = `Failed to create list:  ${error}`;

      });

}

document.querySelector("button#createListButton").addEventListener("click", createList);




