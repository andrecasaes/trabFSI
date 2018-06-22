<!DOCTYPE html>
<html>
<head>
	<!--Esta é a página de instrução após o cadastro.-->
    <meta http-equiv="Content-Language" content="pt-br" charset="utf-8">
    <title>RecalculandoRota</title>
    <link rel="stylesheet" type="text/css" href="css.css">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Arvo|BioRhyme+Expanded|Cinzel|Cormorant+Unicase|Dosis|Indie+Flower|Josefin+Sans|Jua|Open+Sans|Questrial|Quicksand|Raleway|Righteous|Roboto|Roboto+Mono|Roboto+Slab|Song+Myung" rel="stylesheet">
</head>
<body background="design2.jpg"><!--Definindo a imagem como background.-->
    <div class = "logo"><h1>No Show</h1></div>
    <div class="divTransparente"><!--Adicionando a div transparente.-->
    <h2 class = "titulo"><?php
//criando conexão
$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "NoShow";

$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . $conn->connect_error);
} 

//pegando dados do formulário da página de cadastro
$nome = $_POST['nome'];
$email = $_POST['e-mail'];
$usuario = $_POST['usuario'];
$senha = $_POST['senha'];
$profissao = $_POST['profissao'];

//query para o banco de dados
$sql = "INSERT INTO profissional (NomeProf, Email, Usuario, Senha, Profissao)
VALUES ('$nome', '$email', '$usuario', '$senha', '$profissao');";

//enviando para o banco de dados
if ($conn->query($sql) === TRUE) {
    echo "Cadastro enviado com sucesso!";
} else {
    echo "Error: " . $sql . "<br>" . "$conn->error";
}

$conn->close();
?></h2>
    <h3 class="texto">Agora, procure pelo nosso Bot no Telegram:</h3><!--Instruções ao usuário.-->
    <h1 class="texto">NoShow</h1>
    <h4 class="texto">Clique em "Sou um profissional!" e pronto :)</h4>
    <button class="button"><a href="index.html" class="button">Voltar para o início</a></button>
</div>
</body>
</html>