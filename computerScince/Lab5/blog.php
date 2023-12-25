<?php
require_once ("lab5_db.php");
$link = mysqli_connect ("localhost", "admin", "admin");
$db = "Lab5DB";

$select = mysqli_select_db($link, $db);

$selecting_all_from_notes = "SELECT * FROM notes";

$select_note = mysqli_query($link, $selecting_all_from_notes);
while ($note = mysqli_fetch_array($select_note)) {
    echo "<section>";
    echo $note ['created'], "<br>";
    ?>
    <a href="comments.php?note=<?php echo $note['id']; ?>">
        <?php echo "<h4>",$note ['title'], "</h4>";?></a>

    <?php
    echo $note ['article'], "<br>";
    echo "</section>", "<br><br><br>";}
