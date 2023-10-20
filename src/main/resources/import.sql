-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
insert into Person (id, name, active, dateOfBirth) values(1, 'Jane Doe', true, '1977-12-09');
insert into Person (id, name, active, dateOfBirth) values(2, 'John Doe', true, '1982-12-30');
insert into Person (id, name, active, dateOfBirth) values(999, 'Flux Capacitor', false, '1955-11-05');
insert into Person (id, name, active, dateOfBirth) values(5, 'Melissa Smith', true, '1965-02-13');
insert into Person (id, name, active, dateOfBirth) values(20100, 'Rose Precede', true, '2001-08-22');
insert into Person (id, name, active, dateOfBirth) values(666, 'Foo Bar "Hello, world!" Baz', true, '2023-10-21');