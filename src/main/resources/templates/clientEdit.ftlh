<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>${bankClient.firstName}</title>
</head>
<body>
<form action="/clients">
    <button type="submit">Отмена</button>
</form>
<h1>Редактирование профиля клиента</h1>
<h2>${bankClient.firstName} ${bankClient.lastName} ${bankClient.secondName}</h2>
<br>
<form method="post" action="/clients/${bankClient.id}/update">
    <label>
        Имя: <input name="firstName" value="${bankClient.firstName}">
    </label>
    <label>
        Фамилия: <input name="secondName" value="${bankClient.secondName}">
    </label>
    <label>
        Отчество: <input name="lastName" value="${bankClient.lastName}">
    </label>
    <label>
        Дата рождения: ${bankClient.birthDate} <input type=date name= "birthDay" value="${bankClient.birthDate}">
    </label>
    <label>
        Телефон: <input name= "phone" value="${bankClient.getContact().getPhone() !" "}">
    </label>
    <label>
        Email: <input name= "email" value="${bankClient.getContact().getEmail() !" "}">
    </label>
    <label>
        Telegram: <input name= "telegram" value="${bankClient.getContact().getTelegram() !" " }">
    </label>
    <label>
        VK: <input name= "vk" value="${bankClient.getContact().getVk() !" " }">
    </label>
    <label>
        Meta: <input name= "meta" value="${bankClient.getContact().getMeta() !" " }">
    </label>
    <input type="submit" value="Сохранить">
</form>
<#if errors??>
    <h1 style="color: red">${errors}</h1>
</#if>
</body>
</html>