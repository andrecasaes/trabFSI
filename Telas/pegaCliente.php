<?php
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

$cliente = $_POST['cliente'];
$data = $_POST['data'];
$horas = $_POST['horas'];
$minutos = $_POST['minutos'];

$primeiroNome = "SELECT  `PrimeiroNome` FROM `usuariostelegram` WHERE Profissional = '$logado';";
$id = "SELECT  `ID` FROM `usuariostelegram` WHERE Profissional = '$logado';";
if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . "$conn->error";
}
$conn->close();
?>