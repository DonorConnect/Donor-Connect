<#import "DefaultLayout.ftl" as layout>
<@layout.defaultLayout>
<div class="row">
    <form action="inject_donation.ftl" method="POST">
        <div>Project Id:<input type="text" name="projectId" /></div>
        <div>Donation Amount:<input type="text" name="donationAmount" /><br /></div>

        <div id = "submit_button"> <input type="submit" /> </div>
    </form>
</div>
</@layout.defaultLayout>
