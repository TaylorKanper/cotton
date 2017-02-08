<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2016/12/22
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default top-navbar" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="javascript:void(0)"><i class="fa fa-comments"></i>
            <strong>棉屋管理系统 </strong></a>
    </div>

    <ul class="nav navbar-top-links navbar-right">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-messages">
                <li>
                    <a href="javascript:void(0)">
                        <div>
                            <strong>今天</strong>
                            <span class="pull-right text-muted">
                                        <em>今天</em>
                                    </span>
                        </div>
                        <div>已完成销售金额 ￥1,100 元</div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="javascript:void(0)">
                        <div>
                            <strong>昨天</strong>
                            <span class="pull-right text-muted">
                                        <em>昨天</em>
                                    </span>
                        </div>
                        <div>昨天总销售金额 ￥2,000 元</div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="javascript:void(0)">
                        <div>
                            <strong>前天</strong>
                            <span class="pull-right text-muted">
                                        <em>前天</em>
                                    </span>
                        </div>
                        <div>前天总销售金额 ￥2,100 元</div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="javascript:void(0)">
                        <strong>查看更多销售情况</strong>
                        <i class="fa fa-angle-right"></i>
                    </a>
                </li>
            </ul>
            <!-- /.dropdown-messages -->
        </li>
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-tasks">
                <li>
                    <a href="#">
                        <div>
                            <p>
                                <strong>衣服</strong>
                                <span class="pull-right text-muted">已售出102件</span>
                            </p>
                            <div class="progress progress-striped active">
                                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60"
                                     aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                    <span class="sr-only">60% Complete (success)</span>
                                </div>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <p>
                                <strong>裤子</strong>
                                <span class="pull-right text-muted">已售出28件</span>
                            </p>
                            <div class="progress progress-striped active">
                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="28"
                                     aria-valuemin="0" aria-valuemax="100" style="width: 28%">
                                    <span class="sr-only">28% Complete</span>
                                </div>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <p>
                                <strong>内裤</strong>
                                <span class="pull-right text-muted">已售出45件 </span>
                            </p>
                            <div class="progress progress-striped active">
                                <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60"
                                     aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                    <span class="sr-only">60% Complete (warning)</span>
                                </div>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <p>
                                <strong>其他</strong>
                                <span class="pull-right text-muted">已售出50件</span>
                            </p>
                            <div class="progress progress-striped active">
                                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="85"
                                     aria-valuemin="0" aria-valuemax="100" style="width: 85%">
                                    <span class="sr-only">85% Complete (danger)</span>
                                </div>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="#">
                        <strong>查看今天所有销售记录</strong>
                        <i class="fa fa-angle-right"></i>
                    </a>
                </li>
            </ul>
            <!-- /.dropdown-tasks -->
        </li>
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-alerts">
                <li>
                    <a href="#">
                        <div>
                            <i class="fa fa-comment fa-fw"></i> 拿裤子
                            <span class="pull-right text-muted small">2016-12-01</span>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <i class="fa fa-twitter fa-fw"></i> 剪裤脚
                            <span class="pull-right text-muted small">12 min</span>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <i class="fa fa-envelope fa-fw"></i> Message Sent
                            <span class="pull-right text-muted small">4 min</span>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <i class="fa fa-tasks fa-fw"></i> New Task
                            <span class="pull-right text-muted small">4 min</span>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <i class="fa fa-upload fa-fw"></i> Server Rebooted
                            <span class="pull-right text-muted small">4 min</span>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="#">
                        <strong>查看所有提醒</strong>
                        <i class="fa fa-angle-right"></i>
                    </a>
                </li>
            </ul>
            <!-- /.dropdown-alerts -->
        </li>
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="javascript:void (0)" id="user-info"><i class="fa fa-user fa-fw"></i>
                    欢迎你 <span>${user.userName}</span></a>
                </li>
                <c:if test="${user.userName eq '王芮'||user.userName eq '康鹏'}">
                    <li><a id="index_user_find" href="javascript:void(0)"><i class="fa fa-gear fa-fw"></i> 设置</a>
                    </li>
                    <li><a id="index_user_add" href="javascript:void(0)"><i class="fa fa-user-plus"></i> 添加用户</a>
                    </li>
                </c:if>
                <li class="divider"></li>
                <li><a href="${ROOT}/login.html"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
</nav>
<!--/. NAV TOP -->
<nav class="navbar-default navbar-side" role="navigation">
    <div id="sideNav" href=""><i class="fa fa-caret-right"></i></div>
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <c:forEach items="${menus}" var="menu">

                    <li><a href="javascript:void(0)" url="${menu.menuUrl}" onclick="openUrl(this)">
                        <i class="${menu.menuIcon}"></i>${menu.menuName}</a></li>

            </c:forEach>
        </ul>
    </div>
</nav>

<div id="index_add_model"></div>
<div id="index_find_model"></div>
<script>
    openUrl = function (obj) {
        window.location.href = ROOT + $(obj).attr('url') + ".do";
    }
</script>
