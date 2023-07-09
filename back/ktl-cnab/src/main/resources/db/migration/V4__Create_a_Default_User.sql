--usernmame: firstuser@gmail.com
--password: firstuser
insert into cnab_user (id, first_name, last_name, password, username, refresh_token)
values ('43fadef7-3b18-437b-a1c1-ee1b1c7a8bf2', 'Fist', 'User', '$2a$10$5g8syOiylsDOv6x6YxcZRuJnLaJkWsFwM6A69YU.Pl1LBEMOJ8m9q', 'firstuser@gmail.com', '');

--usernmame: seconduser@gmail.com
--password: seconduser
insert into cnab_user (id, first_name, last_name, password, username, refresh_token)
values ('265119b6-d420-4ec8-9d5f-19acbd03595f', 'Second', 'User', '$2a$10$vSNKczC2b3p.ygTJFAmHw.5bnmv/B.NfN0yvhMEHcJUbZ/t3D0Q0C', 'seconduser@gmail.com', '');


insert into cnab_role (id, description, role_name)
values ('c0e30fa4-3734-4910-b4d1-8ad59f0d4504', 'ADMIN', 'ROLE_ADMIN');

insert into cnab_role (id, description, role_name)
values ('8e2fa8c3-5c30-4ca6-9f26-23785bc59086', 'MODERATOR', 'ROLE_MODERATOR');


insert into users_roles(user_id, role_id)
values ('43fadef7-3b18-437b-a1c1-ee1b1c7a8bf2', 'c0e30fa4-3734-4910-b4d1-8ad59f0d4504');

insert into users_roles(user_id, role_id)
values ('265119b6-d420-4ec8-9d5f-19acbd03595f', '8e2fa8c3-5c30-4ca6-9f26-23785bc59086');