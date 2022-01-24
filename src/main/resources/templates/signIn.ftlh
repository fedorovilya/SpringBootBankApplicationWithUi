<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Авторизация</title>
</head>
<body>
<h1>Банковское приложение</h1>
<h3>Ввод данных</h3>
<form method="post" action="/signIn">
    <label>Логин:
        <input type="username" name="username" placeholder="Введите логин">
    </label>
    <label>Пароль:
        <input type="password" name="password" placeholder="Введите пароль">
    </label>
    <input type="submit" value="Вход">
</form>
<h3></h3>
<h3></h3>
<form method="post" action="/register">
    <input type="submit" value=" Новый пользователь?">
</form>
</body>
</html>