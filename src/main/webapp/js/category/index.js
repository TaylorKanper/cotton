/**
 * Created by hp on 2016/12/17.
 */
$(function () {
    initTable();
    initEvent();
    $("#main-menu a[url='/category/index']").addClass('active-menu');
});
/**
 * 初始化表格
 */
function initTable() {
    $("#categoryTable").datagrid({
        idField: 'id',
        loadMsg: '正在查询数据',
        emptyMsg: '没有查找到',
        url: ROOT + "/category/findAllByPage.do",
        toolbar: '#tb',
        striped: true,
        loadMsg: '正在加载数据...',
        pagination: true,
        height: 450,
        width: '100%',
        checkOnSelect: false,
        selectOnCheck: false,
        rownumbers: true,
        singleSelect: true,
        pageNumber: 1,
        pageSize: 20,
        columns: [[
            {field: 'cb', checkbox: true},
            {field: 'sortName', title: '类目名称', width: 200},
            {field: 'createDate', title: '创建日期', width: 100},
            {field: 'level', title: '类目等级', width: 100, align: 'left'},
            {field: 'fatherName', title: '父类目名称', width: 100, align: 'left'},
            {field: 'description', title: '说明', width: 470, align: 'left'}
        ]]
    })
}
/**
 * 初始化按钮点击事件
 */
function initEvent() {
    $("#sortSearchForm").find("button[type='button']").bind('click', function () {
        var o = $.ROOT.serializeObject($("#sortSearchForm"));

        $("#categoryTable").datagrid({
            method: 'POST',
            url: ROOT + "/category/findAllByPage.do",
            queryParams: o
        });
    });
    $("#tb button").eq(0).bind('click', function () {
        $('#category_addFirstLevel').dialog({
            title: '添加一级类目',
            width: 520,
            height: 310,
            left: 450,
            href: ROOT + '/category/addFirstLevel.do',
            closed: false,
            cache: false,
            modal: true
        });
    });
    $("#tb button").eq(1).bind('click', function () {
        $('#category_addSecondLevel').dialog({
            title: '添加二级类目',
            width: 570,
            height: 360,
            left: 450,
            href: ROOT + '/category/addSecondLevel.do',
            closed: false,
            cache: false,
            modal: true
        });
    });
    $("#tb button").eq(2).bind('click', function () {
        var list = $("#categoryTable").datagrid('getChecked');

        if (list.length == 0) {
            $.messager.alert('提示', '请勾选行进行删除');
            return;
        }
        $.ajax({
            url: ROOT + "/category/del.do",
            method: 'POST',
            data: JSON.stringify(list),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                if (data.code == 0) {
                    $.messager.alert('删除成功', "包括一级类目在内的" + data.data.result + "条类目已删除");
                } else if (data.code == 1) {
                    $.messager.alert('删除失败', data.msg)
                }
                $("#categoryTable").datagrid('reload');
            }
        })
    });
    $("#tb button").eq(3).bind('click', function () {
        var row = $("#categoryTable").datagrid('getSelected');
        if (row == null) {
            $.messager.alert('提示', "请选中蓝色底纹行修改");
            return;
        }
        $('#category_updateCategory').dialog({
            title: '修改类目信息',
            width: 570,
            height: 360,
            left: 450,
            method: 'POST',
            queryParams: row,
            href: ROOT + '/category/updateCategory.do',
            closed: false,
            cache: false,
            modal: true
        });
    })
}
