INSERT INTO CATEGORY VALUES (-1, 0, 'Sightseeing');
INSERT INTO CATEGORY VALUES (-2, 0, 'Adventure');
INSERT INTO CATEGORY VALUES (-3, 0, 'Gastronomy');
INSERT INTO CATEGORY VALUES (-4, 0, 'Nature');
INSERT INTO CATEGORY VALUES (-5, 0, 'Family Friendly');
INSERT INTO GUIDE VALUES (-3, 0, 5.0,'fischer.borisz@gmail.com','./rjs.gif' ,10, 'BUDAPEST','+36 30 456 849', 50, 'Borisz');
INSERT INTO GUIDE VALUES (-2, 0, 3.0,'v.maxim@gmail.com','dtb.gif', 10, 'BUDAPEST','+36 70 456 849', 30, 'Maxim');
INSERT INTO GUIDE VALUES (-1, 0, 3.5,'tschaba.rocketeer@gmail.com','./kuglof.gif', 20, 'BUDAPEST','+36 20 456 849', 70, 'Csaba');

INSERT INTO EVENT VALUES (-1, 0, 1000, 'XVVVVVV', 'The Best Guided tour in the universe', 6,'BUDAPEST',null, null, -1, -3 );
INSERT INTO EVENT VALUES (-2, 0, 9999, 'VVVVVXX', 'See the crane migration from a close angle', 7,'HORTOBAGY',47.606105, 21.068087, -2, -3);
INSERT INTO EVENT VALUES (-3, 0, 3200, 'XVVVVVV', 'wiNE IS Mine....', 3, 'TOKAJ',48.123018, 21.4254,  -3, -2);
INSERT INTO EVENT VALUES (-4, 0, 55555, 'VVVVVVV', 'culture shock in  District 8th', 1,'BUDAPEST',null, null,  -1, -2);
INSERT INTO EVENT VALUES (-5, 0, 55555, 'XVVVVVV', 'Explore the culinary delights of potcake(kuglof in hungarian) Free samples included...', 1,'BUDAPEST',47.501445,19.16774, -3, -1);
INSERT INTO EVENT VALUES (-6, 0, 55556, 'XVVVVVV', 'gulasch suppe mit kartoffel', 1,'BALATON',46.913689, 17.880592, -3, -2);
INSERT INTO TRAVELLER VALUES (-4, 0, 'Japan', 'Mr. Hikiari Yojimbo','./assets/hikari.png', 'Hikari');
INSERT INTO TRAVELLER VALUES (-3, 0, 'U.S.A.', 'Steve, who forgot his password','./avatar.png', 'Steve');
INSERT INTO TRAVELLER VALUES (-2, 0, 'Germany', 'Herr Mayer', './assets/mayer.jpg', 'Mayer');
INSERT INTO TRAVELLER VALUES (-1, 0, 'Italy', 'Mario Ballotelli','./assets/balomario.jpeg', 'Mario');
INSERT INTO TRAVELLER VALUES (0, 0, 'Sweden', 'Default Defaultson','./avatar.png', 'Dafni');
insert into customer_group values ('admin');
insert into customer_group values ('guide');
insert into customer_group values ('traveler');
insert into customer values ('Borisz', 'Borisz', 'f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c');
insert into customer_customer_group values ('guide', 'Borisz');
insert into customer values ('Csaba', 'Csaba', 'f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c');
insert into customer_customer_group values ('guide', 'Csaba');
insert into customer values ('Maxim', 'Maxim', 'f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c');
insert into customer_customer_group values ('guide', 'Maxim');
insert into customer values ('Mario', 'Mario', 'f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c');
insert into customer_customer_group values ('traveler', 'Mario');
insert into customer values ('Hikari', 'Hikari', 'f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c');
insert into customer_customer_group values ('traveler', 'Hikari');
insert into customer values ('Mayer', 'Mayer', 'f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c');
insert into customer_customer_group values ('traveler', 'Mayer');
insert into customer values ('admin', 'admin', 'f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c');
insert into customer_customer_group values ('admin', 'admin');
-- INSERT INTO RESERVATION VALUES (5, 0, '2019-11-07', 1);