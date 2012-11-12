<script type="text/javascript" src="static/javascript/project_details_bindings.js"></script>
<form accept-charset="utf-8" action="https://api.ammadodemo.com/v1/donate" method="post">
    <div>
        <label>Rs:</label>
        <input id="donationAmount" name="donationAmount" type="text"/> <br/><br/>
        <button id="donateButton" type="submit">Donate</button>
    </div>
    <input id="currencyCode" name="currencyCode" type="hidden" value="INR"/>
    <input id="beneficiaryId" name="beneficiaryId" type="hidden" value="${model["project"].getCharityId()}"/>
    <input id="apiKey" name="apiKey" type="hidden" value="63B6849B-AF76-483F-8720-07D53BB2FD93"/>
    <input id="sharedSecret" name="sharedSecret" type="hidden" value="KDcI89v2gdlpXjB"/>
    <input id="onSuccess" name="onSuccess" type="hidden" value="http://www.donorsconnect.com:8080/Donor-Connect-App/confirmation.ftl?id=${model["project"].getId()}"/>
</form>