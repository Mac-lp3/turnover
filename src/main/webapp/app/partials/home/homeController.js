'use strict';

module.exports = function ($http, $location, resultsService) {

    this.quickStartUnits = 'minutes';
    this.quickStartPeriods = 0;
    this.quickStartWorkers = 0;
    this.quickStartRate = 0;
    this.quickStartTaskTime = 0;
    this.quickStartRestTime = 0;

    this.quickStart = () => {
        
        $http({

            method: 'POST',
            url: 'api/simulation?type=simple',
            data: {
                clockUnits: this.quickStartUnits,
                totalPeriods: this.quickStartPeriods,
                workerConfigurations: [{
                    total: this.quickStartWorkers,
                    additionalTime: 0,
                    restTime: this.quickStartRestTime,
                    arrivalPeriod: 0,
                    stopPeriod: 0
                }],
                taskConfigurations: [{
                    rate: this.quickStartRate,
                    length: this.quickStartTaskTime,
                    startPeriod: 0,
                    endPeriod: 0,
                    lowBound: 0,
                    highBound: 0,
                    proportion: 100
                }]
            }

        }).then((response) => {

            resultsService.setSimulationResults(response.data);
        });
    
    };

    this.advancedConfig = () => {

        $location.path('/advanced');

    };

};