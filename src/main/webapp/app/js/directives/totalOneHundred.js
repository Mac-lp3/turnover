'use strict';

module.exports = function () {

	return {
        restrict: "A",
         
        require: "ngModel",
         
        link: function(scope, element, attributes, ngModel) {

        	ngModel.$validators.totalOneHundred = function(modelValue) {
              
              //true or false based on custome dir validation
              return true;

        	};
            
        }
    };

};