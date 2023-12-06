CREATE TABLE IF NOT EXISTS db_sound_box.countries (
	  id int NOT NULL AUTO_INCREMENT,
	  name varchar(255) NOT NULL,
	  slg varchar(10),
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  key(name),
	  UNIQUE(id, name)
	);
	
	CREATE TABLE IF NOT EXISTS db_sound_box.person (
	  id int NOT NULL AUTO_INCREMENT,
	  name varchar(255) NOT NULL,
	  birthday date NOT NULL,
	  gender varchar(10) NOT NULL,
	  country_id int NOT NULL,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  FOREIGN KEY (country_id) REFERENCES countries(id),
	  UNIQUE(id)
	);
	
	CREATE TABLE IF NOT EXISTS user (
	  id int NOT NULL AUTO_INCREMENT,
	  username varchar(255) UNIQUE NOT NULL,
	  email varchar(255) UNIQUE NOT NULL,
	  password varchar(255) NOT NULL,
	  person_id int,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),  
	  FOREIGN KEY (person_id) REFERENCES person(id),
	  UNIQUE(id, username, email)
	);
	
	CREATE TABLE IF NOT EXISTS playList (
	  id int NOT NULL AUTO_INCREMENT,
	  name varchar(255),
	  user_id int,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  FOREIGN KEY (user_id) REFERENCES user(id),
	  UNIQUE(id)
	);	

  CREATE TABLE IF NOT EXISTS style (
	  id int NOT NULL AUTO_INCREMENT,
	  name varchar(100),
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  UNIQUE(id)
	);

	CREATE TABLE IF NOT EXISTS music (
	  id int NOT NULL AUTO_INCREMENT,
	  name varchar(255), 
	  artist_name varchar(100), 
	  album_name varchar(100), 
	  description varchar(500),
	  duration varchar(25), 
	  id_img_artist varchar(200), 
	  id_img_album varchar(200), 
	  id_img_music varchar(200), 
	  id_track varchar(200),
	  id_already_heard boolean default false,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  UNIQUE(id)
	);

	CREATE TABLE IF NOT EXISTS music_playlist (
	  id int NOT NULL AUTO_INCREMENT,
	  music_id int NOT NULL,
	  playlist_id int NOT NULL,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  FOREIGN KEY (playlist_id) REFERENCES playList(id),
	  FOREIGN KEY (music_id) REFERENCES music(id),
	  UNIQUE(id)
	);
	
	CREATE TABLE IF NOT EXISTS musical_style (
	  id int NOT NULL AUTO_INCREMENT,
	  music_id int NOT NULL,
	  style_id int NOT NULL,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  FOREIGN KEY (music_id) REFERENCES music(id),
	  FOREIGN KEY (style_id) REFERENCES style(id),
	  UNIQUE(id)
	);