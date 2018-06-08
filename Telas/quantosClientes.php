<?php
extract($_POST);
$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "NoShow";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . $conn->connect_error);
} 

session_start();
if((!isset ($_SESSION['usuario']) == true) and (!isset ($_SESSION['senha']) == true)){
    unset($_SESSION['usuario']);
    unset($_SESSION['senha']);
}
$logado = $_SESSION['usuario'];

$a = "SELECT  `PrimeiroNome` FROM `usuariostelegram` WHERE Profissional = '$logado';";
$primeiroNome = explode(" ", $a);

if ($conn->query($a) === TRUE) {
    $i = count($primeiroNome);
	echo $i;
} else {
    echo "Error: " . $a . "$conn->error";
}

//$num = pg_num_rows($primeiroNome);
//$array = array();
//$i = $num;

//while($num > 0) {
//	$num = $num - 1;
//	$i = $i - 1;
//	$array[$i] = $primeiroNome;
//}

$conn->close();
?>