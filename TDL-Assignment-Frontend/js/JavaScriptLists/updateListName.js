'use strict'


//Read
function getListIDAndName()
{
    let listID = document.querySelector("#listNameInput").value;
 
    const url = "http://localhost:9094/todolist/read/" + listID; 

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
                let failedText = document.querySelector("#failedGetListIDText").innerHTML = `Failed to retrieve list ID:  ${response.status}`;
            }
            else
            {
           

               response.json().then((data) => {
                 let listIDInput = document.querySelector("#retrievedListID");
                listIDInput.value = data.id;

                let listnameInput = document.querySelector("#updatedListNameInput");
                listnameInput.value = data.name;
               
              
});
             
                  
                console.log("List ID retrieved!");

                
                  //Display a success message on page if it's gone through :)
                let successText = document.querySelector("#gotListIDSuccessText").innerHTML = "Got List ID !";

            }
           
        
       
       
    }) 
  
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedDisplayListText").innerHTML = `Failed to delete list:  Error:  ${error}`; 
      

      });


}


function updateListName()
{
    let newListName = document.querySelector("#updatedListNameInput").value;
    let gottenListID = document.querySelector("#retrievedListID").value;
  
    const url = "http://localhost:9094/todolist/update/" + gottenListID;

    //Turn all of the input to lowercase
    let finalListNameString = newListName.toLowerCase();
    //Remove all whitespaces/ spacing from input string
    finalListNameString = finalListNameString.replace(/\s+/g, '');

    let dataToSend = {
        name: finalListNameString
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
                       let failedText = document.querySelector("#failedUpdateIDText").innerHTML = `Failed to update list:  ${response.status}`;
                 }
                 else
                 {
                     console.log("List name updated!");
                    let successText = document.querySelector("#updatedIDSuccessText").innerHTML = "List updated!";
     
                 }
        //Response from API
        
         //Display a sucess message on page if it's gone through :)
      
    }) 
    //Display error on page if there's a failure
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedUpdateIDText").innerHTML = `Failed to update list name:  Error:  ${error}`; 

      });
}





















document.querySelector("button#getListIDButton").addEventListener("click", getListIDAndName);
document.querySelector("button#updateListButton").addEventListener("click", updateListName);









