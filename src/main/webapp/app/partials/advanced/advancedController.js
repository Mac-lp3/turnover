'use strict';

module.exports = function ($scope, $http, $location, resultsService) {

	this.periodUnits = 'minutes';
	this.totalPeriods = 0;
	this.isProbability = false;
	this.showProbabilityRm = false;
	this.isLinear = true; // default
	this.showLinearRm = false;
	this.showWorkerRm = false;
	this.workerConfigs = [];
	this.linearTaskConfigs = [];
	this.probabilityArrivalRate = 0;
	this.probabilityTaskConfigs = [];

	this.addTaskConfig = () => {

		if (this.isLinear) {

			this.linearTaskConfigs.push({
				arrivalRate: 0,
				taskLength: 0,
				startPeriod: 0,
				endPeriod: 0
			});

			if (this.linearTaskConfigs.length <= 1) {

				this.showLinearRm = false;

			} else {

				this.showLinearRm = true;
			}

		} else {

			// If this is the only task config, set proportion to 100. 0 otherwise
			let tempProportion = this.probabilityTaskConfigs.length > 0 ? 0 : 100;

			this.probabilityTaskConfigs.push({
				arrivalRate: this.probabilityArrivalRate,
				lowBound: 0,
				highBound: 0,
				proportion: tempProportion,
				startPeriod: 0,
				endPeriod: 0
			});

			if (this.probabilityTaskConfigs.length <= 1) {

				// something went wrong either way
				this.showProbabilityRm = false;
			} else {
				this.showProbabilityRm = true;
			}

			this.validateProportions();

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

			this.validateProportions();

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
			stopPeriod: 0,
			restTime: 0
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

	this.validateProportions = () => {

		let isValid = false;
	
		let total = 0;	

		this.probabilityTaskConfigs.map((config) => {
			total += config.proportion ? config.proportion : 0; 
		});

		if (total === 100) {
			isValid = true;
		}

		return isValid;

	};

	this.postForm = () => {

		let taskConfigsToPost = [];
		let url = 'api/simulation?type=';

		if (this.isLinear) {

			taskConfigsToPost = this.linearTaskConfigs;
			url = url + 'linear';

		} else if (this.isProbability) {

			taskConfigsToPost = this.probabilityTaskConfigs;
			url = url + 'probability';

		} else {
			// should never get here
		}	

		$http({

			method: 'POST',
			url: url,
			data: {
				clockUnits: this.periodUnits,
				totalPeriods: this.totalPeriods,
				workerConfigurations: this.workerConfigs,
				taskConfigurations: taskConfigsToPost
			}

		}).then(function success(response){

			resultsService.setSimulationResults(response.data);

		});

	};

	$scope.watch

	// initialize
	this.addWorkerConfig();
	this.addTaskConfig();
	this.showProbabilityConfig();
	this.addTaskConfig();
	this.showLinearConfig();

};