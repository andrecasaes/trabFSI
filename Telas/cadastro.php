<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Language" content="pt-br" charset="utf-8">
    <!--Esta é a página de cadastro.-->
    <title>Cadastro</title>
    <link rel="stylesheet" type="text/css" href="css.css">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC|Arvo|BioRhyme+Expanded|Cinzel|Cormorant+Unicase|Dosis|Indie+Flower|Josefin+Sans|Jua|Open+Sans|Questrial|Quicksand|Raleway|Righteous|Roboto|Roboto+Mono|Roboto+Slab|Song+Myung" rel="stylesheet"><!--Importando fontes do google fonts.-->
</head>
<body background="design2.jpg"><!--definindo a imagem como background.-->
    <div class = "logo"><h1>No Show</h1></div>
    <div class="divTransparente"><!--Inserindo div transparente.-->
    <h2 class = "titulo">Cadastro</h2>
    <form method="post" name="Cadastro" onsubmit="return funcao()" action="banco.php">
        <!--O form retorna a função que valida os campos ao clicar em submit, e vai para a página banco.php.-->
        <table>
            <!--Pegando os dados do usuário.-->
            <tr>
                <td>Nome completo:</td>
                <td><input size="30" type="text" name="nome" placeholder="Como as pessoas te chamam?"></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><input size="30" id="email" type="e-mail" name="e-mail" placeholder="Onde a gente pode te encontrar?"></td>
            </tr>
            <tr>
                <td>Usuário:</td>
                <td><input size="30" type="text" name="usuario" placeholder="Seja criativo/a!"></td>
            </tr>
            <tr>
                <td>Senha:</td>
                <td><input size="30" type="password" name="senha" placeholder="Shh...!"></td>
            </tr>
            <tr>
                <td>Confirmar senha:</td>
                <td><input size="30" type="password" name="confirmarSenha" placeholder="De novo!"></td>
            </tr>
            <tr>
                <td>Profissão:</td>
                <td style="text-align: left;"><select name="profissao">
                        <option value="null">--------</option>
                        <option value="Cabeleireiro/a">Cabeleireiro</option>
                        <option value="Dentista">Dentista</option>
                        <option value="Manicure">Manicure</option>
                        <option value="Psicologo/a">Psicologo</option>
                        <option value="Médico/a">Médico/a</option>
                        <option value="Massagista">Massagista</option>
                        <option value="Acupunturista">Acupunturista</option>
                        <option value="Outro" id="outro">Outro (digite):</option>
                        <tr><td></td><td style="text-align: left;" id="input"></td></tr>
                </select></td>
            </tr>
        </table>
        <!--Botões submit e reset.-->
        <br><input class="button" class="espaco" type="submit" value="Pronto!"><br><br>
        <input class="button" type="reset" value="Limpar">
    </form>
</div>
<script type="text/javascript">
//função que valida os campos do form. Toda vez que retorna false, impede que o form seja submetido.
function funcao() {
    //vars pegam os dados do html
    var nome = document.forms["Cadastro"]["nome"].value;
    var email = document.forms["Cadastro"]["e-mail"].value;
    var usuario = document.forms["Cadastro"]["usuario"].value;
    var senha = document.forms["Cadastro"]["senha"].value;
    var confirmarSenha = document.forms["Cadastro"]["confirmarSenha"].value;
    var profissao = document.forms["Cadastro"]["profissao"].value;
    var email1 = document.getElementById("email");
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; //filtro para a validação do email

    //checando se os campos estão vazios
    if (nome == "") {
        alert("Por favor, digite seu nome.");
            return false;
    } else if (email == "") {
            alert("Por favor, digite um e-mail.");
            return false;
    } else if (!filter.test(email1.value)) {
            alert("Por favor, digite um e-mail válido.");
            email1.focus;
            return false;
    } else if (usuario == "") {
            alert("Por favor, informe um nome de usuário.");
            return false;
    } else if (senha == "") {
            alert("Por favor, digite sua senha.");
            return false;
    } else if (confirmarSenha != senha) {
            alert("Ops! As senhas não batem. Confira os campos Senha e Confirmar Senha.");
            return false;
    } else if (profissao == "null") { //o valor null será da option "------"
            alert("Por favor, selecione uma profissão.");
            return false;
    } else if (profissao == "Outro") {
        //cria um novo campo para que o usuário digite uma nova profissão
            var campo = document.createElement("input");
            campo.type = "text";
            document.getElementById("input").appendChild(campo);
            var outro = document.getElementById("outro");
            outro.value = campo.value;
            return false;
    }
}
</script>
</body>
</html>