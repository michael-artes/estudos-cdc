insert into public.role (name) values ('ROLE_ADMIN');
insert into public.role (name) values ('ROLE_COMPRADOR');

ALTER TABLE public.produto ALTER COLUMN descricao TYPE text;

insert into public.user values ('admin@bookshopping.com.br', true, now(), 'Administrador', '$2a$10$kXmdUmJsfQupr6Q2YJfSeeek5eGoRSGTBdzSTo.hqB6/L2071ma0S');
insert into public.user_role (user_login, roles_name) values ('admin@bookshopping.com.br', 'ROLE_ADMIN');
