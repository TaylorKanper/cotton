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
    $("#main-menu a[url='/goods/manager/index']").addClass('active-menu');
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
            {field: 'cost', title: '商品成本', width: 80, align: 'center'},
            {field: 'price', title: '商品售价', width: 80, align: 'center'},
            {field: 'number', title: '商品库存', width: 80, align: 'center'},
            {field: 'updateDate', title: '最后修改日期', width: 120, align: 'left'},
            {field: 'description', title: '备注', width: 280, align: 'left'}
        ]]
    })
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
        $("#goods_add_frame").dialog({
            title: '添加新商品',
            width: 570,
            height: 600,
            left: 450,
            href: ROOT + '/goods/addGoodsFrame.do',
            closed: false,
            cache: false,
            modal: true
        });
    });

    $("#tb").find("button[type='button']").eq(3).bind('click', function () {
        var row = $('#goodsTable').datagrid("getChecked");
        if (row.length == 0) {
            $.messager.alert("提示", "请勾选行进行删除");
        }
        var ids = [];
        for (var i in row) {
            ids.push(row[i].id);
        }
        $.messager.confirm('警告','你是否要删除这些勾选项,删除后将不能恢复',function (r) {
            if(r){
                $.ajax({
                    url: ROOT + "/goods/del.do",
                    method: "POST",
                    data: JSON.stringify(ids),
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        $.ROOT.handleActionResult(data);
                        $('#goodsTable').datagrid("reload");
                    }
                })
            }
        });
    });
    $("#tb").find("button[type='button']").eq(4).bind('click', function () {
        var row = $('#goodsTable').datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示", "请选中行进行修改操作");
            return;
        }
        $("#goods_update_frame").dialog({
            title: '修改商品',
            width: 570,
            height: 460,
            queryParams: row,
            method: 'post',
            left: 450,
            href: ROOT + '/goods/updateGoodsFrame.do',
            closed: false,
            cache: false,
            modal: true
        });
    });
    $("#tb").find("button[type='button']").eq(5).bind('click', function () {
        var row = $('#goodsTable').datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示", "请选中行进行修改操作");
            return;
        };
        $('#goods_update_number_frame').removeClass("hidden");
        $('#goods_update_number_frame').dialog({
            title: '修改数量',
            width: 370,
            height: 200,
            queryParams: row,
            closed: false,
            cache: false,
            modal: true
        }).find('h4>span').text(row.number);
        $('#goods_update_number_frame').find("input[type='number']").val('').attr({
            min: -row.number + 1
        });
        $('#goods_update_number_frame').find("input[type='hidden']").val(row.id);
        $('#goods_update_number_frame').ajaxForm({
            url: ROOT + '/goods/updateNumber.do',
            success: function (data) {
                $.ROOT.handleActionResult(data);
                $('#goodsTable').datagrid("reload");
                $('#goods_update_number_frame').panel('close');
                $('#goods_update_number_frame').addClass("hidden");
            }
        })
    })

}
