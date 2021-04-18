insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('juan', 'lopez', '10000A',"curso1", 30, "pendiente", "inmediata", "");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('rafa', 'montero', '20000A',"curso2", 40, "validado", "media", "");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('javi', 'diaz', '30000A',"curso3", 50, "pendiente", "inmediata", "");

insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('juan', 'gomez', '40000A',"curso4", 30, "pendiente", "inmediata", "");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('alan', 'palomo', '50000A',"curso5", 40, "validado", "media", "");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('miguel', 'gavilan', '60000A',"curso6", 20, "pendiente", "total", "");

insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('jesus', 'rojas', '70000A',"curso7", 30, "pendiente", "inmediata", "");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('antonio', 'cabezas', '80000A',"curso8", 40, "descartado", "total", "");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('marco', 'leal', '30000A',"curso9", 50, "pendiente", "inmediata", "");

insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('rafa', 'gonzalez', '90000A',"curso10", 30, "pendiente", "parcial", "");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('joaquin', 'espejo', '100000B',"curso11", 40, "validado", "media", "");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad, modalidad) values ('julio', 'gonzalez', '110000B',"curso12", 150, "pendiente", "inmediata", "");


insert into tags(name) values ('php');
insert into tags(name) values ('java');
insert into tags(name) values ('python');
insert into tags(name) values ('angular');
insert into tags(name) values ('linux');


insert into pivot (expert_id, tag_id) values (1, 1);
insert into pivot (expert_id, tag_id) values (1, 2);

insert into pivot (expert_id, tag_id) values (2, 3);

insert into pivot (expert_id, tag_id) values (3, 3);
insert into pivot (expert_id, tag_id) values (4, 5);

insert into pivot (expert_id, tag_id) values (5, 3);
insert into pivot (expert_id, tag_id) values (5, 2);

insert into pivot (expert_id, tag_id) values (6, 4);
insert into pivot (expert_id, tag_id) values (7, 2);

insert into pivot (expert_id, tag_id) values (8, 4);
insert into pivot (expert_id, tag_id) values (8, 1);

insert into pivot (expert_id, tag_id) values (9, 5);

insert into pivot (expert_id, tag_id) values (10, 3);
insert into pivot (expert_id, tag_id) values (10, 1);


insert into pivot (expert_id, tag_id) values (11, 2);
insert into pivot (expert_id, tag_id) values (12, 4);


insert into users (name, surname, dni, is_active) values ('name1', 'surname 1', '10000A', true);
