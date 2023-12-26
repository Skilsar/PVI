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

select *
from WSREF;
select *
from WSREFCOMMENT;