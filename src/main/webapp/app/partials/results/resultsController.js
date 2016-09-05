'use strict';

const $ = require('jquery');
const Chart = require('chart.js');

module.exports = function ($http, $location, resultsService) {

	this.averageWaitTime = 0;
	this.averageTasksPerWorker = 0;
	this.workerColorMap = {};
	this.taskColorMap = {};
	this.hexColorList = ['#d9d9d9', '#bfbfbf', '#a6a6a6', 
    	'#8c8c8c', '#737373', '#595959', '#404040', '#262626'];
    this.hexColorIndex = 0;

	this.advancedConfig = () => {

        $location.path('/advanced');

    };

	/*
	 * Get unique grayscale for given seed
	 */
	this.getGrayScale = (seed) => {

		// if a color has not been generated for this seed, do so
		if (this.taskColorMap[seed] == null) {
			
			this.taskColorMap[seed] = this.hexColorList[this.hexColorIndex];
			++this.hexColorIndex;
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


		/*
		 * Build task dataset objects based on the has configuration hash code.
		 */
		let taskDataSets = [];
		let tempHashCode = 0;
		let totalWaitTime = 0;
		let tempTaskBarLabels = [];
		let tempWaitTimes = [];
		let tempBGColors = [];
		let taskDataSetLable = 'Wait Time';
		let totalIterations = tasks.length - 1;

		for (let i = 0; i < tasks.length; ++i) {
			
			// If this task begins a new set of 
			if (i == 0) {

				// first roung through the loop. Cachse the hash
				tempHashCode = tasks[i].configHashCode;

				// push task values to the temp arrays
				tempTaskBarLabels.push('Arrival: ' + tasks[i].arrivalPeriod);
				tempWaitTimes.push(tasks[i].periodsInQueue);
				tempBGColors.push(this.getGrayScale(tasks[i].configHashCode));

				// iterate the total time
				totalWait += tasks[i].periodsInQueue;


			} else if (tempHashCode != tasks[i].configHashCode) {

				// next hash code reached. Create dataset object with old data
				taskDataSets.push({
					label: taskDataSetLable,
					data: this.cloneArray(tempWaitTimes),
					backgroundColor: this.cloneArray(tempBGColors)
				});

				// reset data arrays
				tempWaitTimes = [];
				tempBGColors = [];

				// push values to arrays
				tempTaskBarLabels.push('Arrival: ' + tasks[i].arrivalPeriod);
				tempWaitTimes.push(tasks[i].periodsInQueue);
				tempBGColors.push(this.getGrayScale(tasks[i].configHashCode));

				// update current hashcode
				tempHashCode = tasks[i].configHashCode;

				// iterate the totalWaitTime
				totalWait += tasks[i].periodsInQueue;

			} else if (i == totalIterations) {

				// end of task list reached. push final values
				tempTaskBarLabels.push('Arrival: ' + tasks[i].arrivalPeriod);
				tempWaitTimes.push(tasks[i].periodsInQueue);
				tempBGColors.push(this.getGrayScale(tasks[i].configHashCode));

				// create final dataset object
				taskDataSets.push({
					label: taskDataSetLable,
					data: this.cloneArray(tempWaitTimes),
					backgroundColor: this.cloneArray(tempBGColors)
				});

				// iterate total wait time.
				totalWait += tasks[i].periodsInQueue;

			} else {
				
				// task is in same data set - just push values
				tempTaskBarLabels.push('Arrival: ' + tasks[i].arrivalPeriod);
				tempWaitTimes.push(tasks[i].periodsInQueue);
				tempBGColors.push(this.getGrayScale(tasks[i].configHashCode));

				// iterate total time
				totalWait += tasks[i].periodsInQueue;
			}

		}

		this.averageWaitTime = totalWait / tasks.length;

		// const taskBarChart = new Chart($('#taskBarChart'), {
		//  	type: 'bar',
		//  	data: {
		//  		labels: tempTaskBarLabels,
		// 	 	datasets: taskDataSets
		//  	},
		//  	options: {
		//  		scales: {
		//  			xAxes: [{
		//  				stacked: true,
		//  				barPercentage: .3,
		//  				display: false
		//  			}],
		//  			yAxes: [{
		//  				scaleLabel: {
		//  					display: true,
		//  					labelString: 'Wait Time'
		//  				}
		//  			}]
		//  		}
		//  	}
		// });

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
		 				stacked: true,
		 				barPercentage: .3,
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

	this.cloneArray = (arrayToClone) => {

	    let clone = [];

	    for(let i = 0; i < arrayToClone.length; ++i) {

	    	clone.push(arrayToClone[i]);

	    }

	    return clone;
	};

	this.buildChartData();

};