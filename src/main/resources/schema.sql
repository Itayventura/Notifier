CREATE TABLE team(
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE employee (
  id INT(11) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email_address VARCHAR(255) NOT NULL,
  roll VARCHAR(255) NOT NULL,
  team_id INT(11),
  PRIMARY KEY(id),
  FOREIGN KEY (team_id) REFERENCES team(id)

);

CREATE TABLE team_message(
    id INT(11) NOT NULL AUTO_INCREMENT,
    content VARCHAR(255) NOT NULL,
    sender_id INT(11),
    team_id INT(11),
    FOREIGN KEY (sender_id) REFERENCES employee(id),
    FOREIGN KEY (team_id) REFERENCES team(id)
);

