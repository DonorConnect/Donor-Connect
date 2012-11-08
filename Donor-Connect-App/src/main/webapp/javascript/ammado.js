/*global $ */

function AmmadoValidations() {
    this.minimumCustomAmountValidation = {
        expression: "if (VAL > 220) return true; else return false;",
        message: "Should be greater than 220"
    };
    this.maximumCustomAmountValidation = {
        expression: "if (VAL <=570000) return true; else return false;",
        message: "Should be less than 570000"
    };
    this.amountMustBeNumberValidation = {
        expression: "if (VAL.match(/^\\d+(\\.\\d{1,2}){0,1}$/)) return true; else return false;",
        message: "Should be a number"
    };

    this.amountValidations = [
        "amountMustBeNumberValidation",
        "minimumCustomAmountValidation",
        "maximumCustomAmountValidation"
    ];
}

function Ammado() {
    this.usesAmountField = function (domElement) {
        var validations = new AmmadoValidations();
        $(validations.amountValidations).each(function (index, item) {
            $(domElement).validate(item);
        });
    };
}
