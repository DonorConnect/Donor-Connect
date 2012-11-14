describe("Ammado Validations", function() {
    beforeEach(function(){
        validations = new AmmadoValidations();
    });

    it("should return false when number is less than 220", function () {
        var expressionToTest = validations.minimumCustomAmountValidation.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("20")).toBeFalsy();
    });

    it("should return true when number is greater than 220", function () {
        var expressionToTest = validations.minimumCustomAmountValidation.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("320")).toBeTruthy();
    });

    it("should return false when number is greater than 570000", function() {
        var expressionToTest = validations.maximumCustomAmountValidation.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("570001")).toBeFalsy();
    });

    it("should return true when number is decimal", function() {
        var expressionToTest = validations.maximumCustomAmountValidation.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("500.21")).toBeTruthy();
    });

    it("should return false when number has more than two decimal", function() {
        var expressionToTest = validations.amountMustBeNumberValidation.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("500.214")).toBeFalsy();
    });

    it("should return true when number is exactly 570000", function() {
        var expressionToTest = validations.maximumCustomAmountValidation.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("570000")).toBeTruthy();
    });

    it("should return false when special chars are entered", function() {
        var expressionToTest = validations.amountMustBeNumberValidation.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("ff")).toBeFalsy();
        expect(validate("$1000.00")).toBeFalsy();
    });

    it("should return false when nothing is entered",function() {
        var expressionToTest = validations.emailMustBeValid.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("")).toBeFalsy();
    });

    it("should return false when no @ is present in the email",function() {
        var expressionToTest = validations.emailMustBeValid.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("abcgmail.com")).toBeFalsy();
    });

    it("should return true when email is valid",function() {
        var expressionToTest = validations.emailMustBeValid.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("abc@gmail.com")).toBeTruthy();
    });

    it("should return false when no . is present in the email after the @ ",function() {
        var expressionToTest = validations.emailMustBeValid.expression;

        eval("var validate = function(VAL){" + expressionToTest + "}");

        expect(validate("abc@gmailcom")).toBeFalsy();
    });
});