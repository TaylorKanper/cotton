/**
 * Created by hp on 2016/12/12.
 */
$(function () {
    initIndexDom();
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



