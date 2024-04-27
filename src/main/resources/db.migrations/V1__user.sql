create table users
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email        varchar(255) not null,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    birth_date   date         not null,
    address      varchar(255) null,
    phone_number varchar(20)  null
);
