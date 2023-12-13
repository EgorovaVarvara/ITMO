import mysql.connector
from mysql.connector import Error


def create_connection(host_name, user_name, user_password, db_name):
    connection = None
    try:
        connection = mysql.connector.connect(
            host=host_name,
            user=user_name,
            passwd=user_password,
            database=db_name
        )
        print("Connection to MySQL DB successful")
    except Error as e:
        print(f"The error '{e}' occurred")

    return connection


def create_database(connection, query):
    cursor = connection.cursor()
    try:
        cursor.execute(query)
        print("Database created successfully")
    except Error as e:
        print(f"The error '{e}' occurred")


def execute_query(connection, query):
    cursor = connection.cursor()
    try:
        cursor.execute(query)
        connection.commit()
        print("Query executed successfully")
    except Error as e:
        print(f"The error '{e}' occurred")


def execute_read_query(connection, query):
    cursor = connection.cursor()
    result = None
    try:
        cursor.execute(query)
        result = cursor.fetchall()
        return result
    except Error as e:
        print(f"The error '{e}' occurred")


connection = create_connection("localhost", "root", "########", "infLab4")

create_authors_table = """
CREATE TABLE IF NOT EXISTS authors(
    id INT AUTO_INCREMENT,
    name TEXT NOT NULL,
    age INT,
    gender TEXT,
    country TEXT,
    PRIMARY KEY(id)
) ENGINE = InnoDB
"""

create_genres_table = """
CREATE TABLE IF NOT EXISTS genres(
    id INT AUTO_INCREMENT,
    name TEXT NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
"""

create_albums_table = """
CREATE TABLE IF NOT EXISTS albums(
    id INT AUTO_INCREMENT,
    title TEXT NOT NULL,
    date_of_relise TEXT,
    author_id INTEGER NOT NULL,
    genre_id INTEGER NOT NULL,
    FOREIGN KEY fk_author_id (author_id) REFERENCES authors(id),
    FOREIGN KEY fk_genre_id (genre_id) REFERENCES genres(id),
    PRIMARY KEY (id)
) ENGINE = InnoDB
"""

create_songs_table = """
CREATE TABLE IF NOT EXISTS songs(
    id INT AUTO_INCREMENT,
    title TEXT NOT NULL,
    author_id INTEGER NOT NULL,
    album_id INTEGER NOT NULL,
    FOREIGN KEY fk_author_id (author_id) REFERENCES authors(id),
    FOREIGN KEY fk_album_id (album_id) REFERENCES albums(id),
    PRIMARY KEY (id)
) ENGINE = InnoDB
"""

execute_query(connection, create_authors_table)
execute_query(connection, create_genres_table)
execute_query(connection, create_albums_table)
execute_query(connection, create_songs_table)

create_authors = """
INSERT INTO
    `authors` (`name`, `age`, `gender`)
VALUES
    ('Lana Del Rey', 38, 'female'),
    ('Egor Kreed', 29, 'male'),
    ('Marlyn Manson', 54, 'male'),
    ('Maxim', 40, 'female'),
    ('Valentyn Strykalo', 35, 'male');
"""

create_genres = """
INSERT INTO
    `genres` (`name`)
VALUES
    ('Rock'),
    ('Pop'),
    ('Rap'),
    ('Metal'),
    ('Reggy'),
    ('Hip-Hop');
"""

create_albums = """
INSERT INTO
    `albums` (`title`, `date_of_relise`, `author_id`, `genre_id`)
VALUES
    ('Born to die', '27.01.2012', 1, 2),
    ('What do they know', '23.05.2017', 2, 3),
    ('Lest We Forget: The Best Of', '23.09.2004', 3, 4),
    ('Dificult age', '28.03.2006', 4, 2),
    ('Calm and relax', '24.02.2012', 5, 1),
    ('Part of something bigger', '20.10.2013', 5, 1);
"""

