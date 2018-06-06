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
$cliente = $_POST['cliente'];
$data = $_POST['data'];
$horas = $_POST['horas'];
$minutos = $_POST['minutos'];
$id = "SELECT  `ID` FROM `usuariostelegram` WHERE PrimeiroNome = 'lais'";

$sql = "INSERT INTO todasconsulta (nomeCliente, ID, Data, Hora, Minuto)
VALUES ('$cliente', '$id', '$data', '$horas', '$minutos');";
if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . "$conn->error";
}
$conn->close();
?>
