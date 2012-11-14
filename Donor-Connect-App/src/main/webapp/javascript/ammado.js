/*global $ */

function AmmadoValidations() {
    this.minimumCustomAmountValidation = {
        expression: "if (VAL > 220) return true; else return false;",
        message: "Donation amount should be greater than 220."
    };
    this.maximumCustomAmountValidation = {
        expression: "if (VAL <=570000) return true; else return false;",
        message: "Donation amount should be less than 570000."
    };
    this.amountMustBeNumberValidation = {
        expression: "if (VAL.match(/^\\d+(\\.\\d{1,2}){0,1}$/)) return true; else return false;",
        message: "Please enter a valid number."
    };

    this.emailMustBeValid = {
        expression: "if(VAL.match(/^[\\._a-zA-Z0-9!#\\$%&'\\*\\+\\-/=\\?\\^`\\{\\|\\}~]+@[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)+$/)) return true; else return false;",
        message: "Please enter a valid email address."
    }
}

function Ammado() {
    this.usesAmountField = function (domElement) {
        var validations = new AmmadoValidations();
        $(domElement).validate(validations.amountMustBeNumberValidation);
        $(domElement).validate(validations.minimumCustomAmountValidation);
        $(domElement).validate(validations.maximumCustomAmountValidation);
    };

    this.usesEmailField = function (domElement) {
        var validations = new AmmadoValidations();
        $(domElement).validate(validations.emailMustBeValid);
    };
}
