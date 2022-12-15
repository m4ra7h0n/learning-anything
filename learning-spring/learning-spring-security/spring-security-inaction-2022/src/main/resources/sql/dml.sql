insert into spring_security.sys_user
values (1, 'xjj', '{noop}12345', 1, 1, 1);

insert into spring_security.role
values ('1', 'ROLE_USER', '用户角色', 1);

insert into spring_security.role
values ('2', 'ROLE_ADMIN', '管理员角色', 1);

insert into spring_security.user_role
values ('1', 'xjj', '1', 'ROLE_USER', 1);

insert into spring_security.user_role
values ('2', 'xjj', '2', 'ROLE_ADMIN', 1);
