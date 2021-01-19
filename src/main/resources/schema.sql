CREATE TABLE employee (
  id INT(11) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email_address VARCHAR(255) NOT NULL,
  manager_id INT(11) DEFAULT 1,
  PRIMARY KEY(id)
);

