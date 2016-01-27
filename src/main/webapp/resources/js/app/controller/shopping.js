'use strict';
/**
 * @author: byzas
 */

angular.module('app').controller('ShoppingController',
    function ShoppingController($scope,$rootScope, $http,$timeout, ShoppingService,ngToast){

        $scope.shoppingctrl = {
            init:function(){
                $scope.shoppingctrl.services.loadAll();
                $scope.shoppingctrl.services.loadExtras();
                $scope.shoppingctrl.services.loadProductRecommendations();
                $rootScope.$broadcast("loadShoppingCart");
            },
            members:{
                products:[],
                extras:[],
                product_recommendations:[],
                preShopModalVisible : false,
                selected_cart_item:{
                    product:{},
                    quantity:1,
                    extras:[]
                },
                selected_cart_item_extra:{
                    product:{},
                    quantity:1
                },
                modal:{
                    pmModalShown : function(){},
                    pmModalHide : function(){
                        $scope.shoppingctrl.members.preShopModalVisible = false;
                    }
                }
            },
            funcs:{
                addExtraToSelectedProduct: function(product){
                    var alreadyExists = false;
                    angular.forEach($scope.shoppingctrl.members.selected_cart_item.extras, function(extra) {
                        if(extra.product.id === product.id){
                            alreadyExists = true;
                            extra.quantity += 1;
                        }
                    });
                    if(!alreadyExists){
                        $scope.shoppingctrl.members.selected_cart_item_extra = {};
                        $scope.shoppingctrl.members.selected_cart_item_extra.product = product;
                        $scope.shoppingctrl.members.selected_cart_item_extra.quantity = 1;
                        $scope.shoppingctrl.members.selected_cart_item.extras.push($scope.shoppingctrl.members.selected_cart_item_extra)
                    }
                },
                removeExtraProductFromBaseProduct:function(extra_item){
                    if(extra_item.product.product_type == "EXTRA"){
                        $scope.shoppingctrl.members.selected_cart_item.extras.splice(extra_item, 1);
                    }else{
                        $scope.shoppingctrl.members.selected_cart_item = {};
                    }

                },
                openPreShopModal:function(product){
                    $scope.modal_title = "Sepete Ekle";
                    $scope.shoppingctrl.members.selected_cart_item.product = product;
                    $scope.shoppingctrl.members.selected_cart_item.extras = [];
                    $scope.shoppingctrl.members.preShopModalVisible = true;
                }
            },
            services:{
                loadExtras: function(){
                    $timeout(function(){
                        ShoppingService.allProductsByType("extra").then(function(response){

                            if(response != null && response.data != null && response.data.data != null){
                                $scope.shoppingctrl.members.extras = response.data.data;
                            }
                        }, function(error){
                            $scope.products = [];
                        });
                    },1);
                },
                loadAll: function(){
                    $timeout(function(){
                        ShoppingService.allProductsByType("base").then(function(response){

                            if(response != null && response.data != null && response.data.data != null){
                                $scope.shoppingctrl.members.products = response.data.data;
                            }
                        }, function(error){
                            $scope.products = [];
                        });
                    },1);
                },
                loadProductRecommendations: function(){
                    $timeout(function(){
                        ShoppingService.productRecommendations().then(function(response){
                            if(response != null && response.data != null && response.data.data != null){
                                $scope.shoppingctrl.members.product_recommendations = response.data.data;
                            }
                        }, function(error){
                            $scope.products = [];
                        });
                    },1);
                },
                saveCartItemWithExtra:function(){
                    $timeout(function(){
                        ShoppingService.addItemWithExtra($scope.shoppingctrl.members.selected_cart_item)
                            .then(function(response){
                                $rootScope.$broadcast("loadShoppingCart");
                                ngToast.create('Ürün sepetinize eklendi.');
                                $scope.shoppingctrl.members.modal.pmModalHide();
                            }, function(error){});
                    }, 1);
                },
                saveCartItemWithExtra2:function(cart_item){
                    $scope.shoppingctrl.members.selected_cart_item = {};
                    $scope.shoppingctrl.members.selected_cart_item.product = cart_item.product;
                    $scope.shoppingctrl.members.selected_cart_item.quantity = cart_item.quantity;
                    $scope.shoppingctrl.members.selected_cart_item.extras = [];
                    angular.forEach(cart_item.extras, function(extra) {
                        $scope.shoppingctrl.members.selected_cart_item_extra = {};
                        $scope.shoppingctrl.members.selected_cart_item_extra.product = extra.product;
                        $scope.shoppingctrl.members.selected_cart_item_extra.quantity = extra.quantity;
                        $scope.shoppingctrl.members.selected_cart_item.extras.push($scope.shoppingctrl.members.selected_cart_item_extra);
                    });
                    $scope.shoppingctrl.services.saveCartItemWithExtra();


                }
            }
        };

        $scope.shoppingctrl.init();
    });





