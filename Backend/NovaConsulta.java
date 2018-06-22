import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class NovaConsulta extends TelegramLongPollingBot{
	static long chatID;
	static String nomeCliCon;
	static String numMes; //Precisa ser string pq nao aceita int 08 e 09 =(
	Conecta con = new Conecta();
	
	public NovaConsulta() {
	
	}
	
	public void pegaClientes() {
		BotApi20 bot = new BotApi20();
		chatID=bot.chat_id;
		con.procProfConsul();
	}
	
	public void tecladoConsulta() {
		System.out.println("Iniciou metodo para receber nome do cliente da consulta!");
		int i=Conecta.usuarios.size();
		ForceReplyKeyboard replyMarkup = new ForceReplyKeyboard();
		ReplyKeyboardMarkup teclado = new ReplyKeyboardMarkup();
		List<KeyboardRow> linha = new ArrayList<KeyboardRow>();
		KeyboardRow[] botao = new KeyboardRow[i];		
		String[] array = new String[i];
		
		array=Conecta.usuarios.toArray(array);

			int j =0;
			while( i != 0) {
				botao[j]= new KeyboardRow();
				botao[j].add(array[i-1]);
				linha.add(j, botao[j]);		
				j++;
				i--;
			}
			teclado.setKeyboard(linha).setOneTimeKeyboard(true);
			
			SendMessage message = new SendMessage()
					.setChatId(BotApi20.chat_id)
					.setText("Para marcarmos uma consulta preciso que me diga alguns dados!\n"
							+ "Primeiro preciso saber pra quem é a consulta.\n\n"
							+ "Seleciona pra mim o cliente?")
					.setReplyMarkup(teclado);
			
			BotApi20.z=4;
			System.out.println("Teclado de clientes enviado!");
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	
	public void pegaAno() {
		System.out.println("Iniciou metodo para receber o ano da consulta!");
		int i=8;
		ForceReplyKeyboard replyMarkup = new ForceReplyKeyboard();
		ReplyKeyboardMarkup teclado = new ReplyKeyboardMarkup();
		List<KeyboardRow> linha = new ArrayList<KeyboardRow>();
		KeyboardRow[] botao = new KeyboardRow[i];		
		String[] array = {"2025","2024","2023","2022","2021","2020","2019","2018"};

			int j =0;
			while( i != 0) {
				botao[j]= new KeyboardRow();
				botao[j].add(array[i-1]);
				linha.add(j, botao[j]);		
				j++;
				i--;
			}
			teclado.setKeyboard(linha).setOneTimeKeyboard(true);
			
			SendMessage message = new SendMessage()
					.setChatId(BotApi20.chat_id)
					.setText("OK!\n"
							+ "A consulta é para: "+BotApi20.nomeCliente+"\n"
							+ "Agora preciso saber a data da consulta!\n"
							+ "Qual o ano?")
					.setReplyMarkup(teclado);
			
			BotApi20.z=7;
			System.out.println("Teclado de ano enviado!");
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	
	public void pegaMes() {
		System.out.println("Iniciou metodo para receber o mês da consulta!");
		int i=12;
		ForceReplyKeyboard replyMarkup = new ForceReplyKeyboard();
		ReplyKeyboardMarkup teclado = new ReplyKeyboardMarkup();
		List<KeyboardRow> linha = new ArrayList<KeyboardRow>();
		KeyboardRow[] botao = new KeyboardRow[i];		
		String[] array = {"Dezembro","Novembro","Outubro","Setembro","Agosto","Julho","Junho","Maio","Abril","Março","Fevereiro","Janeiro"};

			int j =0;
			while( i != 0) {
				botao[j]= new KeyboardRow();
				botao[j].add(array[i-1]);
				linha.add(j, botao[j]);		
				j++;
				i--;
			}
			teclado.setKeyboard(linha).setOneTimeKeyboard(true);
			
			SendMessage message = new SendMessage()
					.setChatId(BotApi20.chat_id)
					.setText("Anotado!\n"
							+ "A consulta é para: "+BotApi20.nomeCliente+"\n"
							+ "Dia: 00/00/"+BotApi20.nomeAno+"\n"
							+ "Agora o mês!")
					.setReplyMarkup(teclado);
			
			BotApi20.z=6;
			System.out.println("Teclado de mês enviado!");
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	
	public void pegaDia() {
		System.out.println("Iniciou metodo para receber o dia da consulta!");
		//BotApi20 bot = new BotApi20();
		nomeCliCon = BotApi20.nomeCliente;

		int diasMes=0;//Dias no mês
		int colunas =0; //Quantidade de numeros por linha
		int linhas =0;//Numero de linhas
		int p=1, k=1, q=0, z=0; //variaveis auxiliares
		String nu = ""+p;//botoes
		
		switch (BotApi20.nomeMes) {
		case "Janeiro":diasMes=31;linhas=4;colunas=7;numMes="01";break;
		case "Fevereiro":diasMes=28;linhas=4;colunas=7;numMes="02";break;
		case "Março":diasMes=31;linhas=4;colunas=7;numMes="03";break;
		case "Abril":diasMes=30;linhas=5;colunas=6;numMes="04";break;
		case "Maio":diasMes=31;linhas=4;colunas=7;numMes="05";break;
		case "Junho":diasMes=30;linhas=5;colunas=6;numMes="06";break;
		case "Julho":diasMes=31;linhas=4;colunas=7;numMes="07";break;
		case "Agosto":diasMes=31;linhas=4;colunas=7;numMes="08";break;
		case "Setembro":diasMes=30;linhas=5;colunas=6;numMes="09";break;
		case "Outubro":diasMes=31;linhas=4;colunas=7;numMes="10";break;
		case "Novembro":diasMes=30;linhas=5;colunas=6;numMes="11";break;
		case "Dezembro":diasMes=31;linhas=4;colunas=7;numMes="12";break;
		}
		ForceReplyKeyboard replyMarkup = new ForceReplyKeyboard();
		ReplyKeyboardMarkup teclado = new ReplyKeyboardMarkup();
		List<KeyboardRow> linha = new ArrayList<KeyboardRow>();
		KeyboardRow[] botao = new KeyboardRow[diasMes];		
		String[] array = new String[diasMes];
		
		while (linhas>=0) {
			botao[linhas]= new KeyboardRow();
			linhas--;
		}
		if(diasMes == 31) {z=1;}
		while( diasMes != 0) {
			if(p<10) {
				nu = ""+0+p;//Dentro dos botões
			}else{
				nu = ""+p;
			}
			botao[q].add(nu);//Adiciona a escrita nos botoes
				
			if (k>(colunas-1)) {		
				linha.add(q, botao[q]);//adiciona a linha de botoes
				k=0;
				q++;
			}
			k++;diasMes--;p++;
		}
		if (z==1) {//Mes de 31 dias precisa de uma linha a mais com botoes vazios
			botao[4].add(" ");
			botao[4].add(" ");
			botao[4].add(" ");
			botao[4].add(" ");
			linha.add(4, botao[4]);
			z=0;
		}
		
			teclado.setKeyboard(linha).setOneTimeKeyboard(true);
			
			SendMessage message = new SendMessage()
					.setChatId(BotApi20.chat_id)
					.setText("Beleza!\n"
							+ "A consulta é para: "+BotApi20.nomeCliente+"\n"
							+ "Dia: 00/"+numMes+"/"+BotApi20.nomeAno+"\n"
							+ "Dia?...")
					.setReplyMarkup(teclado);
			
			BotApi20.z=5;
			System.out.println("Teclado de dias enviado!");
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	
	public void pegaHora() {
		System.out.println("Iniciou metodo para receber a hora da consulta!");
		nomeCliCon = BotApi20.nomeCliente;

		int numHoras=24;//24 horas
		int colunas=6; //Quantidade de numeros por linha
		int linhas=4;//Numero de linhas
		int p=1, k=1, q=0, z=0; //variaveis auxiliares
		String nu = ""+p;//botoes
		
		ForceReplyKeyboard replyMarkup = new ForceReplyKeyboard();
		ReplyKeyboardMarkup teclado = new ReplyKeyboardMarkup();
		List<KeyboardRow> linha = new ArrayList<KeyboardRow>();
		KeyboardRow[] botao = new KeyboardRow[numHoras];		
		String[] array = new String[numHoras];
		
		while (linhas>=0) {
			botao[linhas]= new KeyboardRow();
			linhas--;
		}
		while( numHoras != 0) {
			nu = ""+p;//Dentro dos botões
			botao[q].add(nu);//Adiciona a escrita nos botoes
				
			if (k>(colunas-1)) {		
				linha.add(q, botao[q]);//adiciona a linha de botoes
				k=0;
				q++;
			}
			k++;numHoras--;p++;
		}		
			teclado.setKeyboard(linha).setOneTimeKeyboard(true);
			
			SendMessage message = new SendMessage()
					.setChatId(BotApi20.chat_id)
					.setText("Quase lá!\n"
							+ "Pra acabar falta só o horario da consulta!\n"
							+ "Por enquanto temos: \n"
							+ "Consulta dia "+BotApi20.nomeDia+"/"+numMes+"/"+BotApi20.nomeAno+" para "+BotApi20.nomeCliente+"\n"
							+ "Qual vai ser o horario?\n"
							+ "Primeiro as horas...")
					.setReplyMarkup(teclado);
			
			BotApi20.z=8;
			System.out.println("Teclado de horas enviado!");
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	public void pegaMin() {
		System.out.println("Iniciou metodo para receber os minutos da consulta!");
		nomeCliCon = BotApi20.nomeCliente;

		int numHoras=12;//24 horas
		int colunas=6; //Quantidade de numeros por linha
		int linhas=2;//Numero de linhas
		int p=0, k=1, q=0, z=0; //variaveis auxiliares
		String nu = ""+p;//botoes
		
		ForceReplyKeyboard replyMarkup = new ForceReplyKeyboard();
		ReplyKeyboardMarkup teclado = new ReplyKeyboardMarkup();
		List<KeyboardRow> linha = new ArrayList<KeyboardRow>();
		KeyboardRow[] botao = new KeyboardRow[numHoras];		
		String[] array = new String[numHoras];
		
		while (linhas>=0) {
			botao[linhas]= new KeyboardRow();
			linhas--;
		}
		while( numHoras != 0) {
			if(p==0||p==5) {
				nu = ""+0+p;//Dentro dos botões
			}else{
				nu = ""+p;
			}
			
			botao[q].add(nu);//Adiciona a escrita nos botoes
				
			if (k>(colunas-1)) {		
				linha.add(q, botao[q]);//adiciona a linha de botoes
				k=0;
				q++;
			}
			k++;numHoras--;p+=5;
		}		
			teclado.setKeyboard(linha).setOneTimeKeyboard(true);
			
			SendMessage message = new SendMessage()
					.setChatId(BotApi20.chat_id)
					.setText("Quase lá!\n"
							+ "Pra acabar falta só o horario da consulta!\n"
							+ "Por enquanto temos: \n"
							+ "Consulta dia "+BotApi20.nomeDia+"/"+numMes+"/"+BotApi20.nomeAno+" às "+BotApi20.nomeHora+":**"+" para "+BotApi20.nomeCliente+"\n"
							+ "Qual vai ser o horario?\n"
							+ "E agora, pra finalizar, os minutos...")
					.setReplyMarkup(teclado);
			
			BotApi20.z=9;
			System.out.println("Teclado de minutos enviado!");
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	
	public void confConsulta() {
		System.out.println("Aeeeeeeeeeeeeee");
		System.out.println("Consulta do "+BotApi20.nomeCliente+"\nNo dia "+BotApi20.nomeDia+"/"+BotApi20.nomeMes+"/"+BotApi20.nomeAno);
		
		SendMessage message = new SendMessage()
				.setChatId(BotApi20.chat_id)
				.setText("Só confirmando os dados!\n"
						+ "A consulta ficou agendada para dia "+
						BotApi20.nomeDia+"/"+numMes+"/"+BotApi20.nomeAno+" às "+BotApi20.nomeHora+":"+BotApi20.nomeMin+" para "+BotApi20.nomeCliente);
		
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline2 = new ArrayList<>();

		rowInline.add(new InlineKeyboardButton().setText("É isso mesmo pode salvar").setCallbackData("ConsultaOK"));
		rowInline2.add(new InlineKeyboardButton().setText("Vish, errei. Posso recomeçar?").setCallbackData("ConsultaNOK"));
		// Set the keyboard to the markup
		rowsInline.add(rowInline);
		rowsInline.add(rowInline2);
		// Add it to the message
		markupInline.setKeyboard(rowsInline);
		message.setReplyMarkup(markupInline);
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	public void insereConsulta() {
		String resultado;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String selectSql;
		int insere = 0;
		con.procuraIDcomUsu();
		
		String data = BotApi20.nomeAno+"-"+numMes+"-"+BotApi20.nomeDia;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noshow?useSSL=false", "root","");
			selectSql = "INSERT INTO `todasconsulta` (`ID`, `Data`, `Hora`, `Minuto`, `Profissional`, `nomeCliente`) VALUES ('"+Conecta.clienteID+"','"+data+"','"+BotApi20.nomeHora+"','"+BotApi20.nomeMin+"','"+Conecta.nomeProf+"','"+BotApi20.nomeCliente+"')";
	        	statement = connection.createStatement();
	        	insere = statement.executeUpdate(selectSql); 
		
		} catch (Exception e) {e.printStackTrace();} finally {
		if (resultSet != null)try {resultSet.close();} catch (Exception e) {}
		if (statement != null)try {statement.close();} catch (Exception e) {}
		if (connection != null)try {connection.close();} catch (Exception e) {}
		}
	}
	public void naoProfissional() {
		SendMessage message = new SendMessage()
				.setChatId(BotApi20.chat_id)
				.enableMarkdown(true)
				.setText("Esse comando é exclusivo para os profissionais =(");
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	@Override
	public String getBotUsername() {return null;}
	@Override
	public void onUpdateReceived(Update arg0) {}
	@Override
	public String getBotToken() {return "516441239:AAG9VU7f04JiZ5KVSDGyzVVYxLNeon1ANJo";}

}
