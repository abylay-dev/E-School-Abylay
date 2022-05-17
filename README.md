# E-School (Abylay)

### Documentation

1. Чтобы скачать с гитхаба проект запускаем команду: `git clone https://github.com/abylay-dev/E-School-Abylay.git`.
2. Вам нужно запустить сервер с помощью приложении, как `XAMPP`, войти в <i>phpMyAdmin</i> затем создать БД под названием `eschool_abylay`. Затем проверьте <i>application.properties</i>, посмотрите правильность портов и названии БД. К примеру там стоит `jdbc:mysql://localhost:3306/eschool_abylay?useUnicode=true&serverTimezone=UTC`.
3. Запустите программу и ожидайте пока сервер запустится.
4. После того как программа запустилась, откройте любой браузер, и напишите URL: `localhost:8086`.
5. 
a) Затем вас сразу же перекинеть на страницу авторизации. <br>Аккаунт, к примеру: <br><pre>username -> `admin@gmail.com`
password -> `admin`</pre>
b) Если вы новый ползователь, то можете и нужно зарегистрироваться. 
6. Затем вы можете перейти на Главную страницу, где вы можете увидеть список студентов, страницу добавления студента и удалить из списка.

P. S. Если вы ЕЩЁ РАЗ хотите запустить проект после успешного запуска, то закоментируйте <b>32-37 строки включительно</b> в классе `UserServiceImpl`, который находится в папке **<i>service->impl->UserServiceImpl</i>**.