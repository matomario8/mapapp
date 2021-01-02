<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<html>
<head>
    <title>mapapp - Map View</title>
    <link rel="stylesheet" href="styles/main.css">
    <link rel="stylesheet" href="styles/bootstrap.min.css">
    <script
            src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous">
    </script>
    <script src="js/map.js"></script>
</head>
<body>
    <h3>Map View</h3>
    <div class="map" id="map-main"></div>
    <button class="btn btn-primary absolute-btn" onclick="followLink()">Get Directions</button>
</body>
</html>