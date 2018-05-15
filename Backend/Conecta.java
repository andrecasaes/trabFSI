
// Use the JDBC driver  
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Conecta {
	String select;
	static String resultado;
	int caso;
	int insere = 0;
	static ArrayList<String> profissionais =  new ArrayList<>();
	int z = 1;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	String selectSql;
	String connectionString= "jdbc:sqlserver://localhost:1433;" 
			+ "database=master;" 
			+ "user=andre;"
			+ "password=andre133;" 
			+ "encrypt=false;" 
			+ "trustServerCertificate=false;"
			+ "hostNameInCertificate=*.database.windows.net;" 
			+ "loginTimeout=30;";
	
	public static void main(String[] args) {}

	public String ProcuraID() {	
		
		long chat = NovoUsuario.chat_id;	
		caso = 1;//Procura
		select = String.format("SELECT * from UsuariosTelegram WHERE ID=%d", chat); 		
		return select;	
	}
	public String Insere() {//Cadastro	
		
			if (NovoUsuario.novoNome.equals("z293")) { //diferencia se é um novo nome ou é padrao
				long chat = NovoUsuario.chat_id;
				caso = 2;//Insere
				String nome = NovoUsuario.nome;
				select = String.format("INSERT INTO UsuariosTelegram (PrimeiroNome, ID) VALUES ('%s', %d);",nome, chat); 		
				return select;	
			}else {
				long chat = NovoUsuario.chat_id;
				caso = 2;//Insere
				String nome = NovoUsuario.novoNome;
				select = String.format("INSERT INTO UsuariosTelegram (PrimeiroNome, ID) VALUES ('%s', %d);",nome, chat); 		
				return select;	
			}
	}
	public String insereProfissional() {
		caso = 1;
		try {
			connection = DriverManager.getConnection(connectionString);
			System.out.println(BotApi20.codigoProf);
				select = String.format("SELECT NomeProf from Profissional where Codigo LIKE '%s';", BotApi20.codigoProf); //Procura se o codigo do prof existe na tabela
				System.out.println("Seleciona "+select);
				selectSql = select;
				statement = connection.createStatement();
				resultSet = statement.executeQuery(selectSql);
			
				while (resultSet.next()) {
		            	resultado = resultSet.getString("NomeProf");
		            	System.out.println("aaaa"+resultado);
		            	
		        if (!resultado.equals(null)) {    	
		        	select = String.format("UPDATE [dbo].[UsuariosTelegram] SET Profissional = '%s' WHERE ID = '%d';",BotApi20.codigoProf, NovoUsuario.chat_id);
		        	statement = connection.createStatement();
		        	insere = statement.executeUpdate(select);
		        	System.out.println(insere);
		       }else if (resultado.equals(null)){
		    	   System.out.println("deu nulo");
		       }
			}
		} catch (Exception e) {e.printStackTrace();
		} finally {
		// Close the connections after the data has been handled.
		if (resultSet != null)
			try {resultSet.close();} catch (Exception e) {}
		if (statement != null)
			try {statement.close();} catch (Exception e) {}
		if (connection != null)
			try {connection.close();} catch (Exception e) {}
		}
		return select;	
		
	}
	
	public void Consulta() {
		try {
			connection = DriverManager.getConnection(connectionString);
			selectSql = select;
			
		switch (caso) {
			case 1: //Procura
				statement = connection.createStatement();
				resultSet = statement.executeQuery(selectSql);

				while (resultSet.next()) {
		            System.out.println(resultSet.getString("PrimeiroNome"));
		            resultado = resultSet.getString("PrimeiroNome");
		            System.out.println(resultado);
		            z = 2;
		        }
				break;

			case 2: // Insere
				statement = connection.createStatement();
				insere = statement.executeUpdate(selectSql);
				System.out.println(insere);
				break;
			
			/*case 3: //Procura Profissionais. Cancelado
				statement = connection.createStatement();
				resultSet = statement.executeQuery(selectSql);
				while (resultSet.next()) {
						profissionais.add(resultSet.getString("NomeProf"));   		            
		        }
				
				System.out.println(profissionais);
				adicionaprof.AdicionaProf();
				profissionais.clear();
				break; */
			}			
		} catch (Exception e) 
			{e.printStackTrace();
		} finally {
			// Close the connections after the data has been handled.
			if (resultSet != null)
				try {resultSet.close();} catch (Exception e) {}
			if (statement != null)
				try {statement.close();} catch (Exception e) {}
			if (connection != null)
				try {connection.close();} catch (Exception e) {}
			}

	}
	
}