<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
	<%
		if (session != null) {
			session.invalidate();
	%>
	<%-- <jsp:forward page="login.html" /> --%>
	<c:redirect url="/index.html"/>
	<%
		} else {
	%>
	Logged Out Successfully....
	<%
		}
	%>
</body>
</html>
