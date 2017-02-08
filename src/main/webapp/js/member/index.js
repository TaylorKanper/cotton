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
    $("#main-menu a[url='/member/index']").addClass('active-menu');
});
function initDom() {
    $('#member_startTime').datebox({
        editable: false,
        width: 180,
        height: 35,
        prompt: '生日开始时间...'
    });
    $('#member_endTime').datebox({
        editable: false,
        width: 180,
        height: 35,
        prompt: '生日结束时间...'
    });
}
/**
 * 初始化表格
 */
function initTable() {
    $("#memberTable").datagrid({
        idField: 'id',
        toolbar: '#tb',
        loadMsg: '正在查询数据',
        emptyMsg: '没有查找到',
        url: ROOT + "/member/findAllByPage.do",
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
            {field: 'memberName', title: '会员名称', width: 80},
            {field: 'nickName', title: '会员昵称', width: 80},
            {field: 'memberPhone', title: '会员手机号', width: 100, align: 'left'},
            {field: 'memberScore', title: '会员积分', width: 100, align: 'left'},
            {
                field: 'chance', title: '打折机会', width: 60, align: 'center', formatter: function (value) {
                return value == 1 ? '有' : '无';
            }
            },
            {field: 'memberBirthday', title: '会员生日', width: 100, align: 'left'},
            {field: 'memberCreate', title: '创建日期', width: 100, align: 'left'},
            {field: 'updateTime', title: '最后修改时间', width: 150, align: 'left'},
            {field: 'description', title: '备注', width: 200, align: 'left'}
        ]]
    })
}
/**
 * 初始化按钮点击事件
 */
function initEvent() {
    $("#tb").find("button[type='button']").eq(0).bind('click', function () {
        var o = $.ROOT.serializeObject($("#memberSearchForm"));
        $("#memberTable").datagrid({
            method: 'POST',
            url: ROOT + "/member/findAllByPage.do",
            queryParams: o
        });
    });
    $("#tb").find("button[type='button']").eq(1).bind('click', function () {
        var $table = $('#memberSearchForm');//获取页面上的表单,转变为dom对象调用dom对象的reset方法
        $table[0].reset();
        $('#member_startTime').datebox('clear');
        $('#member_endTime').datebox('clear');
    });
    $("#tb").find("button[type='button']").eq(2).bind('click', function () {
        $("#member_add_frame").dialog({
            title: '添加新会员',
            width: 570,
            height: 460,
            left: 450,
            href: ROOT + '/member/addMemberFrame.do',
            closed: false,
            cache: false,
            modal: true
        });
    });

    $("#tb").find("button[type='button']").eq(3).bind('click', function () {
        var row = $('#memberTable').datagrid("getChecked");
        if (row.length == 0) {
            $.messager.alert("提示", "请勾选行进行删除");
        }
        var ids = [];
        for (var i in row) {
            ids.push(row[i].id);
        }
        $.ajax({
            url: ROOT + "/member/del.do",
            method: "POST",
            data: JSON.stringify(ids),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                $.ROOT.handleActionResult(data);
                $('#memberTable').datagrid("reload");
            }
        })
        ;
    });
    $("#tb").find("button[type='button']").eq(4).bind('click', function () {
        var row = $('#memberTable').datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示", "请选中行进行修改操作");
            return;
        }
        $("#member_update_frame").dialog({
            title: '修改会员',
            width: 570,
            height: 460,
            queryParams: row,
            method: 'post',
            left: 450,
            href: ROOT + '/member/updateMemberFrame.do',
            closed: false,
            cache: false,
            modal: true
        });
    });
    $("#tb").find("button[type='button']").eq(5).bind('click', function () {
        var row = $('#memberTable').datagrid("getChecked");
        if (row.length == 0) {
            $.messager.alert("提示", "请勾选行进行发送短信");
            return;
        }
        $.messager.alert("提示","功能开发中...");
    });


}
