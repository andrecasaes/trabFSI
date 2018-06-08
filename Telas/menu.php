<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Language" content="pt-br" charset="utf-8">
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" href="forms.css">
    <style type="text/css">
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 80%; /* Could be more or less, depending on screen size */
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
<script type="text/javascript" src="https://gc.kis.v2.scr.kaspersky-labs.com/B20FFB16-72A4-5B42-8BCF-B5289921543D/main.js" charset="UTF-8"></script></head>
<body>
    <div class = "logo"><h1>No Show</h1></div>
    <h2 class = "titulo">Menu</h2>
    <button id="novaConsultaBotao" class="button">Nova Consulta</button>
    <div id="novaConsultaModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>

            <form name="novaConsulta" method="post" action="" id="ajax_form">
            <table>
                <tr>
                    <td>Cliente:</td>
                    <td><input type="text" name="Cliente"></td>
                </tr>
                <tr>
                    <td>Data:</td>
                    <td><input type="date" name="data"></td>
                </tr>
                <tr>
                    <td>Horário:</td>
                    <td><input type="text" name="horas" size="2"> horas
                    <input type="text" name="minutos" size="2"> min</td>
                </tr>
            </table>
            <button class="button" type="reset">Limpar<br></button>
        <input type="submit" id="salvar" value="Salvar" class="button">
        </form>
 
        </div>
    </div>
    <div id="consultas"></div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript">
// Get the modal
var novaConsultaModal = document.getElementById("novaConsultaModal");
// Get the button that opens the modal
var novaConsultaBotao = document.getElementById("novaConsultaBotao");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
// When the user clicks on the button, open the modal 
novaConsultaBotao.onclick = function() {
    novaConsultaModal.style.display = "block";
}
// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    novaConsultaModal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == novaConsultaModal) {
        novaConsultaModal.style.display = "none";
    }
}

//aaahhh deu errado e eh isso mano
//var quantosClientes = $.post("quantosClientes.php", {i: "null"}, function(num){
//	var clienteDoProf = $.post("pegaCliente.php", {index: num}, function(nome){
//		var option = document.createElement("option");
//		option.text = nome;
//		option.value = nome;
//		var select = document.getElementById("select");
//		select.appendChild(option);
//	});
//});

jQuery(document).ready(function(){
	jQuery('#ajax_form').submit(function(){

		var cliente = document.forms["novaConsulta"]["Cliente"].value;
    	var data = document.forms["novaConsulta"]["data"].value;
    	var horas = document.forms["novaConsulta"]["horas"].value;
    	var minutos = document.forms["novaConsulta"]["minutos"].value;
        
    	if (cliente == "") {
        	alert("Por favor, digite o nome do cliente.");
        	return false;
    	} else if (data == "") {
        	alert("Por favor, digite a data.");
        	return false;
    	} else if (horas == "") {
        	alert("Por favor, digite a hora de início da consulta.");
        	return false;
    	} else if (minutos == "") {
        	alert("Por favor, digite os minutos.");
        	return false;
    	} else {
        
        	var caixa = document.createElement("div");
        	caixa.style.border = "2px solid #05365b";

        	var info = document.createElement("form");
        	info.id = "form2";
        	info.method = "post";
        	var sla = document.createElement("input");
        	sla.type = "checkbox";
        	sla.value = cliente;
        	sla.name = "sla";
        	info.appendChild(sla);
        	info.appendChild(document.createTextNode(cliente));
        	info.appendChild(document.createElement("br"));
        	info.appendChild(document.createTextNode("Data: " + data));
        	info.appendChild(document.createElement("br"));
        	info.appendChild(document.createTextNode(horas + ":" + minutos));

        	var avisar = document.createElement("input");
        	avisar.type = "submit";
        	avisar.value = "Avisar!";
        	avisar.className = "button";
        	avisar.form = "info";

        	var cancelar = document.createElement("input");
        	cancelar.type = "button";
        	cancelar.value = "Cancelar!";
        	cancelar.className = "button";
        
	        caixa.appendChild(info);
	        caixa.appendChild(avisar);
	        caixa.appendChild(cancelar);
	        document.body.appendChild(caixa);
	        novaConsultaModal.style.display = "none";

	
				jQuery('#form2').submit(function(){
	    			var dados = jQuery(this).serialize();
	    				jQuery.ajax({
	    					type: "POST",
	    					url: "avisar.php",
	    					data: dados,
	    					success: function(data) {
	        					alert(data);
	    					}
	    				});
	    				return false;
	    		});         
			
        
	    	var dados = jQuery(this).serialize();
	    	jQuery.ajax({
	    		type: "POST",
	    		url: "bancoConsulta.php",
	    		data: dados,
	    		success: function(data) {
	    		}
	    	}); 
    }
    	return false;        
    });
});

</script>

</body>
</html>
