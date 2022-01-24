<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Операции по карте</title>
</head>
<body>
<form method="get" action="/homepage">
    <input type="submit" value="Назад"/>
</form>
<h1>Операции пользователя ${card.getClient().userName}</h1>
<h2>Номер карты ${card.cardNum}</h2>
<table border="1">
    <tr>
        <th>Карта отправителя</th>
        <th>Карта получателя</th>
        <th>Дата</th>
        <th>Категория</th>
        <th>Название операции</th>
        <th>Сумма операции</th>
        <th>Валюта</th>
    </tr>
    <#list transactions as transaction>
    <tr>
        <td>${transaction.senderCard}</td>
        <td>${transaction.recipientCard}</td>
        <td>${transaction.transactionDate}</td>
        <td>${transaction.operation.category}</td>
        <td>${transaction.operation.operationName}</td>
        <td>${transaction.money}</td>
        <td>${card.currency.name}</td>
    </#list>
</table>
</body>
</html>
