<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>

<div class="row">
    <div class="project">
        <div class="col8">

            <div class="projectContent">
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
        <div class="col4">
            #TODO Contact Information
        </div>
        <div style="clear:both"></div>
    </div>

</div>

</@layout.defaultLayout>
