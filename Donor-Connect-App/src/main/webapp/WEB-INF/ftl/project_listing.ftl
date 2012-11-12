<div class="eachProject">
    <div class="projectThumbnail">

        <a href="project?id=${project.getId()}"><img class="thumbnailImage" src=static/${project.getThumbnail()}></a>

        <div class="thumbnailProgress">
            sdfd<progress value="${project.getDonationPercentage()}" max="100"></progress>
        </div>
    </div>

    <div class="projectSummary">
        <h4><a class="projectName" href="project?id=${project.getId()}"> ${project.getName()}</a> </h4>

        <p class="projectBrief"> ${project.getSummary()}</p>

    </div>
</div>
<div style="clear:both"></div>
