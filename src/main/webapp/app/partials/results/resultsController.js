'use strict';

const $ = require('jquery');
const Chart = require('chart.js');

module.exports = function ($http, $location, resultsService) {

	const self = this;
	self.averageWaitTime = 0;
	self.averageTasksPerWorker = 0;

	self.buildChartData = () => {

		// Already sorted by period of completion
		const tasks = resultsService.getSimulationResults().completedTasks;
		let taskLables = [];
		let taskWaitTime = [];

		// Build label and wait time list for tasks
		let totalWait = 0;
		for (let i = 0; i < tasks.length; ++i) {
			taskLables.push(tasks[i].arrivalPeriod);
			taskWaitTime.push(tasks[i].periodsInQueue);
			totalWait += tasks[i].periodsInQueue;
		}

		self.averageWaitTime = totalWait / tasks.length;

		const taskBarChart = new Chart($('#taskBarChart'), {
		 	type: 'bar',
		 	data: {
		 		labels: taskLables,
			 	datasets: [
			 		{
			 			label: 'Wait Time',
			 			data: taskWaitTime
			 		}
			 	]
		 	},
		 	options: {
		 		scales: {
		 			xAxes: [{
		 				display: false
		 			}],
		 			yAxes: [{
		 				scaleLabel: {
		 					display: true,
		 					labelString: 'Wait Time'
		 				}
		 			}]
		 		}
		 	}
		});

		// get the workers list for the simulation
		const workers = resultsService.getSimulationResults().workers;
		let workerLables = [];
		let workerTotals = [];

		// Count tasks completed and assign values/labels
		let totalTasksCompleted = 0;
		for (let i = 0; i < workers.length; ++i) {
			workerLables.push('Worker ' + (i + 1));
			workerTotals.push(workers[i].tasksCompleted);
			totalTasksCompleted += workers[i].tasksCompleted;
		}

		// calculate average
		self.averageTasksPerWorker = totalTasksCompleted / workers.length

		const workerPieChart = new Chart($('#workerPieChart'), {
		 	type: 'pie',
		 	data: {
		 		labels: workerLables,
		 		datasets: [
		 			{
		 				label: 'Tasks Completed',
		 				data: workerTotals
		 			}
		 		]
		 	},
		 	options: {
		 		legend: {
		 			display: false
		 		}
		 	}
		 });

		const workerBarChart = new Chart($('#workerBarChart'), {
		 	type: 'bar',
		 	data: {
		 		labels: workerLables,
		 		datasets: [
		 			{
		 				label: 'Tasks Completed',
		 				data: workerTotals
		 			}
		 		]
		 	},
		 	options: {
		 		scales: {
		 			yAxes: [{
		 				ticks: {
		 					suggestedMin: 0,
		 					beginAtZero: true
		 				}
		 			}]
		 		}
		 	}
		});

	};

	self.buildChartData();

};