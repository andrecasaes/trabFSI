import org.telegram.telegrambots.api.methods.ForwardMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class BotApi20 extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) { // We check if the update has a message and the message has text

			// variaveis
			String message_text = update.getMessage().getText();
			String nome = update.getMessage().getFrom().getFirstName();
			String usuario = update.getMessage().getFrom().getUserName();
			System.out.println(nome + " " + usuario + " " + message_text);
			int messageId = update.getMessage().getMessageId();
			long chat_id = update.getMessage().getChatId();
			// variaveis

			if (update.getMessage().getText().equals("Oi") || update.getMessage().getText().equals("oi")) { // Se tiver escrito igual ele manda msg
				
				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("Bom dia!\nGostaria de saber se vc irá amanhã na consulta ");

				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
				List<InlineKeyboardButton> rowInline = new ArrayList<>();

				rowInline.add(new InlineKeyboardButton().setText("Vou sim").setCallbackData("Sim"));
				rowInline.add(new InlineKeyboardButton().setText("Vai te catar, vou não").setCallbackData("Nao"));
				// Set the keyboard to the markup
				rowsInline.add(rowInline);
				// Add it to the message
				markupInline.setKeyboard(rowsInline);
				message.setReplyMarkup(markupInline);

				try {
					execute(message); // Sending our message object to user
					// execute(encaminha);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}

			} else if (!update.getMessage().getText().equals("Oi")) {

				long chat_id1 = update.getMessage().getChatId(); // variaveis

				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id1)// variavel da linha 21
						.setText("Manda um \"Oi\" ai vai ");

				try {
					execute(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}

		} else if (update.hasCallbackQuery()) {
			FuncionaBotao fun = new FuncionaBotao();
			fun.onUpdateReceived(update);
			fun.FuncionaBotao();
		}
	}

	@Override
	public String getBotUsername() {
		// Return bot username
		// If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
		return "NoShow_bot";
	}

	@Override
	public String getBotToken() {
		// Return bot token from BotFather
		return "516441239:AAG9VU7f04JiZ5KVSDGyzVVYxLNeon1ANJo";
	}

}