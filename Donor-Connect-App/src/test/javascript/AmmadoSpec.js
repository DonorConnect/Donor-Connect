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


});
