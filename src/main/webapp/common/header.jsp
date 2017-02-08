<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2016/12/22
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%
    // 项目路径
    String ROOT = request.getContextPath();
    request.setAttribute("ROOT", ROOT);
%>
<script type="text/javascript">
    var ROOT = "${ROOT}";
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<!-- Bootstrap Styles-->
<link href="${ROOT}/bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet"/>
<link href="${ROOT}/plugins/EasyUI/theme/bootstrap/easyui.css" rel="stylesheet">
<link href="${ROOT}/plugins/EasyUI/theme/icon.css" rel="stylesheet">
<link href="${ROOT}/plugins/EasyUI/theme/color.css" rel="stylesheet">
<!-- FontAwesome Styles-->
<link href="${ROOT}/css/font-awesome.css" rel="stylesheet"/>
<link href="${ROOT}/css/custom-styles.css" rel="stylesheet"/>
<link href="${ROOT}/favicon/favicon.ico" rel="shortcut icon"/>