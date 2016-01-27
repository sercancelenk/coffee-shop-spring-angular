angular.module('app').controller('HeaderController',
        function HeaderController ($scope,$rootScope, $timeout,$location, ShoppingService,ngToast) {
    $rootScope.shoppingcart = [];
    $rootScope.itemcount = 0;

    $scope.header = {
        updateItemCount:function(count){
            $rootScope.itemcount = count;
        },
        loadShoppingCart: function(){
            $timeout(function(){
                ShoppingService.getShoppingCart().then(function(response){
                    if(response != null && response.data != null && response.data.data != null){
                        $rootScope.shoppingcart = response.data.data;
                        $scope.header.updateItemCount($rootScope.shoppingcart.item_count);
                    }
                }, function(error){

                });
            },1);
        },
        updateQuantityOfBaseItem: function(pid, quantity, pname, itemid){

            $timeout(function(){
               ShoppingService.updateQuantityOfProductInSc(pid, quantity, itemid).then(function(response){
                   if(response != null && response.data != null && response.data.data != null){
                       $rootScope.shoppingcart = response.data.data;
                       $scope.header.updateItemCount($rootScope.shoppingcart.item_count);
                       ngToast.create('Ürün (' + pname + ') adedi güncellendi.');
                   }
               }, function(error){

               });
            });
        },
        updateQuantityOfExtraItem: function(pid, extraItem, quantity, pname, itemid){

            $timeout(function(){
                ShoppingService.updateQuantityOfExtraInSc(pid, extraItem.product.id, quantity, itemid).then(function(response){
                    if(response != null && response.data != null && response.data.data != null){
                        $rootScope.shoppingcart = response.data.data;
                        $scope.header.updateItemCount($rootScope.shoppingcart.item_count);
                        ngToast.create('Ürün (' + pname + ') adedi güncellendi.');
                    }
                }, function(error){

                });
            });
        },
        removeBaseItemFromShoppingCart:function(baseItem){
            $timeout(function(){
                ShoppingService.removeItemToSc(baseItem.product.id, baseItem.id).then(function(response){
                    if(response != null && response.data != null && response.data.data != null){
                        $rootScope.shoppingcart = response.data.data;
                        $rootScope.$broadcast("loadShoppingCart");
                        ngToast.create('Ürün (' + baseItem.product.name + ') sepetinizden çıkartıldı.');
                    }
                }, function(error){

                });
            });
        },
        removeExtraItemFromShoppingCart:function(baseItem, extraItem){
            $timeout(function(){
                ShoppingService.removeExtraItemToSc(baseItem.product.id, extraItem.product.id, baseItem.id).then(function(response){
                    if(response != null && response.data != null && response.data.data != null){
                        $rootScope.shoppingcart = response.data.data;
                        $rootScope.$broadcast("loadShoppingCart");
                        ngToast.create('Ürün (' + extraItem.product.name + ') sepetinizden çıkartıldı.');
                    }
                }, function(error){

                });
            });
        },
        addItemToScWithExtra: function(cart_item){
            $timeout(function(){
                ShoppingService.addItemWithExtra(cart_item)
                    .then(function(response){
                        if(response != null && response.data != null && response.data.data != null){
                            $rootScope.$broadcast("loadShoppingCart");
                            $rootScope.$broadcast("updateItemsCount", {count: $rootScope.shoppingcart.item_count});
                            ngToast.create('Ürün (' + cart_item.product.name + ') sepetinize eklendi.');
                        }

                        if(response.data.data == null){
                            ngToast.create(
                                {
                                    className: 'danger',
                                    content: '['+response.data.error.id+'] ' + response.data.error.descriptionText
                                }
                            );
                        }
                    }, function(error){});
            }, 1);
        },
        completeShopping: function(){
            $timeout(function(){
                ShoppingService.completeShopping()
                    .then(function(response){
                        if(response != null && response.data != null && response.data.data != null){
                            $rootScope.shoppingcart = response.data.data;
                            $rootScope.$broadcast("loadShoppingCart");
                            ngToast.create('Alışverişiniz tamamlandı.');
                        }
                    }, function(error){});
            }, 1);
        }
    }

    $scope.$on("updateItemsCount", function (event, args) {
        $scope.header.updateItemCount(args.count);
    });

    $scope.$on("updateQuantityOfItem", function(event, args){
       $scope.header.updateQuantityOfBaseItem(args.pid, args.quantity, args.pname, args.item_id);
    });
    $scope.$on("updateQuantityOfExtraItem", function(event, args){
        $scope.header.updateQuantityOfExtraItem(args.pid,args.extra_item, args.quantity, args.pname, args.item_id);
    });

    $scope.$on("loadShoppingCart", function () {
        $scope.header.loadShoppingCart();
        $rootScope.$broadcast("updateItemsCount", {count: $rootScope.shoppingcart.item_count});
    });

    $scope.$on("removeBaseItemFromShoppingCart", function (event, args) {
        $scope.header.removeBaseItemFromShoppingCart(args.base_item);
    });
    $scope.$on("removeExtraItemFromShoppingCart", function (event, args) {
        $scope.header.removeExtraItemFromShoppingCart(args.base_item, args.extra_item);
    });
    $scope.$on("addItemToScWithExtra", function (event, args) {
        $scope.header.addItemToScWithExtra(args.cart_item);
    });
    $scope.$on("completeShopping", function(){
       $scope.header.completeShopping();
    });
    
});
angular.module('app').controller('loginController',
function loginController($scope, $location) {
    var url = "" + $location.$$absUrl;
    $scope.displayLoginError = (url.indexOf("error") >= 0);
});
