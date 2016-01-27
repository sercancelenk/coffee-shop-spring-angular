'use strict';
/**
 * @author: byzas
 */

angular.module('app').controller('PMController',
    function CartController($scope,$rootScope, $http,$timeout, ShoppingService, ngToast){


        $scope.pm = {
            products:[],
            product_types:["BASE", "EXTRA"],
            modal:{
                pmModalShown : function(){

                },
                pmModalHide : function(){
                    $scope.pm.crud.showModalPm = false;
                    $scope.pm.crud.showModalPmDelete = false;
                }
            },
            crud:{
                showModalPm:false,
                showDeleteModalPm:false,
                modalMode:'',
                product:{},
                openProductManagementModal:function(modalmode, product){

                    $scope.pm.crud.modalMode = modalmode;
                    if(modalmode == 'add'){
                        $scope.modal_title = "Ürün Ekle";
                        $scope.pm.crud.modalpm_submit_button_text = "Ekle";
                        $scope.pm.crud.product = {};
                    }else if(modalmode == 'update'){
                        $scope.modal_title = "Ürün Güncelle";
                        $scope.pm.crud.modalpm_submit_button_text = "Güncelle";
                        $scope.pm.crud.product = product;
                    }

                    $scope.pm.crud.showModalPm = true;
                },
                deleteProductManagementModal:function(product){
                    $scope.pm.crud.modalMode = 'delete';
                    $scope.pm.crud.product = product;
                    $scope.pm.crud.showModalPmDelete = true;
                },
                addOrUpdateProduct:function(product){
                    if($scope.pm.crud.modalMode == 'add'){
                        if($scope.pm.crud.product.product_type == "" || $scope.pm.crud.product.product_type == "Seçiniz"){
                            ngToast.create('Ürün tipi seçiniz!');
                            return;
                        }
                        $timeout(function(){
                            ShoppingService.addProduct(product).then(function(response){
                                if(response.data.data){
                                    $scope.pm.getProducts();
                                    ngToast.create('Ürün (' + product.name + ') eklendi.');
                                }
                            }, function(error){});
                        }, 1);
                    }else if($scope.pm.crud.modalMode == 'update'){
                        if($scope.pm.crud.product.product_type == "" || $scope.pm.crud.product.product_type == "Seçiniz"){
                            ngToast.create('Ürün tipi seçiniz!');
                            return;
                        }
                        $timeout(function(){
                            ShoppingService.updateProduct(product).then(function(response){
                                if(response.data.data){
                                    $scope.pm.getProducts();
                                    ngToast.create('Ürün (' + product.name + ') bilgileri güncellendi.');
                                }
                            }, function(error){});
                        }, 1);
                    }

                },
                deleteProduct:function(product){
                    $timeout(function(){
                        ShoppingService.deleteProduct(product.id).then(function(response){
                            if(response.data.data){
                                $scope.pm.getProducts();
                                $scope.pm.modal.pmModalHide();
                                ngToast.create('Ürün (' + product.name + ') silindi.');
                            }
                        }, function(error){});
                    }, 1);
                }
            },
            init:function(){
                $scope.pm.getProducts();

            },
            getProducts: function(){
                $timeout(function(){
                    ShoppingService.allProducts().then(function(response){
                        $scope.pm.products = response.data.data;
                    }, function(error){});
                }, 1);
            },
            updateShoppingCart: function(item){
                $rootScope.$broadcast("updateQuantityOfItem", {pid: item.product.id, quantity: item.quantity, pname: item.product.name});
                $scope.updatequantitybuttonvisible = false;
            },
            removeItemFromShoppingCart:function(item){
                $rootScope.$broadcast("removeItemFromShoppingCart", {item: item});
            }
        };

        $scope.quantityInputChange=function(){
            $scope.updatequantitybuttonvisible = true;
        }



        $scope.pm.init();
    });
