'use strict';

module.exports = function ($http, $location, resultsService) {

	const self = this;

	self.quickStartUnits = 'minutes';
	self.quickStartPeriods = 0;
	self.quickStartWorkers = 0;
	self.quickStartRate = 0;
	self.quickStartTaskTime = 0;
	self.quickStartRestTime = 0;

	self.quickStart = function () {
		
		$http({

			method: 'POST',
			url: 'api/simulation?type=simple',
			data: {
				quickStartUnits: self.quickStartUnits,
				quickStartPeriods: self.quickStartPeriods,
				quickStartWorkers: self.quickStartPeriods,
				quickStartRate: self.quickStartRate,
				quickStartTaskTime: self.quickStartTaskTime,
				quickStartRestTime: self.quickStartRestTime
			}

		}).then(function success(response){

			resultsService.setSimulationResults(response.data);
		});
	};

};