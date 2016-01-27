<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script src="<c:url value="/resources/js/app/controller/reports.js" />"></script>

<div class="row text-center" ng-controller="ReportsController">
    <div class="col-md-12 hero-future">

        <table class="table table-striped">
            <caption><h4 class="text-info"><i class="fa fa-area-chart"></i>&nbsp;<ins>Müşteri bazında sipariş toplam tutarı</ins></h4></caption>
            <thead>
            <tr>
                <th class="text-left text-warning">Müşteri Adı</th>
                <th class="text-left text-warning">Toplam Tutar</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in reports.report1_data">
                <td class="text-left text-muted">{{item[0].name}}</td>
                <td class="text-left text-muted">{{item[1]}} TL</td>
            </tr>
            </tbody>
        </table>

    </div>
    <div class="col-md-12 hero-future">

        <table class="table table-striped">
            <caption><h4 class="text-info"><i class="fa fa-area-chart"></i>&nbsp;<ins>İçecek türü bazından eklentileri dahil toplam tutar</ins></h4></caption>
            <thead>
            <tr>
                <th class="text-left text-warning">Ürün Adı</th>
                <th class="text-left text-warning">Toplam Tutar</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in reports.report2_data">
                <td class="text-left text-muted">{{item[0]}}</td>
                <td class="text-left text-muted">{{item[1]}} TL</td>
            </tr>
            </tbody>
        </table>

    </div>
    <div class="col-md-12 hero-future">

        <table class="table table-striped">
            <caption><h4 class="text-info"><i class="fa fa-area-chart"></i>&nbsp;<ins>Eklenti türüne göre içecek kırılımında eklenti toplam tutar</ins></h4></caption>
            <thead>
            <tr>
                <th class="text-left text-warning">Eklenti Adı</th>
                <th class="text-left text-warning">Toplam Tutar</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="item in reports.report3_data">
                <td class="text-left text-muted">{{item[1]}}</td>
                <td class="text-left text-muted">{{item[2]}} TL</td>
            </tr>
            </tbody>
        </table>

    </div>



</div>

