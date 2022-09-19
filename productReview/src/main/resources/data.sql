INSERT INTO category (name) VALUES ('Tops test');
INSERT INTO category (name) VALUES ('Jeans');
INSERT INTO category (name) VALUES ('Short');
INSERT INTO category (name) VALUES ('Tops');

INSERT INTO role (role) VALUES ('admin');
INSERT INTO role (role) VALUES ('user');

INSERT INTO product (name, price, rating, category_id, user_id) VALUES ('product B', 200, 3, 1, 2);
INSERT INTO product (name, price, rating, category_id, user_id) VALUES ('product A', 101, 4, 3, 2);
INSERT INTO product (name, price, rating, category_id, user_id) VALUES ('product C', 150, 1, 2, 2);

INSERT INTO address (city, street, zip) VALUES ('Fairfield', '605 E Broadway Ave', '52556');
INSERT INTO address (city, street, zip) VALUES ('Fairfield', '102 Forest Dr', '52556');
INSERT INTO address (city, street, zip) VALUES ('Fairfield', '2000 LibertyVille Road', '52556');

INSERT INTO users (email, first_name, last_name, password, gender, id_address) VALUES ('adam.smith@miu.edu', 'Adam', 'Smith', '1234', 'male', 1);
INSERT INTO users (email, first_name, last_name, password, gender, id_address) VALUES ('thi.truong@miu.edu', 'Thi', 'Truong', '1234', 'female', 2);
INSERT INTO users (email, first_name, last_name, password, gender, id_address) VALUES ('peter.pan@miu.edu', 'Peter', 'Pan', '1234', 'male', 3);

INSERT INTO users_roles(role_id, user_id) VALUES(2, 1);
INSERT INTO users_roles(role_id, user_id) VALUES(1, 2);

INSERT INTO review (comment, product_id, user_id) VALUES ('Comment 2', 1, 2);
INSERT INTO review (comment, product_id, user_id) VALUES ('Comment 5', 3, 3);
INSERT INTO review (comment, product_id, user_id) VALUES ('Comment 4', 3, 2);
INSERT INTO review (comment, product_id, user_id) VALUES ('Comment 3', 2, 3);
INSERT INTO review (comment, product_id, user_id) VALUES ('Comment 1', 1, 1);
