CREATE TABLE IF NOT EXISTS already_heard (
	  id int NOT NULL AUTO_INCREMENT,
	  music_id int NOT NULL,
	  user_id int NOT NULL,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  FOREIGN KEY (music_id) REFERENCES music(id),
	  FOREIGN KEY (user_id) REFERENCES user(id),
	  UNIQUE(id)
	);