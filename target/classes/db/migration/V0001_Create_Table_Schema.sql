CREATE TABLE IF NOT EXISTS db_sound_box.Countries (
      id int NOT NULL AUTO_INCREMENT,
      name varchar(255) NOT NULL,
      slg varchar(10),
      PRIMARY KEY(id),
      UNIQUE(id)
    );

    CREATE TABLE IF NOT EXISTS db_sound_box.Person (
      id int NOT NULL AUTO_INCREMENT,
      name varchar(255) NOT NULL,
      birthday date NOT NULL,
      gender varchar(5) NOT NULL,
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

    CREATE TABLE IF NOT EXISTS Music_Playlist (
      id int NOT NULL AUTO_INCREMENT,
      key_music int,
      playlist_id int,
      PRIMARY KEY(id),
      FOREIGN KEY (playlist_id) REFERENCES PlayList(id),
      UNIQUE(id)
    );

    CREATE TABLE IF NOT EXISTS Music_already_heard (
      id int NOT NULL AUTO_INCREMENT,
      key_music int,
      PRIMARY KEY(id),
      UNIQUE(id)
    );