<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Language" content="pt-br" charset="utf-8">
    <title>Cadastro</title>
    <link rel="stylesheet" type="text/css" href="forms.css">
</head>
<body>
    <div class = "logo"><h1>No Show</h1></div>
    <h2 class = "titulo">Cadastro</h2>
    <form method="post" name="Cadastro" onsubmit="return funcao()" action="banco.php">
        <table>
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
        <input class="button" type="submit" value="Pronto!"><br>
        <input class="button" type="reset" value="Limpar">
    </form>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript">
   
function funcao() {
    var nome = document.forms["Cadastro"]["nome"].value;
    var email = document.forms["Cadastro"]["e-mail"].value;
    var usuario = document.forms["Cadastro"]["usuario"].value;
    var senha = document.forms["Cadastro"]["senha"].value;
    var confirmarSenha = document.forms["Cadastro"]["confirmarSenha"].value;
    var profissao = document.forms["Cadastro"]["profissao"].value;
    var email1 = document.getElementById("email");
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

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
    } else if (profissao == "null") {
            alert("Por favor, selecione uma profissão.");
            return false;
    } else if (profissao == "Outro") {
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