'use strict';
/**
 * @author: byzas
 */

angular.module('app').controller('CartController',
    function CartController($scope,$rootScope, $http,$timeout, ShoppingService, $window){

        $scope.cart = {
            init:function(){
                $scope.cart.loadShoppingCart();
            },
            loadShoppingCart: function(){
                $rootScope.$broadcast("loadShoppingCart");
            },
            updateShoppingCart: function(item, extra_item){
                if(extra_item === ''){
                    $rootScope.$broadcast("updateQuantityOfItem", {pid: item.product.id, quantity: item.quantity, pname: item.product.name,item_id: item.id});
                }else{
                    $rootScope.$broadcast("updateQuantityOfExtraItem", {pid: item.product.id, extra_item: extra_item ,quantity: extra_item.quantity, pname: item.product.name,item_id: item.id});
                }
            },
            removeItemFromShoppingCart:function(base_item, extra_item){
                if(extra_item === ''){
                    $rootScope.$broadcast("removeBaseItemFromShoppingCart", {item_id: base_item.id, base_item: base_item});
                }else{
                    $rootScope.$broadcast("removeExtraItemFromShoppingCart", {base_item: base_item, extra_item: extra_item,item_id: base_item.id});
                }
                $rootScope.$broadcast("loadShoppingCart");

            },
            completeShopping: function(){
                $rootScope.$broadcast("completeShopping");
            }
        };
        $scope.cart.init();
    });
