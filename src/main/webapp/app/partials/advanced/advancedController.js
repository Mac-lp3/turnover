'use strict';

module.exports = function ($http, $location, resultsService) {

	this.periodUnits = 'minutes';
	this.totalPeriods = 0;
	this.isProbability = false;
	this.showProbabilityRm = false;
	this.isLinear = true; // default
	this.showLinearRm = false;

	// linear configuration
	this.linearTaskConfigs = [];

	// initialize with one value
	this.linearTaskConfigs.push({
		arrivalRate: 0,
		taskLength: 0,
		startPeriod: 0
	});

	// probability configuration
	this.probabilityTaskConfigs = [];

	// initialize with one value
	this.probabilityTaskConfigs.push({
		arrivalRate: 0,
		lowBound: 0,
		highBound: 0,
		taskProportion: 100,
		startPeriod: 0
	});

	this.showLinearConfig = () => {
		isProbability = false;
		isLinear = true;
	};

	this.addLinearConfig = () => {
		// TODO
		if (linearTaskConfigs.length <= 1) {
			showLinearRm = false;
		} else {
			showLinearRm = true;
		}
	};

	this.removeLinearConfig = () => {

		// TODO
		if (linearTaskConfigs.length <= 1) {

			// something went wrong either way.
			showLinearRm = false;
		} else {

			showLinearRm = true;
		}
	};

	this.showProbabilityConfig = () => {
		isProbability = true;
		isLinear = false;
	};

	this.addProbabilityConfig = () => {

		// TODO
		if (probabilityTaskConfigs.length <= 1) {

			// something went wrong either way
			showProbabilityRm = false;
		} else {
			showProbabilityRm = true;
		}
	};

	this.removeProbabilityConfig = () => {

		// TODO
		if (probabilityTaskConfigs.length <= 1) {

			// something went wrong either way.
			showProbabilityRm = false;
		} else {

			showProbabilityRm = true;
		}
	};

};