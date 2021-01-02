var PROJECT_ID = "";
var CLIENT_ID = "";
var API_KEY = "";

var startingLatLng = {lat: 34.246, lng: -80.607};

var script = document.createElement("script");
script.src = "https://maps.googleapis.com/maps/api/js?key=" + API_KEY + 
    "&callback=initMap";
script.defer = true;
script.async = true;

var geo;
var map;
var infowindow;

window.initMap = function() {

     geo = new google.maps.Geocoder();

     //Make starting zoom and location configurable
     map = new google.maps.Map(document.getElementById("map-main"), {
        center: startingLatLng,
        zoom: 6
     });

     infowindow = new google.maps.InfoWindow();

     var mapfns = document.createElement("script");
     mapfns.src = "js/map-fns.js";
     mapfns.defer = true;
     mapfns.async = true;
     document.head.appendChild(mapfns)
}

document.head.appendChild(script);