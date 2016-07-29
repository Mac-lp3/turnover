'use strict';

const $ = require('jquery');
const Chart = require('chart.js');

module.exports = function ($http, $location, resultsService) {

	const self = this;

	self.init = function() {

		// Already sorted by period of completion
		const tasks = resultsService.getSimulationResults().completedTasks;
		let taskLables = [];
		let taskWaitTime = [];

		// Build label and wait time list for tasks
		for (let i = 0; i < tasks.length; ++i) {
			taskLables.push(tasks[i].arrivalPeriod);
			taskWaitTime.push(tasks[i].periodsInQueue);
		}

		const taskBarChartContext = $('#taskBarChart');

		const taskBarChart = new Chart(taskBarChartContext, {
		 	type: 'bar',
		 	data: {
		 		labels: taskLables,
			 	datasets: [
			 		{
			 			data: taskWaitTime
			 		}
			 	]
		 	}
		});

		const workers = resultsService.getSimulationResults().workers;
		let workerLables = [];
		let workerTotals = [];

		for (let i = 0; i < workers.length; ++i) {
			workerLables.push(i);
			workerTotals.push(workers[i].tasksCompleted);
		}

		const workerPieChartContext = $('#workerPieChart');

		const workerPieChart = new Chart(workerPieChartContext, {
		 	type: 'pie',
		 	data: {
		 		labels: workerLables,
		 		datasets: [
		 			{
		 				data: workerTotals
		 			}
		 		]
		 	}
		 });

	};

	self.init();

};