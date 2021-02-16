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

    let dataToSend = {
        name: listName
    }

    let request = new Request(url, {
        method: "POST",
        body: dataToSend,
        headers: new Headers()
    });

    fetch(request)
        .then(function () {
        //Response from API
    })

}


