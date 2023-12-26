## Как это запустить?

1. Чекни в настройках докера 
Expose daemon on tcp://localhost:2375 without TLS
Если не включено, то включить и перезагрузить ноут.
2. Потом в папке с проектом выполни mvn clean package docker:build
3. Берешь файл docker-compose.yml (лежит в папке с лабой) и в папке с ним прописываешь docker compose up
4. Потом открываешь docker контейнер lab16 > javacont. В нем открываешь Files и идешь по пути 
usr > local > tomcat > webapps > "Lab16-1...(SNAPSHOT)...)" и туда кидаешь папку static из архива static в папке проекта (просто перетаскиваешь из проводника винды в проводник docker в папку "Lab16-1...(SNAPSHOT)...)".
5. теперь подрубаешься к бд и выполняй код SQL

create database uwsr;
use uwsr;

create table WSREF
(
    id          int primary key identity,
    url         varchar(100) not null,
    description varchar(200) not null,
    minus       int          not null default 0,
    plus        int          not null default 0
);

create table WSREFCOMMENT
(
    id         int primary key identity,
    wsref_id   int           not null references WSREF (id),
    session_id varchar(100)  not null,
    stamp      datetime      not null default sysdatetime(),
    comtext    nvarchar(200) not null
)

все, запускаешь прилагу!

P.S. 
ctrl + alt + A — Вход в приложение
ctrl + alt + G — Выход из приложения