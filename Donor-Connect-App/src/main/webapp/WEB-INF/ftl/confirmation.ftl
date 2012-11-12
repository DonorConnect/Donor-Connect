<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>
<div class="row">
    <div class="projectHeader">
        <h3>Confirmation Page</h3>
    </div>
    <div>
        <p>Thank you for your donation!</p>
        <a href="/Donor-Connect-App/project?id=${model["projectId"]}">See my project.</a>
    </div>
</div>
</@layout.defaultLayout>
