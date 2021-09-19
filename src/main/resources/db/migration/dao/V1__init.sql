create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  score                 int not null,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email, score)
values
       ('user', '$2y$12$uDTMf9F2Djwflqi5U8fwtenibQqqKRcIYqlhT8BSWqXwANBffFQQ.', 'user@gmail.com', 0),
       ('user1', '$2y$12$uDTMf9F2Djwflqi5U8fwtenibQqqKRcIYqlhT8BSWqXwANBffFQQ.', 'user1@gmail.com', 5),
       ('user2', '$2y$12$uDTMf9F2Djwflqi5U8fwtenibQqqKRcIYqlhT8BSWqXwANBffFQQ.', 'user2@gmail.com', 10);

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2);