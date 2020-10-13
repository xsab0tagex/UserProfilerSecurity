<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Редактирование пользователя</title>
</head>
<body>
<h1>${headerMessage}</h1>

<form:form method="POST" action="editUser" modelAttribute="user">

    <form:hidden path="id" />
    <table border="1">
        <tr>
            <td><form:label path="firstName">Имя</form:label></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Фамилия</form:label></td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="userName">Логин</form:label></td>
            <td><form:input path="userName"/></td>
        </tr>
        <tr>
            <td><form:label path="passWord">Пароль</form:label></td>
            <td><form:input path="passWord"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Подтвердить"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>