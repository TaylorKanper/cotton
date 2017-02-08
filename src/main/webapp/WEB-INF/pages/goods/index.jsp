<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <jsp:include page="/common/header.jsp"></jsp:include>
</head>
<body>
<div id="wrapper">
    <jsp:include page="/common/common.jsp"></jsp:include>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-lg-12">
                    <h3 class="text-info">商品管理</h3>
                    <form class="form-inline" role="form" id="goodsSearchForm" method="post" style="margin-top: 10px">
                        <div class="form-group">
                            <label for="goods_goodsName" class="text-info">商品名称</label>
                            <input type="text" class="form-control" id="goods_goodsName" name="goodsName"
                                   placeholder="查询的商品名称...">
                        </div>
                        <div class="form-group">
                            <label for="goods_goodsMerchant" class="text-info">商品厂家</label>
                            <input type="text" class="form-control" id="goods_goodsMerchant" name="merchant"
                                   placeholder="查询的商品厂家...">
                        </div>
                        <div class="form-group">
                            <label for="goods_goodsFirstSort" class="text-info">一级分类</label>
                            <select class="form-control" id="goods_goodsFirstSort" name="firstSortId">
                                <option value="-1">全部</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="goods_goodsSecondSort" class="text-info">二级分类</label>
                            <select class="form-control" id="goods_goodsSecondSort" name="secondSortId">
                                <option value="-1">全部</option>
                            </select>
                        </div>

                        <div class="clearfix" style="margin-bottom: 10px"></div>
                        <div class="form-group">
                            <label for="goods_lowPrice" class="text-info">价格下限</label>
                            <input type="number" class="form-control" id="goods_lowPrice" name="lowPrice"
                                   placeholder="查询的价格下限...">
                        </div>
                        <div class="form-group">
                            <label for="goods_highPrice" class="text-info">价格上限</label>
                            <input type="number" class="form-control" id="goods_highPrice" name="highPrice"
                                   placeholder="查询的价格上限...">
                        </div>
                        <div class="form-group">
                            <label for="goods_startUpdate" class="text-info">开始时间</label>
                            <input class="form-control" id="goods_startUpdate" name="startTime">
                        </div>
                        <div class="form-group">
                            <label for="goods_endUpdate" class="text-info">结束时间</label>
                            <input class="form-control" id="goods_endUpdate" name="endTime">
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <table id="goodsTable"></table>
                    <div id="tb">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-primary"><i class="fa fa-search"></i> 查询
                            </button>
                            <button type="button" class="btn btn-sm btn-default"><i class="fa fa-refresh"></i> 重置
                            </button>
                            <button type="button" class="btn btn-sm btn-success"><i class="fa fa-shopping-bag"></i> 添加商品
                            </button>
                            <button type="button" class="btn btn-sm btn-danger"><i class="fa fa-cut"></i> 删除商品
                            </button>
                            <button type="button" class="btn btn-sm btn-info"><i class="fa fa-modx"></i> 修改商品</button>
                            <button type="button" class="btn btn-sm btn-primary"><i class="fa fa-legal"></i> 修改数量
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>

<div id="goods_add_frame"></div>
<div id="goods_update_frame"></div>
<div id="goods_update_number_frame" class="hidden">
    <div class="container-fluid">
        <h4 class="text-center">原商品数量 <span></span></h4>
        <form class="form-horizontal" role="form" id="goodsUpdateNumberForm" method="post" style="margin-top: 10px">
            <input type="hidden" name="id">
            <div class="form-group">
                <label for="goods_update_goodsNumber" class="col-sm-4 control-label">增加/减少</label>
                <div class="col-sm-8">
                    <input type="number" class="form-control" id="goods_update_goodsNumber" name="number"
                           placeholder="请输入需要修改的商品数量,支持负数...">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <div class="btn-group">
                        <button type="submit" class="btn btn-default">保存</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>

<script src="${ROOT}/js/goods/index.js"></script>
</body>
</html>




