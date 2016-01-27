insert into products(name, price, description, created_date, product_type) values ('Filtre Kahve', 4.0, 'Filtre kahve blah', now(), 'BASE');
insert into products(name, price, description, created_date, product_type) values ('Latte', 5.0, 'Latte Açıklaması', now(), 'BASE');
insert into products(name, price, description, created_date, product_type) values ('Mocha', 6.0, 'Mocha Açıklaması', now(), 'BASE');
insert into products(name, price, description, created_date, product_type) values ('Çay', 3.0, 'Çay Açıklaması', now(), 'BASE');
insert into products(name, price, description, created_date, product_type) values ('Türk Kahvesi', 5.0, 'Türk Kahvesi Açıklaması', now(), 'BASE');

insert into products(name, price, description, created_date, product_type) values ('Süt', 2.0, 'Süt Açıklaması', now(), 'EXTRA');
insert into products(name, price, description, created_date, product_type) values ('Fındık Şurubu', 3.0, 'Fındık Şurubu Açıklaması', now(), 'EXTRA');
insert into products(name, price, description, created_date, product_type) values ('Çikolata Sosu', 5.0, 'Çikolata Sosu Açıklaması', now(), 'EXTRA');
insert into products(name, price, description, created_date, product_type) values ('Limon', 2.0, 'Limon Açıklaması', now(), 'EXTRA');

insert into customer(name, surname, username, password, role, status, created_date) values('Demo Account', 'Demo', 'demo@demo.com', '123', 1, true, now());
insert into customer(name, surname, username, password, role, status, created_date) values('Test Account', 'Test', 'test@test.com', '123', 2, true, now());