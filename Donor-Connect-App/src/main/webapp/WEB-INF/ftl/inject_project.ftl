<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>
<#if created_project_id??>
    Project created with ID ${created_project_id}
</#if>
<div class="row">
    <form action="inject_project.ftl" method="POST">
        Project Name:<input type="text" name="name" /><br />
        Project Description:<input type="text" name="description" /><br />
        Project Image:<input type="text" name="img" /><br />
        <input type="submit" />
    </form>
    <a href="delete_project.ftl">Delete All Projects</a>
</div>
</@layout.defaultLayout>
