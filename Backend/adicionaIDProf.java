import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class adicionaIDProf extends TelegramLongPollingBot{
	String textinho;
	public void adicionaIDProf() {
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
					.setText("Quem é você?")
					.setReplyMarkup(teclado);
			
			BotApi20.z=3;
		
		System.out.println(teclado);
		System.out.println(linha);
		System.out.println(botao);
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	
	public void mensagemFinalizado() {
		BotApi20 bot = new BotApi20();
		SendMessage message = new SendMessage()
				.setChatId(bot.chat_id)
				.enableMarkdown(true)
				.setText("Prontinho!\n"
						+ "Seu cadastro já esta totalmente funcional!\n"
						+ "Agora seus clientes já conseguem te achar para fazer o cadastro deles!\n"
						+ "Para marcar uma consulta basta digitar o comando /Consulta");
		try {execute(message);} catch (TelegramApiException e) {e.printStackTrace();}
	}
	
	@Override
	public String getBotUsername() {return null;}
	@Override
	public void onUpdateReceived(Update arg0) {}
	@Override
	public String getBotToken() {return "516441239:AAG9VU7f04JiZ5KVSDGyzVVYxLNeon1ANJo";}

}
