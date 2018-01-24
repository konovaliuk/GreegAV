<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <title>New Activity</title>
</head>
<body>
<div align="center">
    <form name="newActForm" method="post" action="MainServlet">
        <table align="center" width="25%" border="0">
            <tr>
                <td align="center">
                    Новая Активность<br>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td align="center"> <input type="text" name="newActName" required><br></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td align="center">
                    <input type="submit" name="command" value="Activity">
                </td>
            </tr>
        </table>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>

</html>
