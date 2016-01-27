'use strict';
angular.module('app',['ngToast']);

angular.module('app').directive('modal', function(){
        return {
            template: '<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"><div class="modal-dialog modal-md"><div class="modal-content" ng-transclude><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title" id="myModalLabel">Modal title</h4></div></div></div></div>',
            restrict: 'E',
            transclude: true,
            replace:true,
            scope:{visible:'=', onSown:'&', onHide:'&'},
            link:function postLink(scope, element, attrs){

                $(element).modal({
                    show: false,
                    keyboard: attrs.keyboard,
                    backdrop: attrs.backdrop
                });

                scope.$watch(function(){return scope.visible;}, function(value){

                    if(value == true){
                        $(element).modal('show');
                    }else{
                        $(element).modal('hide');
                    }
                });

                $(element).on('shown.bs.modal', function(){
                    scope.$apply(function(){
                        scope.$parent[attrs.visible] = true;
                    });
                });

                $(element).on('shown.bs.modal', function(){
                    scope.$apply(function(){
                        scope.onSown({});
                    });
                });

                $(element).on('hidden.bs.modal', function(){
                    scope.$apply(function(){
                        scope.$parent[attrs.visible] = false;
                    });
                });

                $(element).on('hidden.bs.modal', function(){
                    scope.$apply(function(){
                        scope.onHide({});
                    });
                });
            }
        };
    }
);
angular.module('app').directive('modalHeader', function(){
    return {
        template:'<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title">{{title}}</h4></div>',
        replace:true,
        restrict: 'E',
        scope: {title:'@'}
    };
});

angular.module('app').directive('modalBody', function(){
    return {
        template:'<div class="modal-body" ng-transclude></div>',
        replace:true,
        restrict: 'E',
        transclude: true
    };
});

angular.module('app').directive('modalFooter', function(){
    return {
        template:'<div class="modal-footer" ng-transclude></div>',
        replace:true,
        restrict: 'E',
        transclude: true
    };
});

angular.module('app').directive(
    'bsDropdown2',
    function($compile) {
        return {
            restrict : 'E',
            scope : {
                items : '=dropdownData',
                doSelect : '&selectVal',
                selectedItem : '=preselectedItem',
                callBack : '&',
                emptyVal : '=emptyVal',
                css:'=css'
            },
            link : function(scope, element, attrs) {
                var html = '';
                var ddClass = "btn button-label btn-warning btn-xs";

                if(attrs.css === null && attrs.css=== undefined && attrs.css !== ""){
                    ddClass = attrs.css;
                }
                switch (attrs.menuType) {
                    case "button":
                        html += '<div class="btn-group"><button class="btn btn-warning btn-sm dropdown-toggle" style="border-top-left-radius:5px !important;border-bottom-left-radius:5px !important;">'
                            + '<small>' + attrs.emptyval + '</small>'
                            + '</button><button class="btn btn-warning btn-sm dropdown-toggle" style="border-top-right-radius:5px !important;border-bottom-right-radius:5px !important;" data-toggle="dropdown"><span class="caret"></span></button>';
                        break;
                    case "dropdown":
                        html += '<div class="dropdown">'
                            + '<button class="btn  bg-cyan btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">' + attrs.emptyval + '<span class="caret"></span></button>'
                            + '';
                        break;
                    default:
                        html += '<div class="dropdown"><a class="dropdown-toggle" role="button" data-toggle="dropdown"  href="javascript:;">Dropdown<b class="caret"></b></a>';
                        break;
                }
                html += '<ul class="dropdown-menu" style="overflow-y: auto; overflow-x: auto; max-height: 250px;"><li ng-repeat="item in items"><a tabindex="-1" data-ng-click="selectVal(item)"><span>{{item}}</span></a></li></ul></div>';
                element.append($compile(html)(scope));

                for (var i = 0; i < scope.items.length; i++) {
                    if (scope.items[i] === scope.selectedItem) {
                        scope.bSelectedItem = scope.items[i];
                        break;
                    }
                }

                scope.selectVal = function(item) {

                    switch (attrs.menuType) {
                        case "button":
                            $('button.button-label', element).html(
                                '<small>' + item + '</small>');
                            break;
                        default:
                            $('a.dropdown-toggle', element).html(
                                '<b class="caret"></b> '
                                + item);
                            break;
                    }
                    scope.doSelect({
                        selectedVal : item
                    });
                    scope.callBack(item);
                };
                if (scope.bSelectedItem != null) {
                    scope.selectVal(scope.bSelectedItem);
                }else {
                    //var b = {
                    //    id : -1,
                    //    name : attrs.emptyval
                    //};
                    //scope.selectVal(b);
                }
            }
        };
    });

angular.module('app').directive('ngEnter', function() {
    return function(scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            if (event.which === 13) {
                scope.$apply(function() {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});

angular.module('app').directive('numbersonly', function() {
    return {
        require : 'ngModel',
        link : function(scope, element, attrs, modelCtrl) {
            modelCtrl.$parsers.push(function(inputValue) {
                // this next if is necessary for when using ng-required on your
                // input.
                // In such cases, when a letter is typed first, this parser will
                // be called
                // again, and the 2nd time, the value will be undefined
                if (inputValue == undefined)
                    return '';
                var transformedInput = inputValue.replace(/[^0-9]/g, '');
                if (transformedInput != inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});