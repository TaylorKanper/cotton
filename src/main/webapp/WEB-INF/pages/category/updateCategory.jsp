<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2016/12/18
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row" style="width: 550px;margin: auto;padding: 10px">
    <div class="col-sm-12">
        <h4 class="text-center">添加二级类目</h4>
        <form id="updateCategroy" class="form-horizontal" role="form">
            <input type="hidden" name="id" value="${category.id}">
            <div class="form-group">
                <label for="categoryName" class="col-sm-3 control-label">类目名称</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="categoryName" name="sortName"
                           value="${category.sortName}" placeholder="类目名称">
                </div>
            </div>

            <div class="form-group">
                <label for="parentId" class="col-sm-3 control-label">所属一级类目</label>
                <div class="col-sm-9">
                    <select class="form-control" id="parentId" name="fatherId" value="${category.fatherId}">
                        <c:if test="${category.fatherId>0}" var="second">
                            <c:forEach items="${categoryList }" var="bean">
                                <c:if test="${category.fatherId eq bean.id}" var="equal">
                                    <option value="${bean.id}" selected>${bean.sortName}</option>
                                </c:if>
                                <c:if test="${not equal}">
                                    <option value="${bean.id}">${bean.sortName}</option>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${not second}">
                            <option value="0">顶级</option>
                        </c:if>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="categoryRemark" class="col-sm-3 control-label">备注</label>
                <div class="col-sm-9">
                    <textarea class="form-control" id="categoryRemark" name="description"
                              placeholder="类目的说明">${category.description}</textarea>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <div class="btn-group">
                        <button type="submit" class="btn btn-success">保存</button>
                        <button id="reset" type="reset" class="btn btn-primary">重置</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#updateCategroy").validate({
            rules: {
                sortName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                }
            },
            messages: {
                sortName: {
                    required: "请输入类目名称",
                    minlength: "请输入至少2个字符",
                    maxlength: "名字请不要超过10个字符"
                }
            },
            errorElement: "em",
            errorPlacement: function (error, element) {
                // Add the `help-block` class to the error element
                error.addClass("help-block");

                // Add `has-feedback` class to the parent div.form-group
                // in order to add icons to inputs
                element.parents(".col-sm-9").addClass("has-feedback");

                if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.parent("label"));
                } else {
                    error.insertAfter(element);
                }

                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if (!element.next("span")[0]) {
                    $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
                }
            },
            success: function (label, element) {
                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if (!$(element).next("span")[0]) {
                    $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>").insertAfter($(element));
                }
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parents(".col-sm-9").addClass("has-error").removeClass("has-success");
                $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parents(".col-sm-9").addClass("has-success").removeClass("has-error");
                $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
            },
            submitHandler: function (form) {
                var o = $.ROOT.serializeObject($(form));
                $.ajax({
                    type: "POST",
                    url: ROOT + "/category/update.do",
                    data: o,
                    success: function (msg) {
                        $("#category_updateCategory").panel("close");
                        $("#categoryTable").datagrid('reload');
                        $.messager.alert("提示", msg.msg);
                    }
                })
            },
            ignore: "#categoryRemark"
        });
    });
</script>
