'use strict'

let listNameInput = document.querySelector ("#listNameInput");
let displayListButton = document.querySelector("#displayListButton");
displayListButton.disabled = true;
listNameInput.addEventListener("change",checkDisplayListNameInput);

let listTable = document.querySelector("listTable");

function checkDisplayListNameInput()
{


    // Check input is not empty, if empty disable button
    if(document.querySelector("#listNameInput").value === "")
    {
        displayListButton.disabled = true;
    } 
    
    else
    {
        displayListButton.disabled = false;
    }
}



//Read
function displayList()
{
    let listName = document.querySelector("#listNameInput").value;
 
    const url = "http://localhost:9094/todolist/findbyname/" + listName; 

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
                let failedText = document.querySelector("#failedDisplayListText").innerHTML = `Failed to retrieve list:  ${response.status}`;
            }
            else
            {
              

               response.json().then((data) => {
               

              //Make Table

              let table = document.createElement("table");
              let row;
              let cellA; 
              let cellB;
              //let cellC;
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

                  
                  //Hide nested arrays
                  if(!Array.isArray(data[key]))
                  {
                 
                    row.appendChild(cellB);
                  
                  }
                 

              }


                    //// Tasks Loop \\\\\

              let taskTable = document.createElement("table");
              let taskRow;
              let cellC; 
              let cellD;

              for (let index = 0; index < data.tasks.length; index++) {
                  let element = data.tasks[index];
                  //let a = data.tasks[0];
                 
               
               //console.log(a);

              for(let key in element)
              {
               
                document.querySelector("#displayTaskTable").appendChild(taskTable);
                
                taskRow = document.createElement("tr");
                cellC = document.createElement("td");
                cellD = document.createElement("td");
                

                cellC.innerHTML = key;
                cellD.innerHTML = element[key];
                
                taskTable.appendChild(taskRow);
                taskRow.appendChild(cellC);
                taskRow.appendChild(cellD);
              }
              
            }
            ////  \\\\




              
});
             
                  
                console.log("List retrieved!");

                
                  //Display a success message on page if it's gone through :)
                let successText = document.querySelector("#displayListSuccessText").innerHTML = "List Displayed!";

            }
           
        
       
       
    }) 
  
    .catch((error) => {
        console.log(`Request failed ${error}`);
        let failedText = document.querySelector("#failedDisplayListText").innerHTML = `Failed to delete list:  Error:  ${error}`; 
      

      });


}


function clearDisplayedLists()
{
    let toDoListTable = document.querySelector("#displayTable");
    let taskTable = document.querySelector("#displayTaskTable");
   
   if(toDoListTable.firstChild)
   {
    toDoListTable.removeChild(toDoListTable.firstChild);
   }
   
   if(taskTable.firstChild)
    {

        taskTable.removeChild(taskTable.firstChild);
    }
  
}




document.querySelector("button#displayListButton").addEventListener("click", displayList);
document.querySelector("button#clearDisplayedListsButton").addEventListener("click", clearDisplayedLists);

