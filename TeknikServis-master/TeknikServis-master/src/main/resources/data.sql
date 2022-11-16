
insert into public.product(id,name) values (1,'CPU') ON CONFLICT DO NOTHING;
insert into public.product(id,name) values (2,'GPU') ON CONFLICT DO NOTHING;
insert into public.product(id,name) values (3,'RAM') ON CONFLICT DO NOTHING;
insert into public.product(id,name) values (4,'Motherboard') ON CONFLICT DO NOTHING;

--insert into public.sale(fiyat, note, product_id) values ('8000', 'ram', 3);
--insert into public.sale(fiyat, note, product_id) values ('1000', 'gpu', 2);
--
--insert into public.basket(product_id, user_id) values (3, 1);
--insert into public.basket(product_id, user_id) values (2, 2);

insert into public.service(desktop, islem_adi, islem_suresi, laptop, mac) values (50, 'Formatlama', 2, 50, 200) ON CONFLICT DO NOTHING;
insert into public.service(desktop, islem_adi, islem_suresi, laptop, mac) values (100, 'Virüs temizliği', 4, 100, 100) ON CONFLICT DO NOTHING;
insert into public.service(desktop, islem_adi, islem_suresi, laptop, mac) values (200, 'Diskten veri kurtarma', 10, 200, 400) ON CONFLICT DO NOTHING;
insert into public.service(desktop, islem_adi, islem_suresi, laptop, mac) values (30, 'Fan ve termal macun temizliği', 1, 100, 200) ON CONFLICT DO NOTHING;


insert into public.role(name) values ('ROLE_USER') ON CONFLICT DO NOTHING;
insert into public.role(name) values ('ROLE_ADMIN') ON CONFLICT DO NOTHING;

insert into public.system_user(email, password, username) values ('zehra@hotmail.com', '$2a$10$UgsVWjo6R96SpL3/rXYyf.GwwE4yLasSl/Ll7BV/lCrDflg17hI.e', 'zehra') ON CONFLICT DO NOTHING;
insert into public.system_user(email, password, username) values ('sevval@hotmail.com', '$2a$10$UgsVWjo6R96SpL3/rXYyf.GwwE4yLasSl/Ll7BV/lCrDflg17hI.e', 'sevval') ON CONFLICT DO NOTHING;
insert into public.system_user(email, password, username) values ('hasan@hotmail.com', '$2a$10$UgsVWjo6R96SpL3/rXYyf.GwwE4yLasSl/Ll7BV/lCrDflg17hI.e', 'hasan') ON CONFLICT DO NOTHING;



insert into public.system_user_roles(system_user_id,  roles_name) values (1,'ROLE_USER') ON CONFLICT DO NOTHING;

insert into public.system_user_roles(system_user_id,  roles_name) values (3,'ROLE_USER') ON CONFLICT DO NOTHING;
insert into public.system_user_roles(system_user_id,  roles_name) values (2,'ROLE_ADMIN') ON CONFLICT DO NOTHING;

insert into public.proposal(durum, fiyat, note, product_id ,user_id) values (false, 3000, 'teklif yapıldı', 3, 1);
insert into public.proposal(durum, fiyat, note, product_id ,user_id) values (true, 750, 'zehranın notu', 1, 1);
insert into public.proposal(durum, fiyat, note, product_id ,user_id) values (false, 500, 'teklif yapıldı', 4, 3);
insert into public.proposal(durum, fiyat, note, product_id ,user_id) values (true, 7550, 'teklif yapıldı', 2, 3);

insert into public.booking(note, zaman, service_id, user_id) values ('randevu alma', '2022-10-10', 2, 1) ON CONFLICT DO NOTHING;
insert into public.booking(note, zaman, service_id, user_id) values ('randevu', '2022-10-10', 1, 2) ON CONFLICT DO NOTHING;
insert into public.booking(note, zaman, service_id, user_id) values ('randevu', '2022-10-10', 1, 1) ON CONFLICT DO NOTHING;

INSERT INTO public.sale(fiyat, product_id) VALUES (10, 1);
INSERT INTO public.sale(fiyat, product_id) VALUES (20, 2);
INSERT INTO public.sale(fiyat, product_id) VALUES (30, 3);
INSERT INTO public.sale(fiyat, product_id) VALUES (40, 4);
INSERT INTO public.basket(sale_id, user_id) VALUES (1, 1);

