
// Add a new Marker object to the given map with the given properties
function addMapMarker(map, properties, customer) {

    var marker = new google.maps.Marker({
        position: properties.latLng,
        map: map,
        title: properties.title,
        icon: {
            url: markerIcon(properties.active)
        },
        customer: customer
    });

    marker.contentString =
        '<div class="info-window"><span><strong>' + customer.first + ' ' + customer.last + '</strong></span><br>'
        + '<span><a href="#">' + customer.email + '</a></span><br><span>' + customer.phone + '</span><br><span>'
        + customer.address + '</span><br><span>' + customer.city + '</span><br><span>' + customer.state + '</span><br><span>'
        + customer.zip + '</span><br>';

     if(customer.stable) {
        marker.contentString += '<span>' + customer.stable + '</span>';
     }

     marker.contentString += '</div>';

    marker.addListener("click", function() {
        toggleMarkerRequest(marker);
    });

    marker.addListener('mouseover', function() {

        infowindow.close();
        infowindow.setContent(marker.contentString);
        infowindow.open(map, marker);
    });

    marker.addListener('mouseout', function() {
        infowindow.close();
    });

    console.log(marker);
    markersList.push(marker);
}

function markerIcon(active) {
    return (active) ? "http://maps.google.com/mapfiles/ms/icons/red-dot.png" :
                "http://maps.google.com/mapfiles/ms/icons/green-dot.png";
}

function generateLink() {

    var link = "https://www.google.com/maps/dir/";
    markersList.forEach(function(marker) {
        if (marker.customer.active === true) {
            link += marker.position + "/";
        }
    });

    return link;
}

function followLink() {
    location.replace(generateLink());
}

function toggleMarker(marker) {
    console.log(marker);
    marker.customer.active = !marker.customer.active;
    marker.setIcon(markerIcon(marker.customer.active));
    console.log("Successfully toggled marker");
}

function toggleMarkerRequest(marker) {
    var url = "/mapapp/toggle-marker";
    var methodType = "POST";

    var customerData = { customer: JSON.stringify(marker.customer) };

    makeAjaxCallWithData(url, methodType, function() { toggleMarker(marker); }, customerData);
}

function mapCustomers(geocoder, map, customers) {

    //foreach customer
    //add map marker()
    var promises = [];

    customers.forEach(function(customer) {
        console.log(customer);

        var p = new Promise(function(resolve, reject) {
            geocoder.geocode({address: customer.address + " " + customer.city + " " + customer.state + " " + customer.zip},

            function(results, status) {
                if (status === "OK") {
                    var latLng = results[0].geometry.location;
                    var properties = {};
                    properties.latLng = results[0].geometry.location;
                    properties.title = customer.first + " " + customer.last;
                    properties.active = customer.active;
                    addMapMarker(map, properties, customer);
                    resolve();

                } else {
                    console.log("Geocode was not successful for the following reason: " + status);
                    reject();
                }
            });
        });

        promises.push(p);
    });

    Promise.all(promises).then(function() {
        generateLink();
    });

}

function mapCustomersRequest(geocoder, map) {
    var url = "/mapapp/get-customers";
    makeAjaxCall(url, "GET", function(customers) {
        mapCustomers(geocoder, map, customers)
    });
}


function makeAjaxCall(url, methodType, callback) {
    $.ajax({
        url: url,
        method: methodType,
        dataType: "json",
        success: callback,
        error: function (reason, xhr) {
            console.log("Error in processing your request", reason);
        }
    });
}

// For stringified JSON
function makeAjaxCallWithData(url, methodType, callback, jsonData) {
    $.ajax({
        url: url,
        method: methodType,
        dataType: "json",
        data: jsonData,
        success: callback,
        error: function (reason, xhr) {
            console.log("Error in processing your request", reason);
        }
    });
}

var markersList = [];

mapCustomersRequest(geo, map);

