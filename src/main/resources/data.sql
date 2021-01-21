INSERT INTO team (name, department) VALUES ('sw1', 'R&D');
INSERT INTO team (name, department) VALUES ('sw2', 'R&D');

INSERT INTO employee (first_name, last_name, email_address, roll, team_id) VALUES ('Itay', 'Ventura', 'a@a.com', 'software developer', 1);
INSERT INTO employee (first_name, last_name, email_address, roll, team_id) VALUES ('Mani', 'mani', 'c@c.com', 'Team leader', 1);
INSERT INTO employee (first_name, last_name, email_address, roll, team_id) VALUES ('Moshe', 'Moshiko', 'b@b.com', 'software developer', 2);
INSERT INTO team_message (content, sender_id, team_id) VALUES ('This is a first message', 2, 1);