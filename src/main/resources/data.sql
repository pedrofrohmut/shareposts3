INSERT INTO shareposts.user
	(name, email, password)
VALUES
	('Jhon Doe', 'jhon@doe.com', '123123'),
    ('Camila Pitanga', 'camila@pitanga.com', '123123'),
    ('Daiane dos Santos', 'daiane@santos.com', '123123');

INSERT INTO shareposts.post 
	(user_id, title, body) 
VALUES 
	(1, 'Post One', 'Post One Body'),
    (1, 'Post Two', 'Post Two Body'),
    (2, 'Post Three', 'Post Three Body'),
    (2, 'Post Four', 'Post Four Body'),
    (3, 'Post Five', 'Post Five Body');