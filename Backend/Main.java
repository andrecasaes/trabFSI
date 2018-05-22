import java.sql.SQLException;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
	public static void main(String[] args) throws SQLException {
		// Initialize Api Context
		ApiContextInitializer.init();

		TelaTeste tela = new TelaTeste();
		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();
		MandaMSG bot = new MandaMSG();

		// Register our bot
		try {
			botsApi.registerBot(new BotApi20());
			bot.MandaMSG();	
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}