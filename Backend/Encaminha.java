import org.telegram.telegrambots.api.methods.ForwardMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Encaminha extends TelegramLongPollingBot {

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "NoShow_bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "516441239:AAG9VU7f04JiZ5KVSDGyzVVYxLNeon1ANJo";
	}

	public void encaminha(Update update) {

		String message_text = update.getMessage().getText();// variaveis
		String nome = update.getMessage().getFrom().getFirstName();
		String usuario = update.getMessage().getFrom().getUserName();
		int messageId = update.getMessage().getMessageId();
		long chat_id = update.getMessage().getChatId();
		System.out.println(nome + " " + chat_id + " " + message_text);

		ForwardMessage message = new ForwardMessage()// Create a message object object
				.setFromChatId(chat_id).setChatId("403373459").setMessageId(messageId);// variavel da linha 21
		// .setText("Bom dia!\nGostaria de saber se vc irá amanhã na consulta ");

		try {
			execute(message); // Sending our message object to user
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdateReceived(Update arg0) {
		// TODO Auto-generated method stub

	}

}
