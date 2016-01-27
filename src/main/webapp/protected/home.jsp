<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="<c:url value="/resources/js/app/controller/shopping.js" />"></script>

<div  ng-controller="ShoppingController">
<div class="row">
	<div class="col-lg-12">
		<h4 class="text-warning"><i class="fa fa-coffee"></i>&nbsp;Öneriler</h4>
		<hr>
	</div>
</div>
<div class="row text-center">
	<div class="col-md-3 col-sm-6 hero-feature" ng-repeat="p in shoppingctrl.members.product_recommendations">
		<div class="thumbnail">
			<img src="http://placehold.it/800x100" alt="">
			<div class="caption">
				<h3 class="text-danger">{{p.name}}</h3>
				<p><span class="text-muted"> </span></p>
				<p><span class="text-warning"></span></p>
				<p>
					<button ng-click="shoppingctrl.services.saveCartItemWithExtra2(p.items[0])" class="btn btn-success btn-xs"><i class="fa fa-cart-plus"></i>&nbsp;Sepete Ekle</button>
				</p>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<h4 class="text-warning"><i class="fa fa-coffee"></i>&nbsp;İçecek Çeşitlerimiz</h4>
		<hr>
	</div>
</div>
<div class="row text-center">
	<div class="col-md-3 col-sm-6 hero-feature" ng-repeat="p in shoppingctrl.members.products">
		<div class="thumbnail">
			<img src="http://placehold.it/800x100" alt="">
			<div class="caption">
				<h3 class="text-danger">{{p.name}}</h3>
				<p><span class="text-muted"> {{p.description}}.</span></p>
				<p><span class="text-warning"><i class="fa fa-money"></i>&nbsp;{{p.price}} TL</span></p>
				<p>
					<button ng-click="shoppingctrl.funcs.openPreShopModal(p)" class="btn btn-success btn-xs"><i class="fa fa-cart-plus"></i>&nbsp;Sepete Ekle</button>
				</p>
			</div>
		</div>
	</div>

	<modal visible="shoppingctrl.members.preShopModalVisible" on-sown="shoppingctrl.members.modal.pmModalShown()" on-hide="shoppingctrl.members.modal.pmModalHide()">
		<modal-header title="{{modal_title}}"></modal-header>
		<modal-body>
			<table class="table">
				<caption><h5><span class="text-danger"><i class="fa fa-coffee"></i>&nbsp;Seçilen Ürün</span></h5></caption>
				<thead>
					<th class="text-left text-muted">Ürün</th>
					<th class="text-left text-muted">Fiyat</th>
					<th class="text-left text-muted">Adet</th>
				</thead>
				<tbody>
					<tr>
						<td class="text-left text-warning">{{shoppingctrl.members.selected_cart_item.product.name}}</td>
						<td class="text-left text-warning">{{shoppingctrl.members.selected_cart_item.product.price}}&nbsp;TL</td>
						<td class="text-left text-warning"><input class="text-warning" style="width: 30px !important;" type="text" numbersonly ng-model="shoppingctrl.members.selected_cart_item.quantity"/> </td>

					</tr>
				</tbody>
			</table>
			<table class="table" ng-show="shoppingctrl.members.selected_cart_item.extras.length > 0">
				<caption><h6><span class="text-danger"><i class="fa fa-coffee"></i>&nbsp;Ekstralar</span></h6></caption>
				<thead>
				<th class="text-left text-muted">Ürün</th>
				<th class="text-left text-muted">Fiyat</th>
				<th class="text-left text-muted">Adet</th>
				<th class="text-left text-muted">Sil</th>
				</thead>
				<tbody>
				<tr ng-repeat="extra in shoppingctrl.members.selected_cart_item.extras">
					<td class="text-left text-warning">{{extra.product.name}}</td>
					<td class="text-left text-warning">{{extra.product.price}}&nbsp;TL</td>
					<td class="text-left text-warning"><input class="text-warning" style="width: 30px !important;" type="text" numbersonly ng-model="extra.quantity"/> </td>
					<td class="text-left text-warning"><button class="btn btn-danger btn-xs" type="button" ng-click="shoppingctrl.funcs.removeExtraProductFromBaseProduct(extra)"><i class="fa fa-close"></i> </button></td>
				</tr>
				</tbody>
			</table>
			<div class="well">
				<h5 class="text-left text-danger"><i class="fa fa-coffee"></i>&nbsp;Ekstra Seçebilirsiniz</h5>
				<div class="row text-center">
					<div class="col-md-3 col-sm-6 hero-feature" ng-repeat="p in shoppingctrl.members.extras">
						<div class="thumbnail">
							<div class="caption">
								<h5 class="text-warning">{{p.name}}</h5>
								<p><span class="text-warning"><i class="fa fa-money"></i>&nbsp;{{p.price}} TL</span></p>
								<p>
									<button ng-click="shoppingctrl.funcs.addExtraToSelectedProduct(p)" class="btn btn-success btn-xs"><i class="fa fa-plus"></i></button>
								</p>
							</div>
						</div>
					</div>
				</div>

			</div>
		</modal-body>
		<modal-footer>
			<button class="btn btn-primary"  ng-click="shoppingctrl.services.saveCartItemWithExtra()"><i class="fa fa-save"></i>&nbsp;Kaydet</button>
			<button class="btn btn-primary"  ng-click="shoppingctrl.members.modal.pmModalHide()"><i class="fa fa-close"></i>&nbsp;İptal</button>
		</modal-footer>
	</modal>
</div>
</div>

