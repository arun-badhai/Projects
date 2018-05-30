$(document).ready(function() {
    $(".fieldNames").attr("disabled",true);
    $("#submitButton").hide();

    $( "#editButton" ).click(function(e) {
        e.preventDefault();
        editContact();
    });
    $( "#cancelButton" ).click(function(e) {
        e.preventDefault();
        cancelEdit();
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
    window.back(-1);
}
function deleteContact() {
    
}