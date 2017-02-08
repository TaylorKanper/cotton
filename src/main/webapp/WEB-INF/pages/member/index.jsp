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
                    <h3 class="text-info">会员管理</h3>
                    <form class="form-inline" role="form" id="memberSearchForm" method="post" style="margin-top: 10px">
                        <div class="form-group">
                            <label for="member_memberName" class="text-info">会员名称</label>
                            <input type="text" class="form-control" id="member_memberName" name="memberName"
                                   placeholder="查询的会员名称...">
                        </div>
                        <div class="form-group">
                            <label for="member_memberNickName" class="text-info">会员昵称</label>
                            <input type="text" class="form-control" id="member_memberNickName" name="nickName"
                                   placeholder="查询的会员昵称...">
                        </div>
                        <div class="form-group">
                            <label for="member_memberPhone" class="text-info">会员手机号</label>
                            <input type="text" class="form-control" id="member_memberPhone" name="memberPhone"
                                   placeholder="查询的会员手机号...">
                        </div>
                        <div class="form-group">
                            <label for="member_chance" class="text-info">生日特权</label>
                            <select class="form-control" id="member_chance" name="chance">
                                <option value="0">全部</option>
                                <option value="1">未使用</option>
                                <option value="-1">已使用</option>
                            </select>
                        </div>

                        <div class="clearfix" style="margin-bottom: 10px"></div>
                        <div class="form-group">
                            <label for="member_lowScore" class="text-info">积分下限</label>
                            <input type="number" class="form-control" id="member_lowScore" name="lowScore"
                                   placeholder="查询的积分下限...">
                        </div>
                        <div class="form-group">
                            <label for="member_highScore" class="text-info">积分上限</label>
                            <input type="number" class="form-control" id="member_highScore" name="highScore"
                                   placeholder="查询的积分上限...">
                        </div>
                        <div class="form-group">
                            <label for="member_startTime" class="text-info">开始时间</label>
                            <input class="form-control" id="member_startTime" name="startTime">
                        </div>
                        <div class="form-group">
                            <label for="member_endTime" class="text-info">结束时间</label>

                            <input class="form-control" id="member_endTime" name="endTime">
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <table id="memberTable"></table>
                    <div id="tb">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-primary"><i class="fa fa-search"></i> 查询
                            </button>
                            <button type="button" class="btn btn-sm btn-default"><i class="fa fa-refresh"></i> 重置
                            </button>
                            <button type="button" class="btn btn-sm btn-success"><i class="fa fa-user-plus"></i>
                                添加会员
                            </button>
                            <c:if test="${user.userName eq '康鹏'||user.userName eq '王芮'}">
                                <button type="button" class="btn btn-sm btn-danger"><i class="fa fa-dropbox"></i> 删除会员
                                </button>
                                <button type="button" class="btn btn-sm btn-info"><i class="fa fa-modx"></i> 修改会员
                                </button>
                                <button type="button" class="btn btn-sm btn-success"><i class="fa fa-send"></i> 生日短信
                                </button>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>

<div id="member_add_frame"></div>
<div id="member_update_frame"></div>
<jsp:include page="/common/footer.jsp"></jsp:include>

<script src="${ROOT}/js/member/index.js"></script>
</body>
</html>




