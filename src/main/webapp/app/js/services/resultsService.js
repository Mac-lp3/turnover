'user strict';

/*
 * Maintains results from the simulation between partials/controllers.
 */
module.exports = function($location) {

	const self = this;
	this.simulationResults = {};

	self.setSimulationResults = function(data) {
		this.simulationResults = data;
		$location.path('/results');
	};

	self.getSimulationResults = function() {
		return this.simulationResults;
	};

};