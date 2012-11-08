<#macro defaultLayout title="Donors Connect" desc="Donors Connect Website" img="image/children_thumbnail.png">
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html;charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

    <meta property="og:title" content="${title}"/>
    <meta property="og:image" content="${img}"/>
    <meta property="og:description" content="${desc}"/>

    <link rel="stylesheet" href="css/project.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/home.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/jquery.validate.css">
    <title>Donors-Connect-Project</title></head>
    <script type="text/javascript" src="javascript/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="javascript/jquery.validate.js"></script>
<body>
<div style="background: #BFD9DA"><#include "header.ftl" /></div>
<div style="height: 20px; clear: both;">
</div>
<div class="body">
    <#nested />
</div>
<script type="text/javascript" src="javascript/ammado.js"></script>
</body>
</html>
</#macro>
