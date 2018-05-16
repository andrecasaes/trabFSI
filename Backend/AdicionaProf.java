import static java.lang.Math.toIntExact;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiValidationException;

public class AdicionaProf extends TelegramLongPollingBot implements ReplyKeyboard {
	
	public void AdicionaProf() {
		int i=Conecta.profissionais.size();
		ForceReplyKeyboard replyMarkup = new ForceReplyKeyboard();
		ReplyKeyboardMarkup teclado = new ReplyKeyboardMarkup();
		List<KeyboardRow> linha = new ArrayList<KeyboardRow>();
		KeyboardRow[] botao = new KeyboardRow[i];		
		String[] array = new String[i];
		
		array=Conecta.profissionais.toArray(array);

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
					.setText("Seleciona pra mim quem vai cuidar de você?")
					.setReplyMarkup(teclado);
			
			BotApi20.z=2;
		
		System.out.println(teclado);
		System.out.println(linha);
		System.out.println(botao);
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onUpdateReceived(Update arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "516441239:AAG9VU7f04JiZ5KVSDGyzVVYxLNeon1ANJo";
	}

	@Override
	public void validate() throws TelegramApiValidationException {
		// TODO Auto-generated method stub
		
	}

}
