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
                    <h3 class="text-info">类目管理</h3>
                    <form class="form-inline" role="form" id="sortSearchForm" method="post" style="margin-top: 20px">
                        <div class="form-group">
                            <label for="index_sortName" class="text-info">类目名称</label>
                            <input type="text" class="form-control" id="index_sortName" name="sortName"
                                   placeholder="查询的类目名称...">
                        </div>
                        <div class="form-group">
                            <label for="index_sortLevel" class="text-info">类目等级</label>
                            <select class="form-control" id="index_sortLevel" name="level"
                                    placeholder="查询的类目等级...">
                                <option value="0">全部</option>
                                <option value="1">顶级</option>
                                <option value="2">二级</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="index_sortFatherName" class="text-info">父类目名称</label>
                            <input type="text" class="form-control" id="index_sortFatherName" name="fatherName"
                                   placeholder="查询的父类目名称...">
                        </div>
                        <div class="form-group">
                            <label for="index_sortDescription" class="text-info">描述</label>
                            <input type="text" class="form-control" id="index_sortDescription" name="description"
                                   placeholder="查询的类目描述...">
                        </div>
                        <button type="button" class="btn btn-info"><i class="fa fa-search"></i> 查询</button>
                        <button type="reset" class="btn btn-default"><i class="fa fa-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <table id="categoryTable"></table>
                    <div id="tb">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-success"><i class="fa fa-plus"></i> 添加一级类目</button>
                            <button type="button" class="btn btn-sm btn-info"><i class="fa fa-plus"></i>添加二级类目</button>
                            <button type="button" class="btn btn-sm btn-danger"><i class="fa fa-delicious"></i> 删除类目</button>
                            <button type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i>修改类目</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>

<div id="category_addFirstLevel"></div>
<div id="category_addSecondLevel"></div>
<div id="category_updateCategory"></div>
<jsp:include page="/common/footer.jsp"></jsp:include>

<script type="text/javascript" src="${ROOT}/js/category/index.js"></script>
</body>
</html>

