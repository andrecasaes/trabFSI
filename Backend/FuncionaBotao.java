import static java.lang.Math.toIntExact;

import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class FuncionaBotao extends TelegramLongPollingBot {
	String call_data;
	long message_id;
	long chat_id;

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

		if (call_data.equals("Sim")) {
			String answer = "De boas então, te vejo amanhã";
			EditMessageText new_message = new EditMessageText().setChatId(chat_id).setMessageId(toIntExact(message_id))
					.setText(answer);
			try {
				execute(new_message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		} else if (call_data.equals("Nao")) {
			String answer = "Puts, blz vou desmarcar então";
			EditMessageText new_message = new EditMessageText().setChatId(chat_id).setMessageId(toIntExact(message_id))
					.setText(answer);
			try {
				execute(new_message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

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
