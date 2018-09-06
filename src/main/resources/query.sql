SELECT * FROM shareposts.post;

SELECT * FROM shareposts.user;
    
desc shareposts.user;

SELECT 
	post.id, post.title, post.body, post.created_at, post.user_id, user.name, user.email 
FROM 
	shareposts.post AS post 
INNER JOIN 
	shareposts.user AS USER 
ON 
	post.user_id = user.id;

/*
SELECT column_name(s)
FROM table1
INNER JOIN table2 ON table1.column_name = table2.column_name;