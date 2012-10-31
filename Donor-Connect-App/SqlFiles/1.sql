CREATE TABLE Project (
  id bigint(20) NOT NULL,
  description varchar(255) DEFAULT NULL,
  image varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  status tinyint(1) DEFAULT NULL,
  summary varchar(255) DEFAULT NULL,
  thumbnail varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);