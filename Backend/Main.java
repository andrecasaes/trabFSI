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

		// Register our bot
		try {
			botsApi.registerBot(new BotApi20());
			tela.visu();
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}