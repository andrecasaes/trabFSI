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

$cliente = $_POST['sla'];

$sql = "INSERT INTO mandaconsulta (nomeCliente) VALUES ('$cliente');";
if ($conn->query($sql) === TRUE) {
    echo "Mensagem enviada";
} else {
    echo "Error: " . $sql . "<br>" . "$conn->error";
}
$conn->close();
?>
