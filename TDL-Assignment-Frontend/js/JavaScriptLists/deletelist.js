'use strict'

let listIDInput = document.querySelector ("#listIDInput");
let listIDButton = document.querySelector("#deleteListButton");
listIDButton.disabled = true;
listIDInput.addEventListener("change",checkDeleteListTextInput);

function checkDeleteListTextInput()
{


    // Check input is not empty, if empty disable button
    if(document.querySelector("#listIDInput").value === "")
    {
        listIDButton.disabled = true;
    } 
    
    else
    {
        listIDButton.disabled = false;
    }
}









//Delete
function deleteList()
{
    let listID = document.querySelector("#listIDInput").value;
 
    const url = "http://localhost:9094/todolist/delete/" + listID; 

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
                let failedText = document.querySelector("#deletedCreatedListText").innerHTML = `Failed to delete list:  ${response.status}`;
            }
            else
            {
              
                console.log("List deleted!");
                  //Display a success message on page if it's gone through :)
                let successText = document.querySelector("#deletedListSuccessText").innerHTML = "List Deleted!";

            }
           
        
       
       
    }) 
  
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#deletedCreatedListText").innerHTML = `Failed to delete list:  Error:  ${error}`; 
      

      });

}

document.querySelector("button#deleteListButton").addEventListener("click", deleteList);