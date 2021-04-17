insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad) values ('name1', 'surname 1', '10000A',"master1", 30, "pendiente", "inmediata");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad) values ('name2', 'surname 2', '20000A',"master2", 40, "validado", "media");
insert into experts (name, surname, nif, cursos, condiciones, estado, disponibilidad) values ('name3', 'surname 3', '30000A',"master3", 50, "pendiente", "inmediata");


insert into users (name, surname, dni, is_active) values ('name1', 'surname 1', '10000A', true);

insert into tags(name) values ('tag1');
insert into tags(name) values ('tag2');
insert into tags(name) values ('etiqueta1');
insert into tags(name) values ('etiqueta2');
insert into tags(name) values ('limpieza');


insert into pivot (expert_id, tag_id) values (1, 1);
insert into pivot (expert_id, tag_id) values (1, 2);
insert into pivot (expert_id, tag_id) values (2, 3);


