CREATE TABLE exame (rowid bigint auto_increment, nm_exame VARCHAR(255));
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');

CREATE TABLE funcionario (id_funcionario bigint auto_increment, nm_funcionario VARCHAR(255));
INSERT INTO funcionario (nm_funcionario) VALUES ('Arthur'), ('Rafhael'), ('Pablo'), ('Lucas');

CREATE TABLE realifunc (id_funcionario bigint, id_exame bigint, dt_realizacao DATE );
INSERT INTO realifunc (id_funcionario, id_exame, dt_realizacao) VALUES (1, 1, '2005-09-29'), (1,2, '2005-09-29'), (2,2,'2005-09-29');

UPDATE exame SET nm_exame = 'Cancer' WHERE rowid = 2;

