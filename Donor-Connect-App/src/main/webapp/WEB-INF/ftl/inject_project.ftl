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
        <div>Project Status:
            <select name="status">
                <option value="CURRENT" >CURRENT</option>
                <option value="COMPLETED">COMPLETE</option>
            </select>
         </div>
        <div id="project_thumbnail">Project Thumbnail:<input type="text" name="thumbnail" /><br /></div>
        <div id="project-summary">Project Summary:<input type="text" name="summary" /><br /></div>

        <div id = "submit_button"> <input type="submit" /> </div>
    </form>
    <div id = "delete_projects"> <a href="delete_project.ftl">Delete All Projects</a> </div>
</div>
</@layout.defaultLayout>
