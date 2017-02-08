<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row" style="width: 500px;margin: auto;padding: 10px">
    <div class="col-sm-12">
        <h4 class="text-center">添加商品</h4>
        <form id="addGoods" class="form-horizontal" role="form">
            <div class="form-group">
                <label for="goodsName" class="col-sm-3 control-label">商品名称</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="goodsName" name="goodsName" placeholder="商品名称...">
                </div>
            </div>
            <div class="form-group">
                <label for="merchant" class="col-sm-3 control-label">商品厂家</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="merchant" name="merchant" placeholder="商品厂家...">
                </div>
            </div>
            <div class="form-group">
                <label for="firstSortId" class="col-sm-3 control-label">商品一级分类</label>
                <div class="col-sm-9">
                    <select class="form-control" id="firstSortId" name="firstSortId">

                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="secondSortId" class="col-sm-3 control-label">商品二级分类</label>
                <div class="col-sm-9">
                    <select class="form-control" id="secondSortId" name="secondSortId">

                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="cost" class="col-sm-3 control-label">商品成本</label>
                <div class="col-sm-9">
                    <input type="number" id="cost" name="cost" class="form-control"
                           placeholder="商品成本..." min="1">
                </div>
            </div>

            <div class="form-group">
                <label for="price" class="col-sm-3 control-label">商品价格</label>
                <div class="col-sm-9">
                    <input type="number" id="price" name="price" class="form-control"
                           placeholder="商品价格..." min="1">
                </div>
            </div>

            <div class="form-group">
                <label for="number" class="col-sm-3 control-label">商品数量</label>
                <div class="col-sm-9">
                    <input type="number" id="number" name="number" class="form-control"
                           placeholder="商品数量..." min="1">
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-3 control-label">备注</label>
                <div class="col-sm-9">
                    <textarea class="form-control" id="description" name="description"
                              placeholder="关于该商品的一些说明..."></textarea>
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
</div>
<script type="text/javascript">
    $(function () {
        loadData();


        $("#addGoods").validate({
            rules: {
                goodsName: {
                    required: true,
                    minlength: 2,
                    maxlength: 10
                },
                merchant: {
                    required: false,
                    minlength: 1,
                    maxlength: 10
                },
                cost: {
                    required: true,
                    number: true,
                    min: 1
                },
                price: {
                    required: true,
                    number: true,
                    min: 1,
                    valueBelow: "#cost",
                },
                number: {
                    required: true,
                    number: true,
                    min: 1
                }
            },
            messages: {
                memberName: {
                    required: "请输入商品名称"
                },
                merchant: {
                    required: "请输入商品厂家"
                },
                price:{
                    valueBelow:"你的售价不能低于成本"
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
                o.firstSortName = $('#firstSortId option:selected').text().trim();
                o.secondSortName = $('#secondSortId option:selected').text().trim();
                $.ajax({
                    type: "POST",
                    url: ROOT + "/goods/add.do",
                    data: o,
                    success: function (msg) {
                        $("#goods_add_frame").panel("close");
                        $("#goodsTable").datagrid('reload');
                        $.ROOT.handleActionResult(msg);
                    }
                })
            },
            ignore: ".ignore"
        });
    });
    function loadData() {
        var d = {};
        $.ajax({
            url: ROOT + '/category/findAllCategory.do',
            method: "POST",
            success: function (data) {
                var $first = $('#firstSortId'), $second = $('#secondSortId');
                d = data.data;
                var options = '';
                for (var i in d) {
                    options += "<option value='" + d[i].id + "'>" + d[i].categoryName + "</option>";
                }
                function initSecond(id) {
                    for (var a in d) {
                        if (d[a].id == id) {
                            var options = '';
                            for (var b in d[a].children) {
                                options += "<option value='" + d[a].children[b].id + "'>" + d[a].children[b].sortName + "</option>";
                            }
                            $second.empty().append(options);
                        }
                    }
                }

                $first.append(options).bind("change", function () {
                    initSecond($first.val());
                }).trigger('change');

            }
        })
    }
</script>

