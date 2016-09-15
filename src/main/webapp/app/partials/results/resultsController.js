'use strict';

const $ = require('jquery');
const Chart = require('chart.js');

module.exports = function ($http, $location, resultsService) {

	this.totalPeriodsBuffering = 0;
	this.averageWaitTime = 0;
	this.averageTasksPerWorker = 0;
	this.workerColorMap = {};
	this.taskColorMap = {};
	this.hexColorList = ['#d9d9d9', '#a6a6a6', '#404040', '#bfbfbf',  
    	 '#262626', '#8c8c8c', '#737373', '#595959'];
    this.hexColorIndex = 0;
    this.totalCompletedTasks = 0;
    this.taskConfigurationData = [];
    this.tempConfigArray = [];

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
		let currentTaskConfigurationCode = 0;
		let startTaskConfigurationIndex = 0;

		for (let i = 0; i < tasks.length; ++i) {

			if (i == 0) {

				// First iteration - set the current config id
				currentTaskConfigurationCode = tasks[i].configHashCode;
				this.taskConfigurationData.length = 0;

			} else if (currentTaskConfigurationCode != tasks[i].configHashCode) {

				if (this.taskConfigurationData.hasOwnProperty(currentTaskConfigurationCode)) {
					this.taskConfigurationData[currentTaskConfigurationCode]
					  .totalCompleted += (i - startTaskConfigurationIndex);
				} else {

					// create a config data object
					this.taskConfigurationData[currentTaskConfigurationCode] = {
						configurationCode: currentTaskConfigurationCode,
						colorCode: this.getGrayScale(currentTaskConfigurationCode),
						startPeriod: startTaskConfigurationIndex,
						endPeriod: i,
						totalCompleted: i - startTaskConfigurationIndex,
						taskTime: tasks[i].serviceTimeRequired
					};
				}

				// next config reached - update current config id
				currentTaskConfigurationCode = tasks[i].configHashCode;

				// update start index
				startTaskConfigurationIndex = i;

			} else if (i == tasks.length - 1) {

				// end of task list - create final task data object
				if (this.taskConfigurationData.hasOwnProperty(currentTaskConfigurationCode)) {
					this.taskConfigurationData[currentTaskConfigurationCode]
					  .totalCompleted += (i - startTaskConfigurationIndex);
				} else {

					// create a config data object
					this.taskConfigurationData[currentTaskConfigurationCode] ={
						configurationCode: currentTaskConfigurationCode,
						colorCode: this.getGrayScale(currentTaskConfigurationCode),
						startPeriod: startTaskConfigurationIndex,
						endPeriod: i,
						totalCompleted: i - startTaskConfigurationIndex,
						taskTime: tasks[i].serviceTimeRequired
					};
				}
			}

			// add task data to tracked values
			taskLables.push('Arrival: ' + tasks[i].arrivalPeriod);
			taskWaitTime.push(tasks[i].periodsInQueue);
			taskBarColors.push(this.getGrayScale(currentTaskConfigurationCode));
			totalWait += tasks[i].periodsInQueue;
		}

		for (let obj in this.taskConfigurationData) {
			this.tempConfigArray.push(this.taskConfigurationData[obj]);
		}

		this.totalCompletedTasks = tasks.length;
		this.averageWaitTime = totalWait / tasks.length;

		const taskLineChart = new Chart($('#taskLineChart'), {
		 	type: 'line',
		 	data: {
		 		labels: taskLables,
			 	datasets: [
			 		{
			 			label: 'Wait Time',
			 			data: taskWaitTime
			 		}
			 	]
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
			this.totalPeriodsBuffering += workers[i].totalPeriodsBuffering;
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

