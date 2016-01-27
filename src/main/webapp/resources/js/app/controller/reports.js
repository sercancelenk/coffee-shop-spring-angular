'use strict';
/**
 * @author: byzas
 */

angular.module('app').controller('ReportsController',
    function CartController($scope,$rootScope, $http,$timeout, ShoppingService, ngToast){


        $scope.reports = {
            report1_data:[],
            report2_data:[],
            report3_data:[],
            init:function(){
                $scope.reports.services.report1_load();
                $scope.reports.services.report2_load();
                $scope.reports.services.report3_load();
            },
            services: {
                report1_load: function(){
                    $timeout(function(){
                        ShoppingService.report1LoadData()
                            .then(function(response){
                                $scope.reports.report1_data = response.data.data;
                            }, function(error){});
                    }, 1);
                },
                report2_load: function(){
                    $timeout(function(){
                        ShoppingService.report2LoadData()
                            .then(function(response){
                                $scope.reports.report2_data = response.data.data;
                            }, function(error){});
                    }, 1);
                },
                report3_load: function(){
                    $timeout(function(){
                        ShoppingService.report3LoadData()
                            .then(function(response){
                                $scope.reports.report3_data = response.data.data;
                            }, function(error){});
                    }, 1);
                },
            }
        };

        $scope.reports.init();
    });
