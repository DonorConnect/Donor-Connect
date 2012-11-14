<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>

<div class="row">
    <div class="pageHeader">
        <h2>
            Currently Running Projects
        </h2>
    </div>

</div>

<div class="row">
    <div class="allProjectsBody">
        <div class="col9">
            <div class="projectList">
                <#list model["allProjects"] as project>
                    <#include "project_listing.ftl" />
                </#list>
            </div>
        </div>
        <div class="col3">
            <div class="projectTag">
                TO DO PROJECT TAG
            </div>
        </div>

    </div>
</div>
</@layout.defaultLayout>
