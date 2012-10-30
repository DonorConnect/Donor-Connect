<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>
<#if created_project_id??>
    <div id="success_info"> Project created with ID <span id="project_id">${created_project_id}</span></div>
</#if>
<div class="row">
    <form action="inject_project.ftl" method="POST">
        <div id="project_name">Project Name:<input type="text" name="name" /><br /></div>
        <div id="project_description">Project Description:<input type="text" name="description" /><br /></div>
        <div id="project_image">Project Image:<input type="text" name="img" /><br /></div>
        <input type="submit" />
    </form>
    <a href="delete_project.ftl">Delete All Projects</a>
</div>
</@layout.defaultLayout>
