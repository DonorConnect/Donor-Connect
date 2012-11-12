<div class="eachProject">
    <div class="projectThumbnail">

        <a href="project?id=${project.getId()}"><img class="thumbnailImage" src=static/${project.getThumbnail()}></a>

        <div class="thumbnailProgress">
            <progress value="${project.donationPercentage()}" max="100"></progress>
            <span style="font-size: 12px">${project.donationPercentage()}%</span>
            <table>
                <th>${project.targetAmount}<br><span class="progressTitle">Target</span></th>
                <th>${project.totalDonation()}<br><span class="progressTitle">Pledged</span> </th>
                <th> ${project.leftDays()}<br><span class="progressTitle">Days</span></th>
            </table>

        </div>
    </div>

    <div class="projectSummary">
        <h4><a class="projectName" href="project?id=${project.getId()}"> ${project.getName()}</a> </h4>

        <p class="projectBrief"> ${project.getSummary()}</p>

    </div>
</div>
<div style="clear:both"></div>
