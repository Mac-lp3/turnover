'use strict';

module.exports = function ($http, $location, resultsService) {

	const self = this;

	self.quickStartUnits = '';
	self.quickStartPeriods = '';
	self.quickStartWorkers = '';
	self.quickStartRate = '';
	self.quickStartTaskTime = '';
	self.quickStartRestTime = '';

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