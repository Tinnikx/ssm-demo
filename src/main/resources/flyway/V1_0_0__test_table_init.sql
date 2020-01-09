create table person (
    id int(11) auto_increment primary key,
    name varchar(255) default null,
    gender varchar(255) default null
) engine = innodb;