<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>

<div class="row">
    <div class="col8" style="background-color: #ffffff;">

        <div id="Project_Header">
            <h2> Currently Running Projects </h2>
        </div>

        <#list model["allProjects"] as project>

            <#include "project_listing.ftl" />
        </#list>
    </div>
</div>

</@layout.defaultLayout>
