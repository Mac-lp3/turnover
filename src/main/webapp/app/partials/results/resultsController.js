'use strict';

const $ = require('jquery');
const Chart = require('chart.js');

module.exports = function ($http, $location, resultsService) {

	this.averageWaitTime = 0;
	this.averageTasksPerWorker = 0;
	this.workerColorMap = {};
	this.taskColorMap = {};

	this.advancedConfig = () => {

        $location.path('/advanced');

    };

	/*
	 * Get unique grayscale for given seed
	 */
	this.getGrayScale = (seed) => {

		// if a color has not been generated for this seed, do so
		if (this.taskColorMap[seed] == null) {
			
			const value = Math.random() * 0xFF | 0;
			const grayscale = (value << 16) | (value << 8) | value;
			const color = '#' + grayscale.toString(16);
			
			this.taskColorMap[seed] = color;
		}

		// return the color
		return this.taskColorMap[seed];
	};

	this.buildChartData = () => {

		// Already sorted by period of completion
		const tasks = resultsService.getSimulationResults().completedTasks;
		let taskLables = [];
		let taskWaitTime = [];
		let taskBarColors = [];

		// Build label and wait time list for tasks
		let totalWait = 0;
		for (let i = 0; i < tasks.length; ++i) {
			taskLables.push('Arrival: ' + tasks[i].arrivalPeriod);
			taskWaitTime.push(tasks[i].periodsInQueue);
			taskBarColors.push(this.getGrayScale(tasks[i].configHashCode));
			totalWait += tasks[i].periodsInQueue;
		}

		this.averageWaitTime = totalWait / tasks.length;

		const taskBarChart = new Chart($('#taskBarChart'), {
		 	type: 'bar',
		 	data: {
		 		labels: taskLables,
			 	datasets: [
			 		{
			 			label: 'Wait Time',
			 			data: taskWaitTime,
			 			backgroundColor: taskBarColors
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
		let workerColors =[];

		this.workerColorMap[-1] = '#333333';
		this.workerColorMap[0] = '#808080';
		this.workerColorMap[1] = '#cccccc';

		// Count tasks completed and assign values/labels
		let totalTasksCompleted = 0;
		let tempWorkerLabel = '';
		let srCount = 0;
		let jrCount = 0;
		let stCount = 0;
		for (let i = 0; i < workers.length; ++i) {
		
			if (workers[i].periodsToCompleteTask == -1) {
				tempWorkerLabel = 'Senior Worker ' + ++srCount;
			} else if (workers[i].periodsToCompleteTask == 1) {
				tempWorkerLabel = 'Junior Worker ' + ++jrCount;
			} else {
				tempWorkerLabel = 'Worker ' + ++stCount;
			}

			workerLables.push(tempWorkerLabel);
			workerTotals.push(workers[i].tasksCompleted);
			workerColors.push(this.workerColorMap[workers[i].periodsToCompleteTask]);
			totalTasksCompleted += workers[i].tasksCompleted;
		}

		// calculate average
		this.averageTasksPerWorker = totalTasksCompleted / workers.length

		const workerPieChart = new Chart($('#workerPieChart'), {
		 	type: 'pie',
		 	data: {
		 		labels: workerLables,
		 		datasets: [
		 			{
		 				label: 'Tasks Completed',
		 				data: workerTotals,
		 				backgroundColor: workerColors
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
		 				data: workerTotals,
		 				backgroundColor: workerColors
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

	this.buildChartData();

};