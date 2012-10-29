<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>

<div class="row">
    <div class="col8" style="background-color: #ffffff;">

        <h2> Currently Running Projects </h2>

        <#list model["allProjects"] as project>

            <#include "project_listing.ftl" />

            <br>

        </#list>
    </div>
</div>

</@layout.defaultLayout>
