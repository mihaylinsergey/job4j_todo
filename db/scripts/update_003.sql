ALTER TABLE tasks ADD COLUMN user_id int REFERENCES todo_user(id);