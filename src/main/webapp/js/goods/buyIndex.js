/**
 * Created by hp on 2016/12/21.
 */
/**
 * Created by hp on 2016/12/17.
 */
$(function () {
    initTable();
    initDom();
    initEvent();
    $("#main-menu a[url='/goods/buy/index']").addClass('active-menu');
});
function initDom() {
    $('#goods_startUpdate').datetimebox({
        prompt: '修改开始时间...',
        editable: false,
        showSeconds: false,
        width: 180,
        height: 35
    });
    $('#goods_endUpdate').datetimebox({
        prompt: '修改结束时间...',
        editable: false,
        showSeconds: false,
        width: 180,
        height: 35
    });

    var d = {};
    $.ajax({
        url: ROOT + '/category/findAllCategory.do',
        method: "POST",
        success: function (data) {
            var $first = $('#goods_goodsFirstSort'), $second = $('#goods_goodsSecondSort');
            d = data.data;
            var options = '';
            for (var i in d) {
                options += "<option value='" + d[i].id + "'>" + d[i].categoryName + "</option>";
            }
            function initSecond(id) {
                if (id == -1) {
                    $second.empty().append("<option value='-1'>全部</option>");
                } else {
                    for (var a in d) {
                        if (d[a].id == id) {
                            var options = "<option value='-1'>全部</option>";
                            for (var b in d[a].children) {
                                options += "<option value='" + d[a].children[b].id + "'>" + d[a].children[b].sortName + "</option>";
                            }
                            $second.empty().append(options);
                        }
                    }
                }
            }

            $first.append(options).bind("change", function () {
                initSecond($first.val());
            }).trigger('change');

        }
    })
}
/**
 * 初始化表格
 */
function initTable() {
    $("#goodsTable").datagrid({
        idField: 'id',
        toolbar: '#tb',
        loadMsg: '正在查询数据',
        emptyMsg: '没有查找到',
        url: ROOT + "/goods/findAllByPage.do",
        striped: true,
        loadMsg: '正在加载数据...',
        pagination: true,
        height: 430,
        width: '100%',
        rownumbers: true,
        singleSelect: true,
        checkOnSelect: false,
        selectOnCheck: false,
        pageNumber: 1,
        pageSize: 20,
        columns: [[
            {field: 'cb', checkbox: true},
            {field: 'goodsName', title: '商品名称', width: 80},
            {field: 'merchant', title: '商品厂家', width: 80},
            {field: 'firstSortName', title: '一级分类', width: 80, align: 'left'},
            {field: 'secondSortName', title: '二级分类', width: 80, align: 'left'},
            {field: 'price', title: '商品售价', width: 80, align: 'center'},
            {field: 'number', title: '商品库存', width: 80, align: 'center'},
            {field: 'updateDate', title: '最后修改日期', width: 120, align: 'left'},
            {field: 'description', title: '备注', width: 280, align: 'left'},
            {
                field: 'id', title: '操作', width: 80, align: 'center', formatter: function (value) {
                return "<a class='btn btn-sm btn-primary' href='javascript:void(0)' onclick='buy(" + value + ")'>购买</a>"
            }
            }
        ]]
    })
}

function buy(id) {
    $.ajax({
        url: ROOT + "/goods/buy/buyOne.do",
        method: "post",
        data: {id: id},
        success: function (data) {
            $.ROOT.handleActionResult(data);
            $('#goodsTable').datagrid("reload");
        }
    })
}
function removeGoods(obj, id) {
    $.ajax({
        url: ROOT + '/shopping/removeGoods.do',
        data: {id: id},
        method: 'GET',
        success: function (data) {
            $.ROOT.handleActionResult(data);
            $(obj).parent().parent().remove();
            calculateTotalPrice();
        }
    })
}
function calculateTotalPrice() {
    var tr = $('#shopCartTable tbody tr');
    var s = 0.0;
    for (var i = 0, j = tr.length; i < j; i++) {
        var buyNumber = $(tr[i]).find('input[name=buyNumber]').val();
        var soldPrice = $(tr[i]).find('input[name=soldPrice]').val();
        var discount = $(tr[i]).find('select[name=discount]').val();
        s += buyNumber * parseFloat(soldPrice) * parseFloat(discount);
    }
    $('#shopCartTable input,select').bind('change', calculateTotalPrice);
    s = parseFloat(s).toFixed(2);
    $('#totalPrice').text(s + "元");
}
/**
 * 初始化按钮点击事件
 */
function initEvent() {
    $("#tb").find("button[type='button']").eq(0).bind('click', function () {
        var o = $.ROOT.serializeObject($("#goodsSearchForm"));
        $("#goodsTable").datagrid({
            method: 'POST',
            url: ROOT + "/goods/findAllByPage.do",
            queryParams: o
        });
    });
    $("#tb").find("button[type='button']").eq(1).bind('click', function () {
        var $table = $('#goodsSearchForm');//获取页面上的表单,转变为dom对象调用dom对象的reset方法
        $table[0].reset();
        $('#goods_startUpdate').datebox('clear');
        $('#goods_endUpdate').datebox('clear');
    });
    $("#tb").find("button[type='button']").eq(2).bind('click', function () {
        var row = $('#goodsTable').datagrid('getChecked');
        if (row.length == 0) {
            $.messager.alert('提示', '请勾选中商品进行购买');
            return;
        }
        $.ajax({
            url: ROOT + "/goods/buy/buyGoods.do",
            method: "POST",
            data: JSON.stringify(row),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                $.ROOT.handleActionResult(data);
                $('#goodsTable').datagrid("reload").datagrid('uncheckAll');
            }
        })
    });

    $("#tb").find("button[type='button']").eq(3).bind('click', function () {
        var row = $('#goodsTable').datagrid('getSelected');
        if (!row) {
            $.messager.alert('提示', '请选中商品进行购买');
            return;
        }
        $('#multiBuy').removeClass('hidden');
        $('#multiBuy').dialog({
            title: '购买数量',
            width: 370,
            height: 200,
            closed: false,
            cache: false,
            modal: true
        }).find('h4>span').text(row.number);
        $('#multiBuyGoods').find("input[type='hidden']").val(row.id);
        $('#multiBuyGoods #goods_buyNumber').val('').attr({
            max: row.number
        });
        $('#multiBuyGoods').ajaxForm({
            url: ROOT + '/goods/buy/multiBuy.do',
            success: function (data) {
                $.ROOT.handleActionResult(data);
                $('#goodsTable').datagrid("reload");
                $('#multiBuy').panel('close');
                $('#multiBuy').addClass("hidden");
            }
        })
    });
    $("#tb").find("button[type='button']").eq(4).bind('click', function () {
        var row = $('#goodsTable').datagrid('getChecked');
        if (row.length == 0) {
            $.messager.alert('提示', '请勾选中商品进行购买');
            return;
        }
        $.ajax({
            url: ROOT + "/shopping/addGoodsToCart.do",
            method: "POST",
            data: JSON.stringify(row),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                $.ROOT.handleActionResult(data);
                $('#goodsTable').datagrid('uncheckAll');
            }
        })
    });
    $("#tb").find("button[type='button']").eq(5).bind('click', function () {
        $('#shopCartInfo').dialog({
            title: '购物车信息',
            href: ROOT + '/shopping/getCartFrame.do',
            width: 670,
            height: 450,
            closed: false,
            cache: false,
            modal: true
        });
    });
}
