<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link rel="stylesheet" href="../../css/project.css">
    <link rel="stylesheet" href="../../css/main.css">
    <link rel="stylesheet" href="../../css/layout.css">
    <title>Donors-Connect-Project</title></head>
<body>
<div style="height: 100px">

</div>

<div class="row">
    <div class="col8">
        <div class="project">
            <div class="project-content">
                <h2 class="project-name">
                ${model["project"].getName()}
                </h2>

                <div class="project-image">
                    <img src="${model["project"].getImage()}" alt="children">
                </div>

                <div class="project-share">
                    <img src="image/twitter.png" alt="twitter">
                    <img src="image/facebook.png" alt="facebook">

                </div>
                <div class="project-detail">
                    <p> ${model["project"].getDescription()} </p>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>