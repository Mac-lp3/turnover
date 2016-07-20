'use strict';

const Chart = require('chart.js');

module.exports = function ($http, $location, resultsService) {

	const self = this;

	self.init = function () {

		// mad TODOs here
		const barChart = new Chart(null, {
			type: 'bar',
			data: {
				labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
        		datasets: [{
            		label: '# of Votes',
            		data: [12, 19, 3, 5, 2, 3],
				]}
			}
		});


	};

	self.init();

};