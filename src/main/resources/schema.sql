CREATE TABLE team (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE employee (
  id INTEGER NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email_address VARCHAR(255) NOT NULL,
  roll VARCHAR(255) NOT NULL,
  team_id INT(11),
  PRIMARY KEY(id),
  FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE team_message (
    id INTEGER NOT NULL AUTO_INCREMENT,
    content VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sender_id INT(11),
    team_id INT(11),
    FOREIGN KEY (sender_id) REFERENCES employee(id),
    FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE employee_message (
    id INTEGER NOT NULL AUTO_INCREMENT,
    content VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    sender_id INT(11),
    employee_id INT(11),
    FOREIGN KEY (sender_id) REFERENCES employee(id),
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);



