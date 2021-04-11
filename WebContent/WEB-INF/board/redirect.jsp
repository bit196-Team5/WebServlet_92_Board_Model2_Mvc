<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<c:set var="msg" value="${requestScope.board_msg}"></c:set>
<c:set var="url" value="${requestScope.board_url}"></c:set>
<c:if test="${msg != null && url != null}">
	<script>
		alert('${msg}');		
	    location.href='${url}';
	</script>
</c:if>
