'use strict';

module.exports = function ($http, $location) {

	const self = this;

	self.quickStartUnits = '';
	self.quickStartPeriods = '';
	self.quickStartWorkers = '';
	self.quickStartRate = '';

	self.quickStart = function () {
		
		$http({

			method: 'POST',
			url: 'api/simulation?type=simple',
			data: {

				quickStartUnits: self.quickStartUnits,
				quickStartPeriods: self.quickStartPeriods,
				quickStartWorkers: self.quickStartPeriods,
				quickStartRate: self.quickStartRate

			}

		}).then(function success(response){
			
		});
	};

};