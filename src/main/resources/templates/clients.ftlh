<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Администратор</title>
</head>
<body>
<form input="post" action="/logout">
    <input type="submit" value="Выйти из системы">
</form>
<h1>Режим администратора</h1>
<h2>Список клиентов</h2>

<table border="1">
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
        <th>Дата рождения</th>
        <th>Логин</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    <#list clients as client>
        <tr>
            <td>${client.firstName}</td>
            <td>${client.secondName}</td>
            <td>${client.lastName}</td>
            <td>${client.birthDate}</td>
            <td>${client.userName}</td>
            <td>
                <a href="/clients/${client.id}">Просмотреть и изменить</a>
            </td>
            <td>
                <a href="/cards/${client.id}">Список карт</a>
            </td>
            <td>
                <a href="/transactions/client/${client.id}">Список транзакций</a>
            </td>
            <td>
                <form method="post" action="/clients/${client.id}/delete">
                    <input type="submit" value="Удалить"/>
                </form>
            </td>
    </#list>
</table>
</body>
</html>
