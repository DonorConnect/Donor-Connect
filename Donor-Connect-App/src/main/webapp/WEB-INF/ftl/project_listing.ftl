<div class="eachProject">
    <div class="projectThumbnail">

        <a href="project_detail?project_id=${project.getId()}"><img class="thumbnailImage" src=${project.getThumbnail()}></a>
    </div>

    <div class="projectSummary">
        <h4><a class="projectName" href="project_detail?project_id=${project.getId()}"> ${project.getName()}</a> </h4>

        <p class="projectBrief"> ${project.getSummary()}</p>
        <img src="image/progressbar.png" alt="progressbar">

    </div>
</div>
<div style="clear:both"></div>
