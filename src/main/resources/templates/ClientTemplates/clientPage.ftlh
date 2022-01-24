<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Личный кабинет</title>
</head>
<h1>Добро пожаловать в личный кабинет, ${user.firstName}</h1>
<body>

<form action="/profile">
    <button type="submit">Профиль</button>
</form>

<form action="/profile/transactions/new">
    <button type="submit">Новый платеж</button>
</form>

<h2>Карты клиента</h2>
<table border="1">
    <tr>
        <th>Номер карты</th>
        <th>Баланс</th>
        <th>Валюта</th>
        <th></th>
        <th></th>
    </tr>
    <#list cards as card>
    <tr>
        <td>${card.cardNum}</td>
        <td>${card.money}</td>
        <td>${card.currency.name}</td>
        <td>
            <a href="/profile/transactions/${card.cardNum}">Список транзакций</a>
        </td>
        </#list>
</table>

<p>
<form action="/profile/transactions">
    <button type="submit">Последние операции</button>
</form>
</p>

<p>
<form input="post" action="/logout">
    <input type="submit" value="Выйти из системы">
</form>
</p>
</body>
</html>