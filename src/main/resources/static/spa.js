$(document).ready(function(){
        $(".Add_Buddy_Button").hide();});

/* Function to post and create an addressbook */
function writeBook() {
    let book = $('.bookName').val();
    $.ajax( {
        url:"http://localhost:8080/addressbook",
        type:"POST",
        dataType:"application/json",
        contentType: "application/json",
        data:JSON.stringify({"bookName":book}),}
    )
        $('.content').html("Added Address Book");
        $('.Add_Buddy_Button').show();
};

/* Function to post and create a buddy */
function writeBuddy() {
    let book = $('.bookid').val();
    let name = $('.fname').val();
    let address = $('.address').val();
    let pnumber = $('.pnumber').val();
    $.ajax( {
        url:"http://localhost:8080/addressbook/" + book + "/BuddyInfo",
        type:"POST",
        dataType:"application/json",
        contentType: "application/json",
        data:JSON.stringify({"firstName":name, "address":address, "phoneNumber":pnumber}),}
    ).then(function(data) {
        readBook();
    })};

/* Function to populate fields for creating an addressbook */
function getAddressBookAddFields(){
    var name = "<p>First Name: <input class=\"bookName\" type=\"text\"/></p> " +
        "<input class=\"Add_Book_Button\" type=\"button\" value=\"Create Address Book\" onclick=\"writeBook();\" />"
    $('.content').html(name);
}

/* Function to populate fields for creating a buddy */
function getBuddyAddFields(){
    var fields = "<p>Book Id: <input class=\"bookid\" type=\"text\"/></p>" +
        " <p>First Name: <input class=\"fname\" type=\"text\"/></p>" +
        " <p>Phone Number: <input class=\"pnumber\" type=\"text\"/></p> " +
        "<p>Address: <input class=\"address\" type=\"text\"/></p>" +
        "<input class=\"Add_Buddy_Button\" type=\"button\" value=\"Create Buddy\" onclick=\"writeBuddy();\" />"
    $('.content').html(fields);
}

/* Function to display the addressbook and its buddies*/
function readBook() {
    $.ajax({
        url: "http://localhost:8080/addressbook",
    }).then(function(data) {
        $('.content').html("Books:");
        for (let i = 0; i < data.length; ++i){
            let st = "<p> Book Name:" +data[i].bookName + "<br> Book ID:" + data[i].id+"</p>";
            $('.content').append(st);
            for (let j = 0; j < data[i].buddy.length; ++j){
                let xt = "<p> Buddy ID: " + data[i].buddy[j].id + " FirstName: " + data[i].buddy[j].firstName+"  Address: "+ data[i].buddy[j].address   +" Phone Number: "+ data[i].buddy[j].phoneNumber+"</p>";
                $('.content').append(xt);
            }
            $('.content').append("----------------------------------------------");
        }
    });
};



