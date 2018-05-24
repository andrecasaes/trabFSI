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

$nome = $_POST['nome'];
$email = $_POST['e-mail'];
$usuario = $_POST['usuario'];
$senha = $_POST['senha'];
$confirmarSenha = $_POST['confirmarSenha'];
$Profissao = $_POST['Profissao'];

$sql = "INSERT INTO profissional (NomeProf, Email, Usuario, Senha, SenhaConf, Profissao)
VALUES ('$nome', '$email', '$usuario', '$senha', '$confirmarSenha', '$Profissao');";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . "$conn->error";
}

$conn->close();
?>