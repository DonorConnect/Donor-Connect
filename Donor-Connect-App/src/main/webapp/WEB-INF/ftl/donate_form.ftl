<form accept-charset="utf-8" action="https://api.ammadodemo.com/v1/donate" method="post">
    <fieldset>
        <div>
            <label>Rs:</label>
            <input id="customAmount" max="1000000000000000000000" min="220" name="donationAmount" type="number"/> <br /><br />
            <button type="submit">Donate</button>
        </div>
    </fieldset>
    <input name="currencyCode" type="hidden" value="INR"/>
    <input name="beneficiaryId" type="hidden" value="${model["project"].getCharityId()}"/>
    <input name="apiKey" type="hidden" value="63B6849B-AF76-483F-8720-07D53BB2FD93"/>
    <input name="sharedSecret" type="hidden" value="KDcI89v2gdlpXjB"/>
    <input name="onSuccess" type="hidden" value="https://www.google.co.in/"/>
</form>
