
//validation for informing that price is NULL when adding a new product AKA empty
function validateForm() {
    var name = document.getElementById("name").value;
    
    if (name.trim() === "") {
        alert("Name field cannot be empty.");
        return false; 
    }
    
    return true; // Allow form submission if all checks pass
}