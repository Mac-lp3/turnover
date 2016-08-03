'use strict';

module.exports = function ($http, $location, resultsService) {

	const self = this;
	self.periodUnits = 'minutes';
	self.totalPeriods = 0;
	self.isProbability = false;
	self.isLinear = true; // default

	// linear configuration
	self.linearTaskConfigs = [];

	// initialize with one value
	self.linearTaskConfigs.push({
		arrivalRate: 0,
		taskLength: 0,
		startPeriod: 0
	});

	// probability configuration
	self.probabilityTaskConfigs = [];

	// initialize with one value
	self.probabilityTaskConfigs.push({
		arrivalRate: 0,
		lowBound: 0,
		highBound: 0,
		taskProportion: 100,
		startPeriod: 0
	});

	self.showLinearConfig = function () {
		self.isProbability = false;
		self.isLinear = true;
	};

	self.showProbabilityConfig = function () {
		self.isProbability = true;
		self.isLinear = false;
	};

};