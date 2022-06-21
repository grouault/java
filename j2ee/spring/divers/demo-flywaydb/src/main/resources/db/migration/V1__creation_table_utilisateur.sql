create sequence seq_utilisateur increment 1 start 1;

create table utilisateur(
  id int8 not null primary key,
  nom varchar(255) null,
  prenom varchar(255) null,
  email varchar(255) null
);