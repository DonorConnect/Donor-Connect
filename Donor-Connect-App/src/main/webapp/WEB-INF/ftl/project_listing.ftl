<div class="each-project">
    <div class="project-thumbnail">
        <a href="project_detail.ftl?project_id=${project.getId()}"><img src=${project.getThumbnail()}></a>
    </div>
    <div class="project-summary">
        <a class="project-name" href="project_detail.ftl?project_id=${project.getId()}"> ${project.getName()}</a>

        <p class="project-breif"> ${project.getSummary()}</p>

    </div>
</div>
