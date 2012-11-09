<#macro defaultLayout title="Donors Connect" desc="Donors Connect Website" img="static/image/children_thumbnail.png">
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html;charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">

    <meta property="og:title" content="${title}"/>
    <meta property="og:image" content="${img}"/>
    <meta property="og:description" content="${desc}"/>

    <link rel="stylesheet" href="static/css/project.css">
    <link rel="stylesheet" href="static/css/main.css">
    <link rel="stylesheet" href="static/css/layout.css">
    <link rel="stylesheet" href="static/css/home.css">
    <link rel="stylesheet" href="static/css/header.css">
    <link rel="stylesheet" href="static/css/jquery.validate.css">
    <title>Donors-Connect-Project</title>
    <script type="text/javascript" src="static/javascript/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="static/javascript/jquery.validate.js"></script>
    <script type="text/javascript" src="static/javascript/ammado.js"></script>
</head>

<body>
<div style="background: #BFD9DA"><#include "header.ftl" /></div>
<div style="height: 20px; clear: both;">
</div>
<div class="body">
    <#nested />
</div>
</body>
</html>
</#macro>
