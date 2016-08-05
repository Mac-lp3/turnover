'use strict';

module.exports = function ($http, $location, resultsService) {

	this.periodUnits = 'minutes';
	this.totalPeriods = 0;
	this.isProbability = false;
	this.showProbabilityRm = false;
	this.isLinear = true; // default
	this.showLinearRm = false;
	this.showWorkerRm = false;

	// Worker configurations
	this.workerConfigs = [];

	// init with one value
	this.workerConfigs.push({
		total: 0,
		additionalTime: '0',
		arrivalPeriod: 0,
		stopPeriod: 0
	});

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

	this.addTaskConfig = () => {

		if (this.isLinear) {

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

		} else {

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

		}
	};

	this.removeTaskConfig = () => {

		if (this.isLinear) {

			this.linearTaskConfigs.pop();

			if (this.linearTaskConfigs.length <= 1) {

				// something went wrong either way.
				this.showLinearRm = false;

			} else {

				this.showLinearRm = true;
			}

		} else {

			this.probabilityTaskConfigs.pop();

			if (this.probabilityTaskConfigs.length <= 1) {

				// something went wrong either way.
				this.showProbabilityRm = false;

			} else {

				this.showProbabilityRm = true;
			}

		}

	};

	this.showLinearConfig = () => {
		this.isProbability = false;
		this.isLinear = true;
	};

	this.showProbabilityConfig = () => {

		this.isProbability = true;
		this.isLinear = false;

	};

	this.addWorkerConfig = () => {

		this.workerConfigs.push({
			total: 0,
			additionalTime: '0',
			arrivalPeriod: 0,
			stopPeriod: 0
		});

		if (this.workerConfigs.length <= 1) {

			// something went wrong either way.
			this.showWorkerRm = false;

		} else {

			this.showWorkerRm = true;
		}

	};

	this.removeWorkerConfig = () => {

		this.workerConfigs.pop();

		if (this.workerConfigs.length <= 1) {

			// something went wrong either way.
			this.showWorkerRm = false;

		} else {

			this.showWorkerRm = true;
		}
	};

	// May need this later
	// this.validateProportions = () => {

	// 	let isValid = false;
	
	// 	let total= 0;	
	// 	for (let i = 0; i < this.probabilityTaskConfigs.length; ++i) {

	// 		total =+ this.probabilityTaskConfigs[i].taskProportion ? 
	// 			this.probabilityTaskConfigs[i].taskProportion : 0;
	// 	}

	// 	if (total === 100) {
	// 		isValid = true;
	// 	}

	// 	return isValid;

	// };

	this.postForm = () => {

		if (this.isLinear) {

			$http({
				method: 'POST',
				url: 'api/simulation?type=linear',
				data: {
					taskConfigs: this.linearTaskConfigs,
					workerConfigs: this.workerConfigs
				}
			}).then(function success(response) {

			});

		} else if (this.isProbability) {

		} else {
			// should never get here
		}

	};


};