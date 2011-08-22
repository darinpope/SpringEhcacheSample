drop table country if exists;
drop table region if exists;

create table country (
  id integer primary key,
  code varchar(10) not null,
  country_name varchar(100) not null,
  continent varchar(100) not null
);

create table region (
  id integer primary key,
  code varchar(10) not null,
  local_code varchar(10) not null,
  region_name varchar(100) not null,
  continent varchar(100) not null,
  iso_country varchar(10) not null
);

