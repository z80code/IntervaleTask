<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head> 
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
	<h1>Notes</h1>

	<c:forEach var="note" items="${notes}">
       Item <c:out value="${note.title}"/><p>
       Item <c:out value="${note.body}"/><p>
       <hr>
    </c:forEach>

</body>
</html>
