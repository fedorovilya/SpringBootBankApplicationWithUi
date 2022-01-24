<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Банк</title>
</head>
<body>
<h1>Добро пожаловать в банк!</h1>
<h3>Регистрация?</h3>
<form method="post" action="/signUp">
    <p>
        <label>Имя:
        <input name="firstName" placeholder="Введите имя">
        </label>
    </p>
    <p>
        <label>Фамилия:
        <input name="lastName" placeholder="Введите фамилию">
        </label>
    </p>
    <p>
        <label>Отчество:
        <input name="secondName" placeholder="Введите отчество">
        </label>
    </p>
    <p>
        <label>Дата рождения:
        <input type="date" placeholder="Дата рождения" name="birthDay">
        </label>
    </p>
    <p>
        <label>Email:
        <input type="email" placeholder="Адрес электронной почты" name="email">
        </label>
    </p>
    <p>
        <label>Логин:
        <input type="username" name="userName" placeholder="Введите логин">
        </label>
    </p>
    <p>
        <label>Пароль:
        <input type="password" name="password" placeholder="Введите пароль">
        </label>
    </p>
    <p>
    <input type="submit" value="Зарегистрироваться">
    </p>
</form>
<form method="post" action="/registered">
    <input type="submit" value="Уже зарегистрированы? >>">
</form>
</body>
</html>