<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>
<div class="row">
    <div class="projectHeader">
        <h3> Currently Running Projects </h3>
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
                here is tag
            </div>
        </div>
        <div style="clear:both">

        </div>
    </div>
</div>
</@layout.defaultLayout>
