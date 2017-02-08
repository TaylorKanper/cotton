/**
 * Created by hp on 2016/12/19.
 */
$(function () {
    initUserBtn();
    initSideNavEvent();

this.serializeObject = function (form) {
    var o = {};
    $.each(form.serializeArray(), function () {
        if (this['value'] != '') {
            if (o[this['name']]) {
                o[this['name']] = o[this['name']] + "," + this['value'];
            } else {
                o[this['name']] = this['value'];
            }
        }
    });
    return o;
};
this.handleActionResult = function (obj) {
    if (obj.code == 0) {
        $.messager.alert(obj.msg, obj.data);
    } else if (obj.code == 1) {
        $.messager.alert('失败', obj.msg);
    }
};
jQuery.validator.addMethod("phone", function (value, element) {
    var tel = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写正确的手机号码");
jQuery.validator.addMethod("valueBelow", function (value, element, params) {
    var val = parseInt($(params.toString()).val());
    return this.optional(element) || value >= val;
}, $.validator.format("请检查你的输入值大小"));

$.ROOT = this;
return jQuery;
})
;
(function($){
    $.fn.serializeJson = function(){
        var jsonData1 = {};
        var serializeArray = this.serializeArray();
        // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
        $(serializeArray).each(function () {
            if (jsonData1[this.name]) {
                if ($.isArray(jsonData1[this.name])) {
                    jsonData1[this.name].push(this.value);
                } else {
                    jsonData1[this.name] = [jsonData1[this.name], this.value];
                }
            } else {
                jsonData1[this.name] = this.value;
            }
        });
        // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
        var vCount = 0;
        // 计算json内部的数组最大长度
        for(var item in jsonData1){
            var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
            vCount = (tmp > vCount) ? tmp : vCount;
        }

        if(vCount > 1) {
            var jsonData2 = new Array();
            for(var i = 0; i < vCount; i++){
                var jsonObj = {};
                for(var item in jsonData1) {
                    jsonObj[item] = jsonData1[item][i];
                }
                jsonData2.push(jsonObj);
            }
            return JSON.stringify(jsonData2);
        }else{
            return "[" + JSON.stringify(jsonData1) + "]";
        }
    };
})(jQuery);

/**
 * 初始化左侧菜单事件
 */
function initSideNavEvent() {
    $("#sideNav").click(function () {
        if ($(this).hasClass('closed')) {
            $('.navbar-side').animate({left: '0px'});
            $(this).removeClass('closed');
            $('#page-wrapper').animate({'margin-left': '260px'});
        }
        else {
            $(this).addClass('closed');
            $('.navbar-side').animate({left: '-260px'});
            $('#page-wrapper').animate({'margin-left': '0px'});
        }
    });
}
/**
 * 给用户下拉模态框绑定事件
 */
function initUserBtn() {
    $("#index_user_add").bind('click', function () {
        $('#index_add_model').dialog({
            title: '添加新用户',
            width: 600,
            height: 600,
            href: ROOT + '/user/addUserFrame.do',
            closed: false,
            cache: false,
            modal: true
        });
    });
    $("#index_user_find").bind('click', function () {
        $('#index_find_model').dialog({
            title: '查看用户',
            width: 780,
            height: 410,
            left: 450,
            href: ROOT + '/user/datagrid.do',
            closed: false,
            cache: false,
            modal: true
        });
    });
}