<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script src="<c:url value="/resources/js/app/controller/cart.js" />"></script>

<div class="row text-center" ng-controller="CartController">
	<div class="col-md-12 hero-future">

		<c:if test="${complete}">
			<div class="jumbotron alert-warning" ng-if="shoppingcart.item_count == 0">
				<p class="text-center">
					<span class="text-warning">Alışverişiniz Tamamlandı. &nbsp;<i class="fa fa-check-square-o fa-lg text-success"></i></span>
				</p>
			</div>
		</c:if>
		<div class="well" ng-if="shoppingcart.item_count == 0">
			<p class="text-center">
				<span class="text-warning"><i class="fa fa-info"></i>&nbsp; Sepetinizde herhangi bir ürün bulunamadı. </span>
				<br>
				<br>
				<a href="<c:url value="/"/>" class="btn btn-warning btn-xs">Alışverişe Devam Et</a>
			</p>
		</div>
		<table class="table table-bordered" ng-if="shoppingcart.item_count > 0">
			<caption class="text-left text-warning"><i class="fa fa-align-justify"></i>&nbsp;Sepetiniz</caption>
			<thead>
			<tr>
				<th class="text-left text-warning">No</th>
				<th class="text-left text-warning">Ürün Adı</th>
				<th class="text-left text-warning">Fiyatı</th>
				<th class="text-left text-warning">Adedi</th>
				<th class="text-left text-warning">Ara Fiyat</th>
				<th class="text-left text-warning">Ürün Tipi</th>
				<th class="text-left text-warning">...</th>
			</tr>
			</thead>
			<tbody>
				<tr ng-repeat-start="item in shoppingcart.items">
					<td class="text-left text-info" rowspan="{{item.extras.length + 1}}">{{$index+1}}</td>
					<td class="text-left text-info">{{item.product.name}}</td>
					<td class="text-left text-info">{{item.amount}} TL</td>
					<td class="text-left text-info"><input  style="width: 30px;" type="text" ng-model="item.quantity"/>
					</td>
					<td class="text-left text-info">{{(item.product.price)*item.quantity}} TL</td>
					<td class="text-left text-info">{{item.product.product_type}}</td>
					<td class="text-left text-info">
						<button type="button" class="btn btn-success btn-xs" ng-click="cart.updateShoppingCart(item, '')" title="Güncelle"><i class="fa fa-save"></i> </button>
						<button type="button" class="btn btn-danger btn-xs" ng-click="cart.removeItemFromShoppingCart(item, '')" title="Sil"><i class="fa fa-remove"></i> </button>
					</td>
				</tr>
				<tr ng-repeat="extra in item.extras">
					<td class="text-left text-info">{{extra.product.name}}</td>
					<td class="text-left text-info">{{extra.amount}} TL</td>
					<td class="text-left text-info"><input  style="width: 30px;" type="text" ng-model="extra.quantity"/>
					</td>
					<td class="text-left text-info">{{extra.totalCost}} TL</td>
					<td class="text-left text-info">{{extra.product.product_type}}</td>
					<td class="text-left text-info">
						<button type="button" class="btn btn-success btn-xs" ng-click="cart.updateShoppingCart(item, extra)" title="Güncelle"><i class="fa fa-save"></i> </button>
						<button type="button" class="btn btn-danger btn-xs" ng-click="cart.removeItemFromShoppingCart(item, extra)" title="Sil"><i class="fa fa-remove"></i> </button>
					</td>
				</tr>
				<tr class="active" ng-repeat-end>
					<td colspan="6" class="text-right text-danger">Ara Toplam</td>
					<td class="text-left text-danger">{{item.totalCost}} TL</td>
				</tr>
				<tr>
					<td colspan="7">&nbsp;</td>
				</tr>
				<tr class="alert-danger">
					<td colspan="6" class="text-right text-danger">Genel Toplam</td>
					<td class="text-left text-danger">{{shoppingcart.totalPrice}} TL</td>
				</tr>
				<tr class="success">
					<td colspan="6" class="text-right text-warning"><i class="fa fa-hand-peace-o"></i>&nbsp;İndirim</td>
					<td class="text-left text-danger">{{shoppingcart.discountPrice}} TL</td>
				</tr>
				<tr class="danger">
					<td colspan="6" class="text-right text-danger">Ödenmesi Gereken Tutar</td>
					<td class="text-left text-danger">{{shoppingcart.totalPrice - shoppingcart.discountPrice}} TL</td>
				</tr>
				<tr>
					<td class="text-right text-danger" colspan="7">
						<a href="<c:url value="/"/>" class="btn btn-warning btn-lg pull-left">Alışverişe Devam Et</a>
						<a href="<c:url value="/completeshopping"/> " class="btn btn-success btn-lg">Alışverişi Tamamla</a>
					</td>
				</tr>


			</tbody>
		</table>

	</div>
</div>

