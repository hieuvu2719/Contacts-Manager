INSERT INTO sec_user(email, encryptedPassword)
VALUES('ronaldo@gmail.com','$2a$10$uz2KCe0/GD9KclAgyzWwa.fO1r3xV.wZjFRVuPS71EBRtoTV5/Paq');

INSERT INTO sec_user (email, encryptedPassword)
VALUES ('mickey@mail', '$2a$10$nfs8EEO7RXjAwpTs.jgyOeDcG9qgt8L9ud2CNnx0Hc1JvhnVa9Zqy');

INSERT INTO sec_user (email, encryptedPassword)
VALUES ('whois@who', '$2a$10$7Y5kNTjiRVQNVjdUoccBdeR1LoKzlESNmAWUnj9OrrWuqXoDcFzl.');

INSERT INTO sec_user(email, encryptedPassword)
VALUES('messi@gmail.com','$2a$10$uz2KCe0/GD9KclAgyzWwa.fO1r3xV.wZjFRVuPS71EBRtoTV5/Paq');




INSERT INTO sec_role (roleName)
VALUES ('ROLE_ADMIN');

INSERT INTO sec_role (roleName)
VALUES ('ROLE_MEMBER');

INSERT INTO sec_role (rolename)
VALUES ('ROLE_GUEST');



INSERT INTO user_role (userId, roleId)
VALUES (1, 1);

INSERT INTO user_role (userId, roleId)
VALUES (1, 2);

INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('Ronaldo',123456,'home','ronaldo@gmail.com','ROLE_ADMIN');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('Messi',0120212,'city','messi@gmail.com','ROLE_ADMIN');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('Who',00000,'home','whois@who','ROLE_ADMIN');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('Mickey',9999999,'nowhere','mickey@gmail.com','ROLE_ADMIN');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('Zac',123456,'home','zac@gmail.com','ROLE_MEMBER');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('Matthew',123456,'home','matthew@gmail.com','ROLE_MEMBER');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('Mike',123456,'home','ronaldo@gmail.com','ROLE_MEMBER');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('MrA',123456,'home','a@.com','ROLE_MEMBER');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('MrB',123456,'home','b@gl.com','ROLE_GUEST');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('knowknow',123456,'home','knowknow@gmail.com','ROLE_GUEST');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('Chloe',000000,'home','chloe@gmail.com','ROLE_GUEST');
INSERT INTO contact(name,phoneNumber,address,email,role)
VALUES('Sandra',111111,'home','sandra@gmail.com','ROLE_GUEST');
