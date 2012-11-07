ALTER TABLE Project ADD creationDate datetime not null;
ALTER TABLE Project ADD endDate datetime not null;
ALTER TABLE Project ADD targetAmount double not null default 0;

UPDATE Project set creationDate=NOW(), endDate=NOW();
