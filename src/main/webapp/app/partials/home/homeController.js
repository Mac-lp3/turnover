'use strict';

module.exports = function ($http, $location, resultsService) {

    this.quickStartUnits = 'minutes';
    this.quickStartPeriods = 0;
    this.quickStartWorkers = 0;
    this.quickStartRate = 0;
    this.quickStartTaskTime = 0;
    this.quickStartRestTime = 0;

    this.quickStart = () => {

        const taskConfigs = [];
        taskConfigs.push({
            arrivalRate: this.quickStartRate,
            taskLength: this.quickStartTaskTime,
            proportion: 100,
            startPeriod: 0,
            endPeriod: this.quickStartPeriods
        });
        
        $http({

            method: 'POST',
            url: 'api/simulation?type=simple',
            data: {
                clockUnits: this.quickStartUnits,
                totalPeriods: this.quickStartPeriods,
                workerConfigurations: [{
                    total: this.quickStartWorkers,
                    additionalTime: '0',
                    restTime: this.quickStartRestTime,
                    arrivalPeriod: 0,
                    stopPeriod: this.quickStartPeriods
                }],
                taskConfigurations: taskConfigs
            }

        }).then((response) => {

            resultsService.setSimulationResults(response.data);
        });
    
    };

    this.advancedConfig = () => {

        $location.path('/advanced');

    };

};