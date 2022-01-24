<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Карты клиента</title>
</head>
<body>
<form method="post" action="/back">
    <input type="submit" value=" << Отмена">
</form>
<h1>Карты клиента</h1>
<h2>${bankClient.lastName} ${bankClient.lastName} ${bankClient.secondName}</h2>
<table border="1">
    <tr>
        <th>Номер карты</th>
        <th>Баланс</th>
        <th>Валюта</th>
        <th></th>
        <th></th>
    </tr>
    <#list clientCards as card>
    <tr>
        <td>${card.cardNum}</td>
        <td>${card.money}</td>
        <td>${card.currency.name}</td>
        <td>
            <a href="/transactions/card/${card.cardNum}">Список транзакций</a>
        </td>
        <td>
            <form method="post" action="/clients/${bankClient.id}/delete">
                <input type="submit" value="Заблокировать"/>
            </form>
        </td>
        </#list>
</table>
</body>
</html>
