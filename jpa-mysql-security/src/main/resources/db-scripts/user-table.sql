create table user (
    id int primary key auto_increment,
    active int,
    password varchar(100) not null default '',
    roles varchar(100) not null default 'ROLE_USER',
    user_name varchar(100) not null default 'user'
)