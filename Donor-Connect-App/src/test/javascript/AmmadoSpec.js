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

});
