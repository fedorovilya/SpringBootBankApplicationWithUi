<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Новый платеж</title>
</head>
<body>
<form action="/homepage">
    <button type="submit">Назад</button>
</form>
<h1>Новый платеж</h1>
<h2> ${client.firstName} ${client.lastName} ${client.secondName}</h2>
<br>
<form method="post" action="/profile/transactions/new">
    <p>
        <label>
            Оплатить с:
        </label>
        <select id="senderselect">
            <#list cards  as card>
                <option>${card.cardNum}</option>
            </#list>
        </select>
    </p>
    <p>
        <label>
            Карта отправителя: <input name="sender" value="" placeholder="Введите номер карты получателя">
        </label>
    </p>
    <p>
        <label>
            Карта получателя: <input name="recipient" value="" placeholder="Введите номер карты получателя">
        </label>
    </p>
    <p>
        <label>
            Сумма платежа в : <input name="money" value="" placeholder="Сумма">>
        </label>
    </p>
    <p>
    <label>
        Операция:
    </label>
    <select id="operationSelect">
        <#list operations  as operation>
            <option>${operation.operationName}</option>
        </#list>
    </select>
    </p>
    <p>
        <label>
            Операция: <input name="operation" value="" placeholder="Введите название операции">
        </label>
    </p>
    <p>
        <input type="submit" value="Сохранить">
    </p>
</form>
<#if errors??>
    <h1 style="color: red">${errors}</h1>
</#if>
</body>
</html>