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
<form action="/homepage">
    <button type="submit">Назад</button>
</form>
<h1>Редактирование профиля</h1>
<h2> ${bankClient.firstName} ${bankClient.lastName} ${bankClient.secondName}</h2>
<br>
<form method="post" action="/profile/update">
    <p>
    <label>
        Имя: <input name="firstName" value="${bankClient.firstName}">
    </label>
    </p>
    <p>
    <label>
        Фамилия: <input name="lastName" value="${bankClient.lastName}">
    </label>
    </p>
    <p>
    <label>
        Отчество: <input name="secondName" value="${bankClient.secondName}">
    </label>
    </p>
    <p>
    <label>
        Дата рождения: ${bankClient.birthDate} <input type=date name= "birthDay" value="${bankClient.birthDate}">
    </label>
    </p>
    <p>
    <label>
        Телефон: <input name= "phone" value="${bankClient.getContact().getPhone() !" "}">
    </label>
    </p>
    <p>
    <label>
        Email: <input name= "email" value="${bankClient.getContact().getEmail() !" "}">
    </label>
    </p>
    <p>
    <label>
        Telegram: <input name= "telegram" value="${bankClient.getContact().getTelegram() !" " }">
    </label>
    </p>
    <p>
    <label>
        VK: <input name= "vk" value="${bankClient.getContact().getVk() !" " }">
    </label>
    </p>
    <p>
    <label>
        Meta: <input name= "meta" value="${bankClient.getContact().getMeta() !" " }">
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