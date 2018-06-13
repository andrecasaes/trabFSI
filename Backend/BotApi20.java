import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.ForwardMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class BotApi20 extends TelegramLongPollingBot {
	static long chat_id;
	static String nome;
	static String sobrenome;	
	static String nomeCom;
	static String message_text;
	static String usuario;
	static int messageId;
	static Message reply;
	static String novoNome;
	static String nomeProf;
	static String nomeCliente, nomeAno,nomeMes,nomeDia,nomeHora,nomeMin;//Variaveis para a agendar nova consulta
	static int z=1;
	static int t=1;
	static String textomsg;
	
	NovoUsuario novo = new NovoUsuario();
	Conecta consult = new Conecta();
	AdicionaProf adicio = new AdicionaProf();
	adicionaIDProf adiID = new adicionaIDProf();
	NovaConsulta novaconsul = new NovaConsulta();
	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) { // We check if the update has a message and the message has text

			// variaveis
			message_text = update.getMessage().getText();
			nome = update.getMessage().getFrom().getFirstName();
			sobrenome = update.getMessage().getFrom().getLastName();
			nomeCom = nome+" "+sobrenome;
			usuario = update.getMessage().getFrom().getUserName();
			messageId = update.getMessage().getMessageId();
			chat_id = update.getMessage().getChatId();
			reply = update.getMessage().getReplyToMessage();
			// variaveis
			
			System.out.println(nome + " " + nomeCom + " " + message_text);
			
			if(reply != null) {
					String textodoreply =reply.getText();
				if(textodoreply.equals("Me fala o seu nome então?")) {	
				novoNome = update.getMessage().getText();
				NovoUsuario.in=2;
				NovoUsuario reply = new NovoUsuario();
				reply.NovoUsuario();		
				}
			}else { //possibilita que quando a pessoa manda o novo nome ele nao envie a mensagem de correção (Manda um oi vai)
			
			switch (message_text) {
			
			case "/Start": novo.NovoUsuario(); novo.casonovo=1; break;
			case "/start": novo.NovoUsuario(); novo.casonovo=1; break;
			case "/consulta": novaconsul.pegaClientes(); break;
			case "/Consulta": novaconsul.pegaClientes(); break;
			case "Oi":t=1; mensagemPadrao(); break;
			case "oi":t=1; mensagemPadrao(); break;
			case "ping":t=2; mensagemPadrao(); break;
			case "Ping":t=2; mensagemPadrao(); break;
			case "pong":t=3; mensagemPadrao(); break;
			case "Pong":t=3; mensagemPadrao(); break;
				
			default:
				if (z==1) {
				SendMessage message = new SendMessage() // Create a message object object
				.setChatId(chat_id)
				.setText("Sou de SI, não sei conversar =(\nMas estou me esforçando para aumentar minhas relações interpessoais heheh\nFala oi pra mim?");
				
				try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
				break;
				} else if(z==2) {//Adiciona profissional em usuariotelegram
					nomeProf = update.getMessage().getText();
					consult.insereProfissional();
					z=1;
				}else if(z==3) {//Insere id em profissional);
					nomeProf = update.getMessage().getText();
					consult.insereIDProfissional();
					z=1;
				}else if(z==4) {//Insere nome em consulta
					z=1;
					nomeCliente = update.getMessage().getText();
					novaconsul.pegaAno();
				}else if(z==5) {//Insere dia em consulta
					z=1;
					nomeDia = update.getMessage().getText();
					novaconsul.pegaHora();
				}else if(z==6) {//Insere mes em consulta
					z=1;
					nomeMes = update.getMessage().getText();
					novaconsul.pegaDia();
				}else if(z==7) {//Insere ano em consulta
					z=1;
					nomeAno = update.getMessage().getText();
					novaconsul.pegaMes();
				}else if(z==8) {//Insere hora da consulta
					z=1;
					nomeHora = update.getMessage().getText();
					novaconsul.pegaMin();
				}else if(z==9) {//Insere minutos da consulta
					z=1;
					nomeMin = update.getMessage().getText();
					novaconsul.insereConsulta();
				}
			}
		}

		} else if (update.hasCallbackQuery()) {
			FuncionaBotao fun = new FuncionaBotao();
			fun.onUpdateReceived(update);
			fun.FuncionaBotao();
		}
	}
	
	public void mensagemPadrao() {
		if (t==1) {
			textomsg = "Oie\nViu? Agora eu consigo falar responder o oi das pessoas.... Estou evoluindo...\n"
					+ "Eu sei tambem jogar um jogo super legal....\nDigita ping ai =)";
		}else if(t==2) {
			textomsg = "Pong.\n"
					+ "Agora você.";
		}else if(t==3) {
			textomsg = "Errou feio, errou rude!\n";
		}
		SendMessage message = new SendMessage() // Create a message object object
				.setChatId(chat_id)
				.setText(textomsg);
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
				
	}

		
	@Override
	public String getBotUsername() {return "NoShow_bot";}

	@Override
	public String getBotToken() {return "516441239:AAG9VU7f04JiZ5KVSDGyzVVYxLNeon1ANJo";}

}