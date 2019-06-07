<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- content begin -->
<script>
$(document).ready(function() {
	$('#changeNicknameForm').submit(function() {
		var nickname = $('#changeNicknameForm input[name*=nickname]').val();
		nickname = $.trim(nickname);
		if (nickname.length === 0) {
			alert('<spring:message code="invalid.nickname" />');
			$('#changeNicknameForm input[name*=nickname]').val('');
			return false;
		}
		$('#changeNicknameForm input[name*=nickname]').val(nickname);
	});
});
</script>
<div id="content-categories"><spring:message code="user.membership" /></div>
<h3><spring:message code="user.change.nickname" /></h3>
<form id="changeNicknameForm" action="changeNickname" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table style="width: 98%;">
<tr>
	<td>
		<spring:message code="user.nickname" />
		<input type="text" name="nickname" />
		<input type="submit" value="<spring:message code="global.submit" />" />
	</td>
</tr>
</table>
</form>
<!-- content end -->