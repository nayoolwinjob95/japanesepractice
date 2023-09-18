$(document).ready(function(){
    // SHOWCASE SLIDER SECTION
    $('.carousel').carousel({
    interval:6000,
    pause:'hover'
    });
    
    // GET YEAR
    var currentYear = new Date().getFullYear();
    $('#getyear').text(currentYear);
});