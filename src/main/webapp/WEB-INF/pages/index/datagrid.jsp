<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2016/12/14
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="index-dataGrid"></table>

<script>
    /**
     * Created by hp on 2016/12/14.
     */
    $(function () {
        $("#index-dataGrid").datagrid({
            striped: true,
            url: ROOT + '/user/findAll.do',
            loadMsg: '正在加载数据...',
            pagination: true,
            height: '100%',
            width: '100%',
            rownumbers: true,
            singleSelect: true,
            pageNumber: 1,
            pageSize: 10,
            columns: [[
                {field: 'userName', title: '用户名称', width: 80},
                {field: 'loginName', title: '登录名', width: 100},
                {field: 'phone', title: '联系电话', width: 100, align: 'left'},
                {field: 'entryDate', title: '入职日期', width: 100, align: 'left'},
                {field: 'remark', title: '备注', width: 200, align: 'left'},
                {
                    field: 'status', title: '操作', width: 150, align: 'center', formatter: function (value, row) {
                    if (value == -1) {
                        return "<a href='javascript:void(0)' class='btn btn-success btn-sm' onclick='changeThisUser(this," + row.id + "," + value + ")'>启用</a>";
                    } else {
                        return "<a href='javascript:void(0)' class='btn btn-danger btn-sm' onclick='changeThisUser(this," + row.id + "," + value + ")'>禁用</a>";
                    }

                }
                }
            ]]
        });
    });
    /**
     * 启用、禁用用户
     * @param id
     */
    function changeThisUser(obj, id, status) {
        $.ajax({
            url: ROOT + '/user/changeStatus.do',
            data: {
                id: id,
                status: status
            },
            method: 'post',
            success: function (data) {
                $("#index-dataGrid").datagrid('reload');
                if (data.data) {
                    $.messager.alert("成功", data.msg);
                }

            }
        })
    }

</script>