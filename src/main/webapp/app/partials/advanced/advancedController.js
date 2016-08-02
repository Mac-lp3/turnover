'use strict';

module.exports = function ($http, $location, resultsService) {

	const self = this;
	self.periodUnits = 'minutes';
	self.totalPeriods = 0;
	self.isProbability = false;
	self.isLinear = true; // default

	self.showLinearConfig = () => {
		self.isProbability = false;
		self.isLinear = true;
	};

	self.showProbabilityConfig = () => {
		self.isProbability = true;
		self.isLinear = false;
	};

};