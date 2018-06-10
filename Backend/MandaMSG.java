import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.eclipse.swt.widgets.Synchronizer;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MandaMSG extends TelegramLongPollingBot {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	String selectSql;
	String select;
	int ID = 0;
	int[] arID;
	static ArrayList<Integer> arrayid = new ArrayList<>();
	Date dt = new Date(0);
	int hr,min = 0;
	int resultado = 0;
	int insere = 0;
	String nomeCliente= null;
	String nomeProf= null;
	
	
	
	public void MandaMSG() {		
		while (true) {
			try {
				Thread.sleep(5 * 1000);
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noshow?useSSL=false", "root", "");
				select = "SELECT * FROM `mandaconsulta`";
				statement = connection.createStatement();
				resultSet = statement.executeQuery(select);
				//System.out.println("esse " + resultSet);
				System.out.println("ID: "+ID);
				
				while (resultSet.next()) {
					ID = resultSet.getInt("ID");
					nomeCliente = resultSet.getString("nomeCliente");
					nomeProf = resultSet.getString("Profissional");
					dt = resultSet.getDate("Data");
					hr = resultSet.getInt("Hora");
					min = resultSet.getInt("Minuto");
					System.out.println(dt);
					System.out.println(hr+" "+min);
					System.out.println("BAIXO " + ID);
					enviaMSG();
					select = String.format("DELETE FROM `mandaconsulta` WHERE ID = '%d'", ID) ;//Apaga a linha dos que ja mandou msg
					statement = connection.createStatement();
					resultado = statement.executeUpdate(select);
					ID=0;
				}
				if (ID == 0) {System.out.println("Deu nulo");}
			} catch (Exception e) {System.out.println("Será? "+e.getMessage());e.printStackTrace();} 
				finally{
					if (resultSet != null)try {resultSet.close();} catch (Exception e) {}
					if (statement != null)try {statement.close();} catch (Exception e) {}
					if (connection != null)try {connection.close();} catch (Exception e) {}
			}
			
		} // While grande
	}// Classe
	public void enviaMSG() {
		System.out.println("Mandou msg para: "+ID);
		long chatId = ID;
		String textinho = String.format("Eae %s, tudo bem?\n"
				+ "%s, gostaria de saber se vc irá na consulta no dia\n*"
				+dt+"* as *"+hr+":"+min+"*", nomeCliente, nomeProf);
		
		SendMessage manda = new SendMessage() // Create a message object object
				.setChatId(chatId)
				.enableMarkdown(true)
				.setText(textinho);

		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();

		rowInline.add(new InlineKeyboardButton().setText("Vou sim").setCallbackData("Sim"));
		rowInline.add(
				new InlineKeyboardButton().setText("Vai te catar, vou não").setCallbackData("Nao"));
		// Set the keyboard to the markup
		rowsInline.add(rowInline);
		// Add it to the message
		markupInline.setKeyboard(rowsInline);
		manda.setReplyMarkup(markupInline);

		try {execute(manda);} catch (TelegramApiException c) {c.printStackTrace();}
		
		System.out.println("ID2:" +ID);
	}
	
	@Override
	public String getBotUsername() {return null;}

	@Override
	public void onUpdateReceived(Update arg0) {}

	@Override
	public String getBotToken() {return "516441239:AAG9VU7f04JiZ5KVSDGyzVVYxLNeon1ANJo";}

}
