/**
 * Created by hp on 2016/12/12.
 */
$(function () {
    initIndexDom();
    initTable();
    $("#main-menu a[url='/main/index']").addClass('active-menu');
});
function initIndexDom() {
    var d = {};
    $.ajax({
        url: ROOT + '/category/findAllCategory.do',
        method: "POST",
        success: function (data) {
            var $first = $('#today_firstSort'), $second = $('#today_secondSort');
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
    });
}
function initTable(){
    $("#today_table").datagrid({
        idField: 'id',
        loadMsg: '正在查询数据',
        emptyMsg: '没有查找到',
        url: ROOT + "/main/findAllToday.do",
        striped: true,
        loadMsg: '正在加载数据...',
        pagination: false,
        height: 460,
        width: '100%',
        rownumbers: true,
        singleSelect: true,
        checkOnSelect: false,
        selectOnCheck: false,
        pageNumber: 1,
        pageSize: 20,
        columns: [[
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
                return "<a class='btn btn-sm btn-primary' href='javascript:void(0)' onclick='buy(" + value + ")'>退货</a>"
            }
            }
        ]]
    })
}



