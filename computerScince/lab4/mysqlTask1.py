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


connection = create_connection("localhost", "root", "v156727122005V", "sm_app")

create_users_table = """
CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT, 
  name TEXT NOT NULL, 
  age INT, 
  gender TEXT, 
  nationality TEXT, 
  PRIMARY KEY (id)
) ENGINE = InnoDB
"""
execute_query(connection, create_users_table)

create_posts_table = """
CREATE TABLE IF NOT EXISTS posts (
  id INT AUTO_INCREMENT, 
  title TEXT NOT NULL, 
  description TEXT NOT NULL, 
  user_id INTEGER NOT NULL, 
  FOREIGN KEY fk_user_id (user_id) REFERENCES users(id), 
  PRIMARY KEY (id)
) ENGINE = InnoDB
"""

execute_query(connection, create_posts_table)

create_comments_table = """
CREATE TABLE IF NOT EXISTS comments (
  id INT AUTO_INCREMENT, 
  text TEXT NOT NULL, 
  user_id INTEGER NOT NULL, 
  post_id INTEGER NOT NULL,
  FOREIGN KEY fk_user_id (user_id) REFERENCES users(id), 
  FOREIGN KEY fk_post_id (post_id) REFERENCES posts(id),
  PRIMARY KEY (id)
) ENGINE = InnoDB
"""

execute_query(connection, create_comments_table)

create_likes_table = """
CREATE TABLE IF NOT EXISTS likes (
  id INT AUTO_INCREMENT,
  user_id INTEGER NOT NULL, 
  post_id INTEGER NOT NULL,
  FOREIGN KEY fk_user_id (user_id) REFERENCES users(id),
  FOREIGN KEY fk_post_id (post_id) REFERENCES posts(id), 
  PRIMARY KEY (id)
) ENGINE = InnoDB
"""

execute_query(connection, create_likes_table)

# create_users = """
# INSERT INTO
#   `users` (`name`, `age`, `gender`, `nationality`)
# VALUES
#   ('James', 25, 'male', 'USA'),
#   ('Leila', 32, 'female', 'France'),
#   ('Brigitte', 35, 'female', 'England'),
#   ('Mike', 40, 'male', 'Denmark'),
#   ('Elizabeth', 21, 'female', 'Canada');
# """

# execute_query(connection, create_users)

# create_posts = """
# INSERT INTO
#   `posts` (`title`, `description`, `user_id`)
# VALUES
#   ("Happy", "I am feeling very happy today", 1),
#   ("Hot Weather", "The weather is very hot today", 2),
#   ("Help", "I just saved the Paris!", 2),
#   ("Great News", "I am getting married", 1),
#   ("Interesting Game", "It was a fantastic game of tennis", 5),
#   ("Party", "Anyone up for a late-night party today?", 3);
# """

# execute_query(connection, create_posts)

# create_comments = """
# INSERT INTO
#   `comments` (`text`, `user_id`, `post_id`)
# VALUES
#   ('Count me in', 1, 6),
#   ('What sort of help?', 5, 3),
#   ('Congrats buddy', 2, 4),
#   ('I was rooting for Nadal though', 4, 5),
#   ('Help with your thesis?', 2, 3),
#   ('Many congratulations', 5, 4);
# """

# execute_query(connection, create_comments)

# create_likes = """
# INSERT INTO
#   `likes` (`user_id`, `post_id`)
# VALUES
#   (1, 6),
#   (2, 3),
#   (1, 5),
#   (5, 4),
#   (2, 4),
#   (4, 2),
#   (3, 6);
# """

# execute_query(connection, create_likes)

select_users = "SELECT * FROM users"
users = execute_read_query(connection, select_users)

for user in users:
    print(user)

# select_posts = "SELECT * FROM posts"
# posts = execute_read_query(connection, select_posts)
#
# for post in posts:
#     print(post)

update_post_description = """
UPDATE
  posts
SET
  description = "The weather has become pleasant now"
WHERE
  id = 2
"""

execute_query(connection,  update_post_description)

delete_comment = "DELETE FROM comments WHERE id = 5"
execute_query(connection, delete_comment)

