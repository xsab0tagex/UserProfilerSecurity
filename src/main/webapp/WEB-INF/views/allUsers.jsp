<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список пользователей</title>
</head>
<body>
<br>
<br>
<br>
<br>
<h3>Список пользователей</h3>
${message}
<br>
<br>
<table border="1">
    <thead>

    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Login</th>
        <th></th>
        <th></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.userName}</td>
            <td>
                <form action="${pageContext.request.contextPath}/editUser/${user.id}" target="_self">
                    <button>Редактировать</button>
                </form></td>
            <td>
                <form action="${pageContext.request.contextPath}/deleteUser/${user.id}" target="_self">
                    <button>Удалить</button>
                </form>
            </td>

        </tr>
    </c:forEach>
    </tbody>


</table>
<br>
<br>
<br>
<form action="${pageContext.request.contextPath}/addUser" target="_self">
    <button>Добавить пользователя</button>
</form>

</body>
</html>