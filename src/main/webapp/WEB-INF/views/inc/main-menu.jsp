<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<ul id="nav">
    <li><a href="/java">Java</a></li>
    <li><a href="/jdbc">JDBC</a></li>
    <li><a href="/jsp">JSP</a></li>
    <li><a href="/css-layout">CSS Layout</a></li>
    <li><a href="/jsp-pjt">JSP Project</a></li>
    <li><a href="/spring">Spring</a></li>
    <li><a href="/javascript">JavaScript</a></li>
    <li><a href="/google-app-engine">Google Cloud</a></li>
    <li><a href="/blog">Blog</a></li>
    <li><a href="/bbs/chat?page=1">BBS</a></li>
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <li><a href="/admin">Admin</a></li>
    </security:authorize>
</ul>
