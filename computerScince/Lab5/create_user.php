<?php
$link = mysqli_connect ("localhost", "root", "");
if ($link) {
    echo "Connection to the server was successfully established<br>";
} else {
    echo "No connection to the server<br>";
}

$query = "GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost' IDENTIFIED BY 'admin' WITH GRANT OPTION";
$create_user = mysqli_query($link, $query);
if ($create_user) {
    echo "User created successfully<br>";
} else {
    echo "User wasn't created<br>";
}