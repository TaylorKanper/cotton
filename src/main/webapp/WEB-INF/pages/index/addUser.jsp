<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2016/12/15
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="width: 500px;margin: auto;padding: 10px">
    <h4 class="text-center">添加新用户</h4>
    <form id="signUpForm" class="form-horizontal" role="form">
        <div class="form-group">
            <label for="userName" class="col-sm-3 control-label">名称</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="userName" name="userName" placeholder="真实姓名">
            </div>
        </div>
        <div class="form-group">
            <label for="loginName" class="col-sm-3 control-label">登录帐号</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="loginName" name="loginName" placeholder="登录帐号">
            </div>
        </div>
        <div class="form-group">
            <label for="passWord" class="col-sm-3 control-label">设置密码</label>
            <div class="col-sm-9">
                <input type="password" class="form-control" id="passWord" name="passWord" placeholder="输入密码">
            </div>
        </div>
        <div class="form-group">
            <label for="rePutPassWord" class="col-sm-3 control-label">确认密码</label>
            <div class="col-sm-9">
                <input type="password" class="form-control" id="rePutPassWord" name="rePutPassWord"
                       placeholder="请确认密码">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-3 control-label">电话号码</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" id="phone" name="phone" placeholder="电话号码">
            </div>
        </div>
        <div class="form-group">
            <label for="entryDate" class="col-sm-3 control-label">入职日期</label>
            <div class="col-sm-9">
                <input type="date" class="form-control" id="entryDate" name="entryDate" placeholder="入职日期">
            </div>
        </div>
        <div class="form-group">
            <label for="remark" class="col-sm-3 control-label">备注</label>
            <div class="col-sm-9">
                <textarea type="date" class="form-control" id="remark" name="remark" placeholder="备注"></textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <div class="btn-group">
                    <button type="submit" class="btn btn-default">保存</button>
                    <button id="reset" type="reset" class="btn btn-primary">重置</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script>
    $(function () {
        jQuery.validator.addMethod("isPhone", function (value, element) {
            var tel = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (tel.test(value));
        }, "请正确填写您的手机号码");
        $("#signUpForm").validate({
            rules: {
                userName: {
                    required: true,
                    minlength: 2,
                    maxlength: 4
                },
                loginName: {
                    required: true,
                    minlength: 2,
                    maxlength: 12
                },
                passWord: {
                    required: true,
                    minlength: 6
                },
                rePutPassWord: {
                    required: true,
                    minlength: 6,
                    equalTo: "#passWord"
                },
                phone: {
                    required: true,
                    isPhone: "#phone"
                },
                entryDate: {
                    required: true,
                    date: true
                }
            },
            messages: {
                userName: {
                    required: "请输入你的真实姓名",
                    minlength: "请输入至少2个字符",
                    maxlength: "名字请不要超过4个字符"
                },
                loginName: {
                    required: "请输入你的登录帐号",
                    minlength: "请输入至少2个字符",
                    maxlength: "名字请不要超过12个字符"
                },
                passWord: {
                    required: "请输入你的登录密码",
                    minlength: "登录密码至少6位"
                },
                rePutPassWord: {
                    required: "请输入确认密码",
                    minlength: "确认密码至少6位",
                    equalTo: "两次输入的密码不一致"
                },
                phone: "请输入正确的手机号码"
                ,
                entryDate: {
                    required: "请输入入职日期",
                    date: "请输入正确的日期"
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
                $.ajax({
                    type: "POST",
                    url: ROOT+"/user/addUser.do",
                    data: $(form).serialize(),
                    success: function (msg) {
                        $("#index_add_model").panel("close");
                        if (msg.code == 0) {
                            $.messager.alert('成功', msg.msg);
                        } else {
                            $.messager.alert('失败', msg.msg);
                        }
                    }
                })
            },
            ignore: "#remark"
        });
    });
</script>

