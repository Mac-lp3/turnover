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
				clockUnits: self.quickStartUnits,
				totalPeriods: self.quickStartPeriods,
				workerConfigurations: [{
					total: self.quickStartWorkers,
					additionalTime: 0,
					restTime: self.quickStartRestTime,
					arrivalPeriod: 0,
					stopPeriod: 0
				}],
				taskConfigurations: [{
					rate: self.quickStartRate,
					length: self.quickStartTaskTime,
					startPeriod: 0,
					endPeriod: 0,
					lowBound: 0,
					highBound: 0,
					proportion: 100
				}]
			}

		}).then(function success(response){

			resultsService.setSimulationResults(response.data);
		});
	};

};