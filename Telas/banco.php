<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Language" content="pt-br" charset="utf-8">
    <title>RecalculandoRota</title>
    <link rel="stylesheet" type="text/css" href="forms.css">
</head>
<body>
    <div class = "logo"><h1>No Show</h1></div>
    <h2 class = "titulo"><?php
$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "NoShow";

$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . $conn->connect_error);
} 

$nome = $_POST['nome'];
$email = $_POST['e-mail'];
$usuario = $_POST['usuario'];
$senha = $_POST['senha'];
$profissao = $_POST['profissao'];

$sql = "INSERT INTO profissional (NomeProf, Email, Usuario, Senha, Profissao)
VALUES ('$nome', '$email', '$usuario', '$senha', '$profissao');";

if ($conn->query($sql) === TRUE) {
    echo "Cadastro enviado com sucesso!";
} else {
    echo "Error: " . $sql . "<br>" . "$conn->error";
}

$conn->close();
?></h2>
    <h3>Agora, procure pelo bot "NoShow" no Telegram e mande o código:</h3>
    <h1>noshow</h1>
    <h4>Obs.: Não efetue o cadastro como cliente.</h4>
    <button class="button"><a href="index.html" class="button">Voltar para o início</a></button>
</body>
</html>