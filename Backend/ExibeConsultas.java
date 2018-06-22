import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class ExibeConsultas extends TelegramLongPollingBot{
	static long chatID;
	Conecta con = new Conecta();
	public void ExibeConsultas() {
	}
	public void pegaClientes() {
		chatID=BotApi20.chat_id;
		con.exibirConsulProf();
	}
	public void exibeLista() {
		int i=Conecta.nomeCliente.size();
		String texto="Essas são as suas atuais consultas!\n\n";
		String auxilio = "";
		
			
		String[] nome = new String[i];
		String[] Data = new String[i];
		String[] Hora = new String[i];
		String[] Min = new String[i];
		String[] tudo = new String[i];
		
		nome=Conecta.nomeCliente.toArray(nome);
		Data=Conecta.Data.toArray(Data);
		Hora=Conecta.Hora.toArray(Hora);
		Min=Conecta.Minuto.toArray(Min);

			int j =0;
			while( i != 0) {
			auxilio = Data[i-1];
			String aux[] = new String[3];
			aux = auxilio.split("-");
			
			tudo[i-1]="*"+nome[i-1]+"* dia "+aux[2]+"/"+aux[1]+"/"+aux[0]+" às "+Hora[i-1]+":"+Min[i-1]+"\n";
			texto+=tudo[i-1];
				i--;
			}
			
			
			SendMessage message = new SendMessage()
					.setChatId(BotApi20.chat_id)
					.enableMarkdown(true)
					.setText(texto);
			
			System.out.println("Todas as consultas enviadas!");
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
}
