$(document).ready(function() {
    $(".fieldNames").attr("disabled",true);
    $("#submitButton").hide();

    $( "#editButton" ).click(function(e) {
        e.preventDefault();
        editContact();
    });
});
function editContact(){
    $(".fieldNames").attr("disabled",false);
    $("#editButton").hide();
    $("#submitButton").show();
    $(".delete").hide();
}
function saveEdit() {
    
}
function cancelEdit() {
    
}
function deleteContact() {
    
}