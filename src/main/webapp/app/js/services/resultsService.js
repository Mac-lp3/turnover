'user strict';

/*
 * Maintains results from the simulation between partials/controllers.
 */
module.exports = function($location) {

	// results to pass between pages
	this.simulationResults = {};

	// list of data to maintain 
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

	this.setSimulationResults = (data) => {
		this.simulationResults = data;
		$location.path('/results');
	};

	this.getSimulationResults = () => {
		return this.simulationResults;
	};

};