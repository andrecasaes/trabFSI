/* Desativado
	Desativado
	Desativado
	Desativado
	Desativado

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
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
		ReplyKeyboardMarkup teclado = new ReplyKeyboardMarkup();
		List<KeyboardRow> linha = new ArrayList<KeyboardRow>();
		KeyboardRow botao = new KeyboardRow();		
		
		//botao.clear();
		String[] array = new String[i];
		array=Conecta.profissionais.toArray(array);
		
		SendMessage message = new SendMessage()
				.setChatId(BotApi20.chat_id)
				.setText("Caraio");
			int j =0;
			///while (i != 0) {
			//botao.add("teste");
			//botao.add("testi");
			while( i != 0) {
				System.out.println(array[i-1]);
				System.out.println("i="+i);
				System.out.println("boa");	
					//linha.add(j, botao);
					//botao.add("teste");
					//botao.set(1, "");//texto diferente
					botao.add(j, array[i-1]);
					linha.add(j,botao);//add o botao na linha
					//botao.remove("testi");
					//teclado.setKeyboard(linha).setOneTimeKeyboard(true);			
					System.out.println(botao);
					System.out.println("oe");
				j++;
				i--;
			}
			message.setReplyMarkup(teclado);
			teclado.setKeyboard(linha);
			
			
		
		
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



*/