create_songs = """
INSERT INTO
    `songs` (`title`, `author_id`, `album_id`)
VALUES
    ('Dark Paradise', 1, 1),
    ('I`ll spend it', 2, 2),
    ('Lighters', 2, 2),
    ('Sweet Dreams', 3, 3),
    ('Become a wind', 4, 4),
    ('Do you know', 4, 4),
    ('Kayen', 5, 5),
    ('Rustem', 5, 5),
    ('All is decided', 5, 5),
    ('Pervomay', 5, 5),
    ('Stalevarov street', 5, 6),
    ('Office dude', 5, 6),
    ('Airplane graveyard', 5, 6);
"""

# execute_query(connection, create_authors)
# execute_query(connection, create_genres)
# execute_query(connection, create_albums)
# execute_query(connection, create_songs)

add_author = """
INSERT INTO
    `authors` (`name`, `age`, `gender`)
VALUES
    ('Zemfira', 47, 'Russia');
"""

add_genre = """
INSERT INTO
    `genres` (`name`)
VALUES
    ('Classic');
"""

add_album = """
INSERT INTO
    `albums` (`title`, `date_of_relise`, `author_id`, `genre_id`)
VALUES
    ('Foregive me, my love', '28.03.2000', 6, 1);
"""

add_song = """
INSERT INTO
    `songs` (`title`, `author_id`, `album_id`)
VALUES
    ('I was looking for', 6, 7);
"""

# execute_query(connection, add_author)
# execute_query(connection, add_genre)
# execute_query(connection, add_album)
# execute_query(connection, add_song)

for i in execute_read_query(connection, "SELECT * FROM songs"):
    print(i)

print()

for i in execute_read_query(connection, "SELECT songs.title, authors.name from songs INNER JOIN authors ON songs.author_id = authors.id"):
    print(i)

print()

for i in execute_read_query(connection, "SELECT * from albums WHERE genre_id = 1"):
    print(i)

print()

for i in execute_read_query(connection, "SELECT date_of_relise from albums GROUP BY date_of_relise"):
    print(i)

print()

select_in_select = """
SELECT songs.title FROM songs WHERE songs.album_id IN 
    (SELECT albums.id FROM albums WHERE albums.genre_id = 1)
"""

for i in execute_read_query(connection, select_in_select):
    print(i)

print()

using_union1 = """
SELECT songs.title FROM songs WHERE songs.album_id IN 
    (SELECT albums.id FROM albums WHERE albums.genre_id = 1)
UNION
SELECT albums.title FROM albums WHERE albums.genre_id = 1
"""
for i in execute_read_query(connection, using_union1):
    print(i)

print()

using_union2 = """
SELECT songs.title FROM songs WHERE songs.author_id = 5
UNION
SELECT albums.title FROM albums WHERE albums.author_id = 5
"""
for i in execute_read_query(connection, using_union2):
    print(i)

print()

using_distinct = "SELECT DISTINCT songs.author_id FROM songs"
for i in execute_read_query(connection, using_distinct):
    print(i)

print()

using_update1 = "UPDATE authors SET name = 'Валентин Стрыкало' WHERE name = 'Valentyn Strykalo'"
execute_query(connection, using_update1)
for i in execute_read_query(connection, "SELECT name FROM authors"):
    print(i)

print()
using_update2 = "UPDATE albums SET title = 'Трудный возраст' WHERE title = 'Dificult age'"
execute_query(connection, using_update2)
for i in execute_read_query(connection, "SELECT title FROM albums"):
    print(i)

print()

using_delete1 = "DELETE FROM authors WHERE id = 2"
using_delete2 = "DELETE FROM genres WHERE name='Pop'" # пояснить ошибку
using_delete3 = "DELETE FROM albums WHERE id = 2"
using_delete4 = "DELETE FROM songs WHERE album_id = 2"

execute_query(connection, using_delete1)
execute_query(connection, using_delete2)
execute_query(connection, using_delete3)
execute_query(connection, using_delete4)

using_delete5 = "DELETE FROM songs"

execute_query(connection, using_delete5)
