<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script src="<c:url value="/resources/js/app/controller/pm.js" />"></script>

<div class="row text-center" ng-controller="PMController">
    <div class="col-md-12 hero-future">

        <table class="table table-bordered">
            <caption><h3 class="text-warning">Ürünler<button class="btn btn-primary btn-xs pull-right" ng-click="pm.crud.openProductManagementModal('add','')"><h5>Yeni Ürün</h5></button></h3></caption>
            <thead>
            <tr>
                <th class="text-left text-warning">Adı</th>
                <th class="text-left text-warning">Fiyatı</th>
                <th class="text-left text-warning">Açıklama</th>
                <th class="text-left text-warning">Ürün Tipi</th>
                <th class="text-center text-warning">Düzenle</th>
                <th class="text-center text-warning">Sil</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in pm.products">
                <td class="text-left text-info">{{item.name}}</td>
                <td class="text-left text-info">{{item.price}} TL</td>
                <td class="text-left text-info">{{item.description}}</td>
                <td class="text-left text-info">{{item.product_type}}</td>
                <td class="text-center text-info"><button type="button" class="btn btn-success btn-xs" ng-click="pm.crud.openProductManagementModal('update',item)" title="Güncelle"><i class="fa fa-save"></i> </button></td>
                <td class="text-center text-info"><button type="button" class="btn btn-danger btn-xs" ng-click="pm.crud.deleteProductManagementModal(item)" title="Sil"><i class="fa fa-remove"></i> </button></td>
            </tr>
            </tbody>
        </table>

    </div>

    <modal visible="pm.crud.showModalPm" on-sown="pm.modal.pmModalShown()" on-hide="pm.modal.pmModalHide()">
        <modal-header title="{{modal_title}}"></modal-header>
        <modal-body>
            <form role="form" novalidate id="pmForm" name="pmForm">
                <div class="form-group">
                    <label for="product_name" class="text-left text-warning pull-left">Ürün Adı</label>
                    <input type="text" class="form-control" id="product_name" placeholder="Ürün Adı" ng-model="pm.crud.product.name"  required/>
                </div>
                <div class="form-group">
                    <label for="product_description" class="text-left text-warning pull-left">Ürün Açıklaması</label>
                    <input type="text" class="form-control" id="product_description" placeholder="Ürün Açıklaması" ng-model="pm.crud.product.description"/>
                </div>
                <div class="form-group text-left">
                    <div class="row">
                        <div class="col-md-12">
                            <label class="text-left text-warning pull-left">Ürün Tipi</label>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-6"><input type="text" class="form-control" readonly ng-model="pm.crud.product.product_type" aria-describedby="basic-addon2"></div>
                        <div class="col-md-6 text-left">
                            <bs-dropdown2 data-menu-type="button"
                                          select-val="pm.crud.product.product_type = selectedVal"
                                          preselected-item="pm.crud.product.product_type"
                                          data-dropdown-data="pm.product_types"
                                          emptyval="Seçiniz"></bs-dropdown2>
                        </div>
                    </div>
                <div class="form-group">
                    <label for="product_price" class="text-left text-warning pull-left">Ürün Fiyatı(TL)</label>
                    <input numbersonly type="text" class="form-control text-warning" id="product_price" placeholder="Ürün Fiyatı" ng-model="pm.crud.product.price" required />
                </div>
                <button type="button" class="btn btn-success" ng-click="pm.crud.addOrUpdateProduct(pm.crud.product)" ng-disabled="pmForm.$invalid">{{pm.crud.modalpm_submit_button_text}}</button>
                <button type="button" class="btn btn-danger" ng-click="pm.modal.pmModalHide()">Kapat</button>
            </form>
        </modal-body>
        <%--<modal-footer>--%>
            <%--<button class="btn btn-primary"  ng-click="hide(1)">Save</button>--%>
        <%--</modal-footer>--%>
    </modal>

    <modal visible="pm.crud.showModalPmDelete" on-sown="pm.modal.pmModalShown()" on-hide="pm.modal.pmModalHide()">
        <modal-header title="{{modal_title}}"></modal-header>
        <modal-body>
            <form role="form">
                <div class="form-group">
                    <label>'{{pm.crud.product.name}}' silinsin mi?</label>
                </div>
                <button type="button" class="btn btn-danger" ng-click="pm.crud.deleteProduct(pm.crud.product)">Sil</button>
                <button type="button" class="btn btn-primary" ng-click="pm.modal.pmModalHide()">Kapat</button>
            </form>
        </modal-body>
        <%--<modal-footer>--%>
        <%--<button class="btn btn-primary"  ng-click="hide(1)">Save</button>--%>
        <%--</modal-footer>--%>
    </modal>


</div>

