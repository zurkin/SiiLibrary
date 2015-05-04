<html>
<body>
	<%
		if (session != null) {
			session.invalidate();
	%>
	<jsp:forward page="login.html" />
	<%
		} else {
	%>
	Logged Out Successfully....
	<%
		}
	%>
</body>
</html>
