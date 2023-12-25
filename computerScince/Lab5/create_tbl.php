<?php
function execute_query($link, $query){
    $success = mysqli_query($link, $query);
    if ($success) {
        echo "Query executed successfully<br>";
    } else {
        echo "Query not executed<br>";
    }
}

$link = mysqli_connect ("localhost", "admin", "admin");
if ($link) {
    echo "connection to the server was successfully established<br>";
} else {
    echo "No connection to the server<br>";
}

$db = "Lab5DB";
$select = mysqli_select_db($link, $db);
if ($select){
    echo "Database selected<br>";
}else{
    echo "Database wasn't selected<br>";
}

$create_notes_table = "
CREATE TABLE notes
(id INT NOT NULL AUTO_INCREMENT,
PRIMARY KEY (id),
created DATE,
title VARCHAR (20),
article VARCHAR (255))
";
execute_query($link, $create_notes_table);

$create_notes = "
INSERT INTO 
    `notes` (`created`, `title`, `article`)
VALUES
    (27.12.2005, 'I just was born!', 'I am really just borned...'),
    (07.02.2014, 'Welcome!', 'I added a new territory to my house.'),
    (28.04.2020, 'So sad:(', 'Isolation will be longer...'),
    (01.09.1939, 'Im shocked...', 'All of you knows what happened today..,');
";
execute_query($link, $create_notes);

$create_comments_table = "
CREATE TABLE comments
(id INT NOT NULL AUTO_INCREMENT,
PRIMARY KEY (id),
created DATE,
author VARCHAR (20),
comment VARCHAR (256),
art_id SMALLINT)
";
execute_query($link, $create_comments_table);

$create_comments = "
INSERT INTO
    `comments` (`created`, `author`, `comment`, `art_id`)
VALUES
    (27.12.2005, 'Ryan Gosling', 'Congrats!', 1),
    (28.12.2005, 'Dwayne Jonson', 'So happy for you!', 1),
    (29.04.2020, 'Eren Yeager', 'So cringe', 3),
    (30.04.2020, 'Naruto', 'Im gonna do coffee', 3),
    (30.04.2020, 'Tyler Derden', 'Do you remember the first rule of isolation?', 3),
    (24.12.2023, 'Leo Caprio', 'Nice move dude', 4);
";
execute_query($link, $create_comments);
