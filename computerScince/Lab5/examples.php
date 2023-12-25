<?php echo "<h2>My first php!!!</h2>";
echo "<h3>exercise 1</h3>";
$a = 10;
$b = 20;
echo "a = ", $a, "<br/>", "b = ", $b, "<br/>";

$c = $a + $b;
echo "<h3>exercise 2</h3>";
echo "c = ", $c, "<br/>";

echo "<h3>exercise 3</h3>";
$c *= 3;
echo "c = ", $c, "<br/>";

echo "<h3>exercise 4</h3>";
$c /= $b - $a;
echo "c = ", $c, "<br/>";

echo "<h3>exercises 5-7</h3>";
$p = "Programm";
$b = "works";
$result = $p . " " . $b;
$result .= " nice!";
echo $result, "<br/>";

echo "<h3>exercise 8</h3>";
$q = 5;
$w = 7;
echo "q = ", $q, ", w = ", $w, "<br/>";
$q ^= $w ^= $q ^= $w;
echo "q = ", $q, ", w = ", $w, "<br/>";

echo "<h3>exercise 9</h3>";
for ($i = 23; $i <= 78; $i++){
    echo $i, " ";
}

echo "<h3>exercise 10</h3>";
for ($i = 1; $i <= 10; $i++){
    echo "<li>element</li>";
}


echo "<h3>exercise 11</h3>";
for ($i = 0; $i <= 100; $i++){
    $numbers[$i] = rand();
}
echo "<h4>printing with while:<br/></h4>";
$i = 0;
while ($i < 100){
    echo $numbers[$i], " ";
    $i ++;
}
echo "<h4>printing with foreach:</h4>";
foreach ($numbers as $number){
    echo $number, " ";
}

echo "<h3>exercise 12</h3>";
$day = date("l");
switch($day){
    case "Monday":
        echo "Monday";
        break;
    case "Tuesday":
        echo "Tuesday";
        break;
    case "Wednesday":
        echo "Its Wednesday, my dudes";
        break;
    case "Thursday":
        echo "Thursday";
        break;
    case "Friday":
        echo "Friday";
        break;
    case "Saturday":
        echo "Saturday";
        break;
    case "Sunday":
        echo "Sunday - deadlines are burn!!!";
        break;
}

echo "<h3>exercise 13</h3>";
function getPlus10($var){
    echo $var + 10;
}
$a = 42;
echo "a = ", $a, "<br/>a + 10 = ";
getPlus10(42);
