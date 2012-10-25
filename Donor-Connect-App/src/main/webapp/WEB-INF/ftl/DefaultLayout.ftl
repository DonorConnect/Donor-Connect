<#macro defaultLayout>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link rel="stylesheet" href="css/project.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/home.css">
    <title>Donors-Connect-Project</title></head>
<body>
<div><#include "header.ftl" /></div>
<div style="height: 20px; clear: both;">
</div>
<div class="body">
    <#nested />
</div>
</body>
</html>
</#macro>
