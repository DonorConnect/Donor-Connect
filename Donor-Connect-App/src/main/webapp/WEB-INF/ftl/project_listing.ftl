<div class="eachProject">
    <div class="projectThumbnail">

        <a href="project/${project.getId()}"><img class="thumbnailImage" src=static/${project.getThumbnail()}></a>
    </div>

    <div class="projectSummary">
        <h4><a class="projectName" href="project/${project.getId()}"> ${project.getName()}</a> </h4>

        <p class="projectBrief"> ${project.getSummary()}</p>
        <img src="static/image/progressbar.png" alt="progressbar">

    </div>
</div>
<div style="clear:both"></div>
