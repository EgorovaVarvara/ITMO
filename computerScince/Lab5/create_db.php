<?php
$link = mysqli_connect ("localhost", "root", "");
if ($link) {
    echo "connection to the server was successfully established<br>";
} else {
    echo "No connection to the server<br>";
}

$db = "Lab5DB";
$query = "CREATE DATABASE $db";
$create_db = mysqli_query($link, $query);
	if ($create_db) {
        echo "Database $db was successfully created<br>";
    } else {
        echo "Database wasn't created<br>";
    }
