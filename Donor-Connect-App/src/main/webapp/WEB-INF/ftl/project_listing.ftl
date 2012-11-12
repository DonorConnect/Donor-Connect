<div class="eachProject">
    <div class="projectThumbnail">

        <a href="project?id=${project.getId()}"><img class="thumbnailImage" src=static/${project.getThumbnail()}></a>

    </div>

    <div class="projectSummary">
        <h4><a class="projectName" href="project?id=${project.getId()}"> ${project.getName()}</a> </h4>

        <p class="projectBrief"> ${project.getSummary()}</p>
        <div class="thumbnailProgress">
            <progress value="${project.getDonationPercentage()}" max="100"></progress>
            <span style="font-size: 12px">${project.getDonationPercentage()}%</span>
            <table>
                <th><span class="progressTitle">Target: </span>${project.targetAmount}</th>
                <th><span class="progressTitle">Pledged: </span>${project.totalDonation()} </th>
                <th><span class="progressTitle">Days: </span>${project.leftDays()}</th>
            </table>

        </div>
    </div>
</div>
<div style="clear:both"></div>
