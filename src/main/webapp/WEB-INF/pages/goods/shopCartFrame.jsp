<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">
    <h4>购物车总价 <span class="text-right text-danger font-weight-400" id="totalPrice"></span></h4>
    <form id="shopCartForm">
        <table id="shopCartTable" class="table table-bordered table-condensed table-striped table-hover">
            <thead>
            <tr>
                <th class="sr-only hidden">id</th>
                <th>商品名称</th>
                <th>一级分类</th>
                <th>二级分类</th>
                <th>购买数量</th>
                <th>成交价格</th>
                <th>折扣</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${shoppingCartList}" var="bean" varStatus="loop">
                <tr>
                    <td class="sr-only hidden"><input type="hidden" name="goodsId" value="${bean.goodsId}"></td>
                    <td class="col-xs-2">${bean.goodsName}</td>
                    <td class="col-xs-2">${bean.firstSortName}</td>
                    <td class="col-xs-2">${bean.secondSortName}</td>
                    <td class="col-xs-2"><input name="buyNumber" class="form-control input-sm" type="number"
                                                value="${bean.buyNumber}" min="1" step="1"></td>
                    <td class="col-xs-2"><input name="soldPrice" class="form-control input-sm" type="number"
                                                value="${bean.soldPrice}" min="0"></td>
                    <td class="col-xs-2">
                        <select name="discount" class="form-control input-sm">
                            <option value="1">无</option>
                            <option value="0.95">0.95</option>
                            <option value="0.9">0.9</option>
                            <option value="0.8">0.8</option>
                            <option value="0.85">0.85</option>
                            <option value="0.7">0.7</option>
                        </select></td>
                    <td class="col-xs-1"><a class="btn btn-sm btn-danger" href="javascript:void(0)"
                                            onclick="removeGoods(this,${bean.goodsId})">移除</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
    <form class="form-horizontal" role="form" id="member-form" method="post" style="margin-top: 10px">
        <div class="form-group">
            <label for="member-phone" class="col-sm-2 control-label">会员手机</label>
            <div class="col-sm-5">
                <input type="phone" class="form-control" id="member-phone" name="buyMemberPhone"
                       placeholder="请输入会员手机或名称匹配..." list="memberList">
                <datalist id="memberList">

                </datalist>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-8">
                <div class="btn-group" id="shopButton">
                    <button type="button" class="btn btn-primary">会员购买</button>
                    <button type="button" class="btn btn-default">非会员购买</button>
                </div>
            </div>
        </div>
    </form>

</div>
<script>
    $(function () {


        $.ajax({
            url: ROOT + "/member/findAllByName.do",
            method: "GET",
            success: function (data) {
                var s = '';
                for (var i in data.data) {
                    s += "<option value=" + data.data[i].memberPhone + ">" + data.data[i].memberName + " 手机号: " + data.data[i].memberPhone + "</option>";
                }
                $('#memberList').html(s);
            }
        });
        calculateTotalPrice();
        initEvent();


        /**
         * 初始化按钮事件
         */
        function initEvent() {
            var $member = $('#shopButton button').eq(0);
            var $noneMember = $('#shopButton button').eq(1);
            $member.bind('click', function () {
                var memberPhone = $('#member-phone').val();
                var reg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
                if (!reg.test(memberPhone)) {
                    $.messager.alert("提示", "请输入正确的会员手机号码");
                }
                var s = $('#member-phone').val();
                var list = $("#shopCartForm").serializeJson();
                var cart = {
                    list:JSON.parse(list),
                    buyMemberPhone:s
                };

                console.log(JSON.stringify(cart));
                $.ajax({
                    url: ROOT + '/shopping/buyGoodsWithMember.do',
                    method: "POST",
                    data: JSON.stringify(cart),
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        $.ROOT.handleActionResult(data);
                        $('#shopCartInfo').panel('close');
                    }
                })
            });
            $noneMember.bind('click', function () {
                var list = $('#shopCartForm').serializeJson();
                $.ajax({
                    url: ROOT + '/shopping/buyGoods.do',
                    method: "POST",
                    data: list,
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        $.ROOT.handleActionResult(data);
                        $('#shopCartInfo').panel('close');
                    }
                })
            })
        }
    })
</script>