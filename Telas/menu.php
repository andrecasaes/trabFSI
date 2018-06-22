<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Language" content="pt-br" charset="utf-8">
    <!--Esta é a página do menu.-->
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="css.css"><!--Importando nossa stylesheet.-->
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Arvo|BioRhyme+Expanded|Cinzel|Cormorant+Unicase|Dosis|Indie+Flower|Josefin+Sans|Jua|Open+Sans|Questrial|Quicksand|Raleway|Righteous|Roboto|Roboto+Mono|Roboto+Slab|Song+Myung" rel="stylesheet"><!--Importando algumas fontes interessantes do google fonts.-->
    <?php  
		session_start(); //iniciando a sessão
		if((!isset ($_SESSION['usuario']) == true) and (!isset ($_SESSION['senha']) == true)) {
    		unset($_SESSION['usuario']);
    		unset($_SESSION['senha']);
    		header('location:index.html');
    	}
 		$logado = $_SESSION['usuario']; //definindo "logado" como o nome de usuário
	?>
</head>
<body background="design2.jpg"> <!--Colocando a imagem de fundo no background do body inteiro.-->
    <div class = "logo"><h1>No Show</h1></div><!--Logo da página.-->
    <div class="divTransparente"><!--Colocando a div para criar o fundo branco transparente.-->
    <h2 class = "titulo">Menu</h2><!--Título da página.-->
	<h4 class="texto"><?php echo "Bem-vindo/a, " . $logado; ?></h4><!--Saudação personalizada com o nome de usuário.-->
	<h3 class="texto">Estas são suas consultas:</h3><!--Título da lista de consultas.-->
	<p class="texto"><?php
		//criando conexão
		$servername = "127.0.0.1";
		$username = "root";
		$password = "";
		$dbname = "NoShow";
		$conn = new mysqli($servername, $username, $password, $dbname);
		if (!$conn) {
    		die("Connection failed: " . $conn->connect_error);
		} 

		//buscando o ID do cliente
		$id = "SELECT  `ID` FROM `usuariostelegram` WHERE Profissional = '$logado'";
		if ($conn->query($id) === TRUE) {
    		echo "tEste";
		} else {
    		echo "Nenhuma consulta no momento :/";
		}
		$conn->close();
	?></p>
</div>
</body>
</html>