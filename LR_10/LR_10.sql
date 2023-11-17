INSERT INTO Users (first_name, last_name, email)
VALUES
    ('Name1', 'Surname1', 'email1@example.com'),
    ('Name2', 'Surname2', 'email2@example.com'),
    ('Name3', 'Surname3', 'email3@example.com'),
    ('Name4', 'Surname4', 'email4@example.com'),
    ('Name5', 'Surname5', 'email5@example.com'),
    ('Name6', 'Surname6', 'email6@example.com'),
    ('Name7', 'Surname7', 'email7@example.com'),
    ('Name8', 'Surname8', 'email8@example.com'),
    ('Name9', 'Surname9', 'email9@example.com'),
    ('Name10', 'Surname10', 'email10@example.com');

CREATE PROCEDURE get_user_by_id
    @user_id INT,
    @user_name VARCHAR(255) OUTPUT
AS
BEGIN
SELECT @user_name = first_name
FROM users
WHERE id = @user_id;
END;