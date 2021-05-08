
insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date, puntuacion) values ('juan', '10000A', 601, "juan@lopez", "paseo de marsella", "curso1", 30, "pendiente", "inmediata", "online", "2021-04-22 01:00:00", "100");
insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('rafael', '20000A', 602, "rafa@montero", "paseo de cadiz", "curso2", 40, "validado", "media", "", "2011-04-21 01:02:00");
insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date, puntuacion) values ('javi', '30000A', 603, "javi@diaz", "paseo de malaga", "curso3", 50, "pendiente", "inmediata", "presencial", "2021-05-20 00:00:00", "90");

insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('juan','40000A', 604, "juan@gomez", "paseo de pozoblanco", "curso4", 30, "pendiente", "inmediata", "", "2021-04-21 01:00:00");
insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('alan',  '50000A', 605, "alan@palomo", "paseo de leon", "curso5", 40, "validado", "media", "", "2021-04-21 01:02:00");
insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('miguel', '60000A', 606, "mihuel@gavilan", "paseo de bruselas", "curso6", 20, "pendiente", "total", "", "2021-04-21 01:00:00");

insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('jesus', '70000A', 607, "jesus@rojas", "paseo de cordoba", "curso7", 30, "pendiente", "inmediata", "", "2021-03-21 01:00:00");
insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('antonio', '80000A', 608, "antonio@cabezas", "paseo de madrid", "curso8", 40, "descartado", "total", "", "2021-04-21 01:00:00");
insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('marco', '30000A', 609, "marco@leal", "paseo de badajoz", "curso9", 50, "pendiente", "inmediata", "", "2018-02-21 01:00:00");

insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('rafa', '90000A', 610, "rafa@gonzalez", "paseo de cordoba", "curso10", 30, "pendiente", "parcial", "", "2021-04-21 01:00:00");
insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('joaquin', '100000B', 611, "joaquin@espejo", "paseo de fuengirola", "curso11", 40, "validado", "media", "", "2021-04-30 01:00:00");
insert into experts (nombre, nif, telefono, correo, direccion, cursos, condiciones, estado, disponibilidad, modalidad, created_date) values ('julio',  '110000B', 612, "julio@gonzalez", "paseo de don benito", "curso12", 150, "pendiente", "inmediata", "", "2021-04-11 01:00:00");


insert into tags(name, created_date) values ('php', "2021-01-11 01:00:00");
insert into tags(name, created_date) values ('java', "2021-02-11 01:00:00");
insert into tags(name, created_date) values ('python', "2021-02-11 01:00:00");
insert into tags(name, created_date) values ('angular', "2020-04-11 01:00:00");
insert into tags(name, created_date) values ('linux', "2021-04-14 01:00:00");

insert into tags(name, created_date) values ('windows', "2020-08-11 01:00:00");
insert into tags(name, created_date) values ('mail', "2013-06-11 01:00:00");
insert into tags(name, created_date) values ('c++', "2012-01-21 01:00:00");
insert into tags(name, created_date) values ('c#', "2020-02-12 01:00:00");
insert into tags(name, created_date) values ('mysql', "2020-03-14 01:00:00");

insert into tags(name, created_date) values ('basic', "2001-01-11 01:00:00");
insert into tags(name, created_date) values ('cobol', "2011-02-11 01:00:00");


insert into pivot (expert_id, tag_id) values (1, 1);
insert into pivot (expert_id, tag_id) values (1, 2);

insert into pivot (expert_id, tag_id) values (2, 3);

insert into pivot (expert_id, tag_id) values (3, 3);
insert into pivot (expert_id, tag_id) values (4, 5);

insert into pivot (expert_id, tag_id) values (5, 3);
insert into pivot (expert_id, tag_id) values (5, 2);

insert into pivot (expert_id, tag_id) values (6, 4);
insert into pivot (expert_id, tag_id) values (7, 2);

insert into pivot (expert_id, tag_id) values (8, 1);
insert into pivot (expert_id, tag_id) values (8, 4);


insert into pivot (expert_id, tag_id) values (9, 5);

insert into pivot (expert_id, tag_id) values (10, 1);
insert into pivot (expert_id, tag_id) values (10, 3);



insert into pivot (expert_id, tag_id) values (11, 2);
insert into pivot (expert_id, tag_id) values (12, 4);


insert into users (email, password) values ('ingenia', '1234');
