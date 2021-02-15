'use strict'



    let dropdown = document.getElementsByClassName("dropdown-btn");
    for (let i = 0; i < dropdown.length; i++) {
        dropdown[i].addEventListener("click", function () {
            this.classList.toggle("active");
            let dropdownText = this.nextElementSibling;
            if (dropdownText.style.display === "block") {
                dropdownText.style.display = "none";
            } else {
                dropdownText.style.display = "block";
            }
        });
    }


document.querySelector("button#dropdown-btn").addEventListener("click", dropdownFunction);




