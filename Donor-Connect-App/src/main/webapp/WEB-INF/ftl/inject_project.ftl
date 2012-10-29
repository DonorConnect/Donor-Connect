<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>
<div class="row">
    <form action="inject_project.ftl" method="POST">
        Project Name:<input type="text" name="name" /><br />
        Project Description:<input type="text" name="description" /><br />
        Project Image:<input type="text" name="img" /><br />
        <input type="submit" />
    </form>

</div>
</@layout.defaultLayout>
