CREATE TABLE IF NOT EXISTS db_sound_box.Countries (
	  id int NOT NULL AUTO_INCREMENT,
	  name varchar(255) NOT NULL,
	  slg varchar(10),
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  key(name),
	  UNIQUE(id, name)
	);
	
	CREATE TABLE IF NOT EXISTS db_sound_box.Person (
	  id int NOT NULL AUTO_INCREMENT,
	  name varchar(255) NOT NULL,
	  birthday date NOT NULL,
	  gender varchar(10) NOT NULL,
	  country_id int NOT NULL,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  FOREIGN KEY (country_id) REFERENCES Countries(id),
	  UNIQUE(id)
	);
	
	CREATE TABLE IF NOT EXISTS User (
	  id int NOT NULL AUTO_INCREMENT,
	  username varchar(255) UNIQUE NOT NULL,
	  email varchar(255) UNIQUE NOT NULL,
	  password varchar(255) NOT NULL,
	  person_id int,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),  
	  FOREIGN KEY (person_id) REFERENCES Person(id),
	  UNIQUE(id, username, email)
	);
	
	CREATE TABLE IF NOT EXISTS PlayList (
	  id int NOT NULL AUTO_INCREMENT,
	  name varchar(255),
	  user_id int,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  FOREIGN KEY (user_id) REFERENCES User(id),
	  UNIQUE(id)
	);	

  CREATE TABLE IF NOT EXISTS Style (
	  id int NOT NULL AUTO_INCREMENT,
	  name varchar(100),
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  UNIQUE(id)
	);

	CREATE TABLE IF NOT EXISTS Music (
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

	CREATE TABLE IF NOT EXISTS Music_Playlist (
	  id int NOT NULL AUTO_INCREMENT,
	  music_id int NOT NULL,
	  playlist_id int NOT NULL,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  FOREIGN KEY (playlist_id) REFERENCES PlayList(id),
	  FOREIGN KEY (music_id) REFERENCES Music(id),
	  UNIQUE(id)
	);
	
	CREATE TABLE IF NOT EXISTS Musical_Style (
	  id int NOT NULL AUTO_INCREMENT,
	  music_id int NOT NULL,
	  style_id int NOT NULL,
	  created_at timestamp DEFAULT (now()),
	  updated_at timestamp,
	  PRIMARY KEY(id),
	  FOREIGN KEY (music_id) REFERENCES Music(id),
	  FOREIGN KEY (style_id) REFERENCES Style(id),
	  UNIQUE(id)
	);
