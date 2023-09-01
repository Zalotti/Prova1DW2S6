CREATE TABLE task (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(250) NOT NULL,
	task_date DATE NOT NULL,
    observation VARCHAR(150) NOT NULL,
    user_id BIGINT(20),
    tag_id BIGINT(20),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (tag_id) REFERENCES tag(id)
)
