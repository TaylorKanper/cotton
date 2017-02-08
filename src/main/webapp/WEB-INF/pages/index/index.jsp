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
            <div class="col-lg-12">
                <h3 class="text-info">今日销售单</h3>
                <form class="form-inline" role="form" id="todayBuySearch" style="margin-top: 10px">
                    <div class="form-group">
                        <label for="today_memberPhone" class="text-info">手机号</label>
                        <input type="text" class="form-control" id="today_memberPhone" name="memberPhone"
                               placeholder="查询的会员手机...">
                    </div>
                    <div class="form-group">
                        <label for="today_firstSort" class="text-info">一级分类</label>
                        <select class="form-control" id="today_firstSort" name="firstId">
                            <option value="-1">全部</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="today_secondSort" class="text-info">二级分类</label>
                        <select class="form-control" id="today_secondSort" name="secondId">
                            <option value="-1">全部</option>
                        </select>
                    </div>
                    <button type="button" class="btn btn-info"><i class="fa fa-search"></i> 查询</button>
                </form>
                <table id="today_table"></table>
            </div>
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>

<jsp:include page="/common/footer.jsp"></jsp:include>

<script src="${ROOT}/js/index/index.js"></script>
</body>
</html>
