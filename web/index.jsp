<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
  <title>Time-Tracker</title>
</head>

<body>
<div align="center">
  <form name="loginForm" method="post" action="MainServlet">
    <table align="center" width="25%" border="0">
      <tr>
        <td align="center">
          Login<br> <input type="text" name="nameInput" required><br>
          Password<br> <input type="password" name="passInput" required><br>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="center">
          <input type="submit" name="command" value="Login">
        </td>
      </tr>
    </table>
  </form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
