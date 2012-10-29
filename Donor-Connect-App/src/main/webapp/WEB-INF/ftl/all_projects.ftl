<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>

<#list model["allProjects"] as project>
    Name: ${project.getName()}
</#list>

</@layout.defaultLayout>
