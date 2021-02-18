'use strict'

let listIDInput = document.querySelector ("#listIDInput");
let displayListButton = document.querySelector("#displayListButton");
displayListButton.disabled = true;
listIDInput.addEventListener("change",checkDisplayListIDInput);

let listTable = document.querySelector("listTable");

function checkDisplayListIDInput()
{


    // Check input is not empty, if empty disable button
    if(document.querySelector("#listIDInput").value === "")
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
    let listID = document.querySelector("#listIDInput").value;
 
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
                let failedText = document.querySelector("#failedDisplayListText").innerHTML = `Failed to retrieve list:  ${response.status}`;
            }
            else
            {
              //  response.json().then((data) => console.info(data));
               // console.log(response);

               response.json().then((data) => {
                let tasksData = data.tasks;
               //console.log(data);
              // const { 0: {id,name,description,colour}} = data.tasks;
       
               // console.log(`Task id: ${id}  Task Name: ${name} Task description: ${description} Task colour ${colour}`);
               const a = data.tasks[0];

               console.log(a);
               
               //console.log( tasksData);
               // tasksData = JSON.stringify(tasksData);
              // console.log(data.tasks[0]); 
           /*    for (let i in tasksData) {
                console.log(tasksData[i]["name"]);
            } */

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
                  row.appendChild(cellB);
/* 
                  for(let key in tasksData)
                  {
                    cellC = document.createElement("td");
                    cellC.innerHTML = tasksData[key];
                    table.appendChild(row);
                    row.appendChild(cellC);
                  } */

              }

              let taskTable = document.createElement("table");
              let taskRow;
              let cellC; 
              let cellD;
              for(let key in a)
              {
               
                document.querySelector("#displayTaskTable").appendChild(taskTable);
                
                taskRow = document.createElement("tr");
                cellC = document.createElement("td");
                cellD = document.createElement("td");
                

                cellC.innerHTML = key;
                cellD.innerHTML = a[key];
                
                taskTable.appendChild(taskRow);
                taskRow.appendChild(cellC);
                taskRow.appendChild(cellD);
              }
              





              
});
             
                      /*    response.json().then((data) =>
                {
                    data = JSON.stringify(data);
                let failedText = document.querySelector("#failedDisplayListText").innerHTML = `${data}`;

                    
                }); 
            */

              /*    response.json().then((data) =>
                {
                    data = JSON.stringify(data);
                let failedText = document.querySelector("#failedDisplayListText").innerHTML = `${data}`;

                    
                }); 
            */
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




document.querySelector("button#displayListButton").addEventListener("click", displayList);

