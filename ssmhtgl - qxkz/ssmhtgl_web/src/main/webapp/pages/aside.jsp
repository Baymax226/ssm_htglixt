<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>
                    <security:authentication property="principal.username"></security:authentication>
                </p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index"><a
                    href="${pageContext.request.contextPath}/pages/main.jsp"><i
                    class="fa fa-dashboard"></i> <span>首页</span></a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/pages/calendar.jsp">
                    <i class="fa fa-calendar"></i> <span>日历</span>
                    <span class="pull-right-container">
              <small class="label pull-right bg-red">3</small>
              <small class="label pull-right bg-blue">17</small>
            </span>
                </a>
            </li>
            <li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
                <span>系统管理</span> <span class="pull-right-container">
                    <i class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">

                    <li id="system-setting">
                        <security:authorize access="hasRole('ADMIN')"><!-- 只有AMIND账户才可以看到用户管理 -->
                        <a href="${pageContext.request.contextPath}/user/findAll.do"> <i
                                class="fa fa-circle-o"></i> 用户管理
                        </a></li>
                    </security:authorize>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/role/findAll.do"> <i
                            class="fa fa-circle-o"></i> 角色管理
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/permission/findAll.do">
                        <i class="fa fa-circle-o"></i> 资源权限管理
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/sysLog/findAll.do"> <i
                            class="fa fa-circle-o"></i> 访问日志
                    </a></li>
                </ul>
            </li>
            <li class="treeview active">
                <a href="mailbox.html">
                    <i class="fa fa-envelope"></i> <span>邮箱</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/pages/mail-list.jsp">收件箱
                            <span class="pull-right-container">
                  <span class="label label-primary pull-right">13</span>
                </span>
                        </a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/pages/mail-write.jsp">发邮件</a></li>
                    <li><a href="${pageContext.request.contextPath}/pages/mail-show.jsp">邮件详情</a></li>
                </ul>
            </li>

            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>基础数据</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">

                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/product/findAll.do">
                        <i class="fa fa-circle-o"></i> 产品管理
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/orders/findAll.do?page=1&size=4"> <i
                            class="fa fa-circle-o"></i> 订单管理
                    </a></li>

                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>