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
		this.isProbability = false;
		this.isLinear = true;
	};

	this.addLinearConfig = () => {
		
		this.linearTaskConfigs.push({
			arrivalRate: 0,
			taskLength: 0,
			startPeriod: 0
		});

		if (this.linearTaskConfigs.length <= 1) {

			this.showLinearRm = false;

		} else {

			this.showLinearRm = true;
		}
	};

	this.removeLinearConfig = () => {

		this.linearTaskConfigs.pop();

		// TODO
		if (this.linearTaskConfigs.length <= 1) {

			// something went wrong either way.
			this.showLinearRm = false;

		} else {

			this.showLinearRm = true;
		}

	};

	this.showProbabilityConfig = () => {

		this.isProbability = true;
		this.isLinear = false;

	};

	this.addProbabilityConfig = () => {

		this.probabilityTaskConfigs.push({
			arrivalRate: 0,
			lowBound: 0,
			highBound: 0,
			taskProportion: 100,
			startPeriod: 0
		});

		if (this.probabilityTaskConfigs.length <= 1) {

			// something went wrong either way
			this.showProbabilityRm = false;
		} else {
			this.showProbabilityRm = true;
		}

	};

	this.removeProbabilityConfig = () => {

		this.probabilityTaskConfigs.pop();

		if (this.probabilityTaskConfigs.length <= 1) {

			// something went wrong either way.
			this.showProbabilityRm = false;

		} else {

			this.showProbabilityRm = true;
		}

	};

};