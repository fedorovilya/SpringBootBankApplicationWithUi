<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Операции пользователя</title>
</head>
<body>
<form action="/clients">
    <button type="submit">Назад</button>
</form>
<#list cards as card>
    <h1> Номер карты ${card.cardNum}</h1>
    <table border="1">
        <tr>
            <th>Карта отправителя</th>
            <th>Карта получателя</th>
            <th>Дата</th>
            <th>Категория</th>
            <th>Название операции</th>
            <th>Сумма операции</th>
            <th>Валюта</th>
            <th></th>
        </tr>
        <#list card.transactionList as transaction>
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
</#list>
</body>
</html>
