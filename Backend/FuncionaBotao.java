import static java.lang.Math.toIntExact;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class FuncionaBotao extends TelegramLongPollingBot {
	String call_data;
	long message_id;
	static long chat_id;
	EditMessageText new_message;
	String answer;
	static int confConsul=0;
	
	NovoUsuario inicia = new NovoUsuario();
	Conecta consult = new Conecta();
	envioAutomatico en = new envioAutomatico();

	@Override
	public void onUpdateReceived(Update update) {
		call_data = update.getCallbackQuery().getData();
		message_id = update.getCallbackQuery().getMessage().getMessageId();
		chat_id = update.getCallbackQuery().getMessage().getChatId();
	}

	public void FuncionaBotao() {
		System.out.println(call_data);
		System.out.println(message_id);
		System.out.println(chat_id);
		
		switch (call_data) {
		case "Sim":
			answer = "De boas então, vou avisar o profissional!\n"
					+ "Até a proxima!";
			new_message = new EditMessageText().setChatId(chat_id).setMessageId(toIntExact(message_id)).setText(answer);
			confConsul = 1;
			en.envioRespProf();
			try {execute(new_message);} catch (TelegramApiException e) {e.printStackTrace();}
		break;
			
		case "Nao":
			answer = "Puts =/\n"
					+ "vou avisar o profissional que você nao vai então.\n"
					+ "Até a próxima!";
			new_message = new EditMessageText().setChatId(chat_id).setMessageId(toIntExact(message_id)).setText(answer);
			confConsul = 2;
			en.envioRespProf();
			try {execute(new_message);} catch (TelegramApiException e) {e.printStackTrace();}
		break;
			
		case "Nome ok":
			answer = "Que otimo! De boas então, vou fazer o seu cadastro!";
			new_message = new EditMessageText().setChatId(chat_id).setMessageId(toIntExact(message_id)).setText(answer);
			NovoUsuario.casonovo=1;
			consult.Insere();
			inicia.linkaProf();
			try {execute(new_message);} catch (TelegramApiException e) {e.printStackTrace();}
		break;
			
		case "Nome errado":
			answer = "Puts, blz vamos arrumar isso.";
			new_message = new EditMessageText().setChatId(chat_id).setMessageId(toIntExact(message_id)).setText(answer);		
			NovoUsuario.in=1;
			NovoUsuario.casonovo=3;
			inicia.NovoUsuario();
			try {execute(new_message);} catch (TelegramApiException e) {e.printStackTrace();}
		break;
		
		case "profissional":
			answer = "Olá!\n" + 
					"Estou muito feliz que esteja usando o nosso programa para deixar a sua rotina mais facil!\n"
					+ "Agora só falta mais um passo para completar seu cadastro!";
			new_message = new EditMessageText().setChatId(chat_id).setMessageId(toIntExact(message_id)).setText(answer);		
			consult.procuraProfSemID();
			try {execute(new_message);} catch (TelegramApiException e) {e.printStackTrace();}
		break;
		default:
			break;
		}
	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "516441239:AAG9VU7f04JiZ5KVSDGyzVVYxLNeon1ANJo";
	}

}
