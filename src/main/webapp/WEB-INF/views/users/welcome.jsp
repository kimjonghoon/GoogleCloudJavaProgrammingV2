<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!-- content begin -->
<div id="content-categories"><spring:message code="user.membership" /></div>

<c:choose>
    <c:when test="${param.msg eq 'Success' }">
		<h3><spring:message code="user.welcome.heading" /></h3>
    </c:when>
    <c:when test="${param.msg eq 'NotEnabled' }">
		<h3><spring:message code="user.not.enabled.user.heading" /></h3>
    </c:when>
    <c:when test="${param.msg eq 'SameName' }">
		<h3><spring:message code="user.same.name.heading" /></h3>
    </c:when>
</c:choose>
<!-- content end -->