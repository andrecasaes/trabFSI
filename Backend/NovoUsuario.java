import static java.lang.Math.toIntExact;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class NovoUsuario extends TelegramLongPollingBot {
	static String message_text;
	static String nome;
	static String usuario;
	static String prof;
	String answer;
	EditMessageText new_message;
	static long chat_id;
	static int casonovo = 1;
	static int in = 1;
	static String novoNome = "z293"; // Garante la no conecta que o nome certo vai ser inserido no sql 
	
	Conecta consult = new Conecta();
	AdicionaProf adicio = new AdicionaProf();

	@Override
	public String getBotUsername() {return null;}

	@Override
	public void onUpdateReceived(Update update) {}

	@Override
	public String getBotToken() {return "516441239:AAG9VU7f04JiZ5KVSDGyzVVYxLNeon1ANJo";}
	
	public void NovoUsuario() {
		chat_id= BotApi20.chat_id;
		nome= BotApi20.nomeCom;
		
		switch (casonovo) {
		case 1: //Procura se já existe
			Conecta consulta = new Conecta();	
			consulta.ProcuraID();
			consulta.Consulta();
			
			String result =  Conecta.resultado;
			
			if (result == null) {
				SendMessage message = new SendMessage()
						.setChatId(chat_id)
						.enableMarkdown(true)
						.setText("Olá! Eu sou o bot responsável pelo programa No Show!\n\n"
								+ "Estou vendo que você é novo por aqui!\n"
								+ "Então, sem enrolação, vamos começar?\n"
								+ "Seu nome aqui no Telegram é:\n\n*" 
								+ BotApi20.nomeCom 
								+ "*\n\n"
								+ "Ele esta certinho? Posso usar ele para te cadastrar?");
				
				
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
				List<InlineKeyboardButton> rowInline = new ArrayList<>();

				rowInline.add(new InlineKeyboardButton().setText("É isso mesmo! Pode usar =)").setCallbackData("Nome ok"));
				rowInline.add(new InlineKeyboardButton().setText("Nop, ta errado").setCallbackData("Nome errado"));
				// Set the keyboard to the markup
				rowsInline.add(rowInline);
				// Add it to the message
				markupInline.setKeyboard(rowsInline);
				message.setReplyMarkup(markupInline);
				
				try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}			
				
			}else {
				System.out.println("Já cadastrado");
				
				SendMessage message = new SendMessage()
						.setChatId(chat_id)
						.setText(result+"\nPercebi que vc já está no meu sistema!\nNão precisa mais mandar \"/Start\" não!");	
				
				try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
			}
		break;

		/*case 2: //Sim
				
			consult.Insere();
			consult.Consulta();
			System.out.println("Yep");
			linkaProf();
			casonovo= 1;//Receta para o proximo usuario
			break;*/
		case 3: //Não
			System.out.println("Nop");
			if (in == 1) {
			ForceReplyKeyboard replyMarkup = new ForceReplyKeyboard();
			SendMessage message = new SendMessage()
					.setChatId(chat_id)
					.setText("Me fala o seu nome então?")
					.setReplyMarkup(replyMarkup);
			
			try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
			}else if (in == 2){
				in=1;
			novoNome= BotApi20.novoNome;
			SendMessage message = new SendMessage()
					.setChatId(chat_id)
					.enableMarkdown(true)
					.setText("*"+novoNome + "*\nEsse foi o nome que vc digitou!\nGostaria de usar esse nome para se cadastrar?");
			
			InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
			List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
			List<InlineKeyboardButton> rowInline = new ArrayList<>();
			List<InlineKeyboardButton> rowInline2 = new ArrayList<>();

			rowInline.add(new InlineKeyboardButton().setText("Sim! Quero usar assim mesmo").setCallbackData("Nome ok"));
			rowInline2.add(new InlineKeyboardButton().setText("Puts, digitei errado! Posso arrumar?").setCallbackData("Nome errado"));
			// Set the keyboard to the markup
			rowsInline.add(rowInline);
			rowsInline.add(rowInline2);
			// Add it to the message
			markupInline.setKeyboard(rowsInline);
			message.setReplyMarkup(markupInline);
			
			try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
			casonovo= 1;
			}
		}		
	}
	public void linkaProf() {
			consult.procuraProf();
	}

}
