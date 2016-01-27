(function () {
    'use strict';

    angular
        .module('app')
        .factory('ShoppingService', ShoppingService);

    ShoppingService.$inject = ['$http'];
    function ShoppingService($http) {
        var service = {};

        service.allProducts = getAllProducts;
        service.allProductsByType = allProductsByType;
        service.productRecommendations = productRecommendations;
        service.getShoppingCart = getShoppingCart;

        service.addItemWithExtra = addItemWithExtra;
        service.removeItemToSc = removeItemToSc;
        service.updateQuantityOfProductInSc = updateQuantityOfProductInSc;
        service.removeExtraItemToSc = removeExtraItemToSc;
        service.updateQuantityOfExtraInSc = updateQuantityOfExtraInSc;
        service.completeShopping = completeShopping;

        service.addProduct = addProduct;
        service.deleteProduct = deleteProduct;
        service.updateProduct = updateProduct;

        service.report1LoadData = report1LoadData;
        service.report2LoadData = report2LoadData;
        service.report3LoadData = report3LoadData;

        return service;

        function report1LoadData() {
            var url = "/management/rest/report/1";
            return $http.get(url).then(handleSuccess,handleError('Error getting datas!'));
        }
        function report2LoadData() {
            var url = "/management/rest/report/2";
            return $http.get(url).then(handleSuccess,handleError('Error getting datas!'));
        }
        function report3LoadData() {
            var url = "/management/rest/report/3";
            return $http.get(url).then(handleSuccess,handleError('Error getting datas!'));
        }

        function getAllProducts() {
            var url = "/product/all";
            return $http.get(url).then(handleSuccess,handleError('Error getting datas!'));
        }
        function productRecommendations() {
            var url = "/product/recommendations";
            return $http.get(url).then(handleSuccess,handleError('Error getting datas!'));
        }
        function allProductsByType(type) {
            var url = "/product/all/" + type.toUpperCase();
            return $http.get(url).then(handleSuccess,handleError('Error getting datas!'));
        }
        function getShoppingCart(){
            var url = "/shop/cart";
            return $http.get(url).then(handleSuccess,handleError('Error getting datas!'));
        }


        function addItemWithExtra(cart_item){
            var url = "/shop/cart/add/item/withextra";
            var data = cart_item;
            return $http.post(url, data).then(handleSuccess, handleError('Error getting datas'));
        }

        function removeItemToSc(pid, itemid){
            var url = "/shop/cart/remove/" + pid + "/" + itemid;
            return $http.delete(url).then(handleSuccess,handleError('Error getting datas!'));
        }

        function updateQuantityOfProductInSc(pid, quantity, itemid){
            var url = "/shop/cart/update/base/quantity/" + pid + "/" + quantity + "/" + itemid;
            return $http.post(url).then(handleSuccess,handleError('Error getting datas!'));
        }

        function removeExtraItemToSc(pid, epid, itemid){
            var url = "/shop/cart/remove/" + pid + "/" + epid + "/" + itemid;
            return $http.delete(url).then(handleSuccess,handleError('Error getting datas!'));
        }

        function updateQuantityOfExtraInSc(pid,epid, quantity, itemid){
            var url = "/shop/cart/update/extra/quantity/" + pid + "/" + epid + "/" + quantity + "/" + itemid;
            return $http.post(url).then(handleSuccess,handleError('Error getting datas!'));
        }


        function addProduct(product){
            var url = "/management/rest/product/add";
            var data = product;
            return $http.post(url, data).then(handleSuccess,handleError('Error getting datas!'));
        }

        function updateProduct(product){
            var url = "/management/rest/product/update";
            var data = product;
            return $http.put(url, data).then(handleSuccess,handleError('Error getting datas!'));
        }

        function deleteProduct(product_id){
            var url = "/management/rest/product/delete/" + product_id;
            return $http.delete(url).then(handleSuccess,handleError('Error getting datas!'));
        }

        function completeShopping(){
            var url = "/shop/cart/complete";
            return $http.post(url).then(handleSuccess,handleError('Error getting datas!'));
        }

        // private functions

        function handleSuccess(data) {
            return data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();