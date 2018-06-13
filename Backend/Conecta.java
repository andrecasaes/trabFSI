
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
	static ArrayList<String> usuarios =  new ArrayList<>();
	int z = 1;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	String selectSql;
	
	
	AdicionaProf adicionaprof = new AdicionaProf();
	adicionaIDProf addID = new adicionaIDProf();
	
	
	public static void main(String[] args) {}

	public String ProcuraID() {	
		System.out.println("Iniciou ProcuraID em Conecta!");
		long chat = NovoUsuario.chat_id;	
		caso = 1;//Procura
		select = String.format("SELECT * from UsuariosTelegram WHERE ID=%d", chat); 		
		return select;	
	}
	public String Insere() { //Cadastro	
		System.out.println("Iniciou Insere em Conecta!");
		
			if (NovoUsuario.novoNome.equals("z293")) { //diferencia se é um novo nome ou é padrao
				long chat = NovoUsuario.chat_id;
				caso = 2;//Insere
				String nome = NovoUsuario.nome;
				select = String.format("INSERT INTO UsuariosTelegram (PrimeiroNome, ID) VALUES ('%s', %d);",nome, chat); 
				Consulta();
				return select;	
			}else {
				long chat = NovoUsuario.chat_id;
				caso = 2;//Insere
				String nome = NovoUsuario.novoNome;
				select = String.format("INSERT INTO UsuariosTelegram (PrimeiroNome, ID) VALUES ('%s', %d);",nome, chat);
				Consulta();
				return select;	
			}
	}
	public int insereProfissional() { // insere no cadastro do cliente a coluna de profissional
		System.out.println("Iniciou insereProfissional em Conecta!");
		caso = 1;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noshow?useSSL=false", "root","");
			System.out.println(BotApi20.nome);
				select = String.format("UPDATE `usuariostelegram` SET Profissional = '%s' WHERE ID = %d;",BotApi20.nomeProf, NovoUsuario.chat_id);
	        	statement = connection.createStatement();
	        	insere = statement.executeUpdate(select);
	        	System.out.println(insere);
	        	NovoUsuario novo =  new NovoUsuario();
	        	novo.cadastroFinalizado(); //Manda a mensagem de cadastro finalizado
		
		} catch (Exception e) {e.printStackTrace();} finally {
		if (resultSet != null)try {resultSet.close();} catch (Exception e) {}
		if (statement != null)try {statement.close();} catch (Exception e) {}
		if (connection != null)try {connection.close();} catch (Exception e) {}
		}
		return insere;	
		
	}
	public void procuraProf() { //Procura na tabela profissionais e seleciona todos
		System.out.println("Iniciou procuraProf em Conecta!");
		caso = 3; 
		selectSql = String.format("SELECT NomeProf from Profissional");
		Consulta();
	}
	public void procuraProfSemID() { //Procura na tabela profissionais e seleciona todos
		System.out.println("Iniciou procuraProf em Conecta!");
		caso = 4; 
		selectSql = String.format("SELECT NomeProf from Profissional where IDProf = '0'");
		Consulta();
	}
	public int insereIDProfissional() { // insere no cadastro do cliente a coluna de profissional
		System.out.println("Iniciou insereIDProfissional em Conecta!");
		caso = 1;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noshow?useSSL=false", "root","");
			System.out.println(BotApi20.nome);
				select = String.format("UPDATE `profissional` SET IDProf = '%d' WHERE NomeProf = '%s'", NovoUsuario.chat_id, BotApi20.nomeProf);
	        	statement = connection.createStatement();
	        	insere = statement.executeUpdate(select);
	        	System.out.println(insere);
	        	adicionaIDProf adiID = new adicionaIDProf();
	        	adiID.mensagemFinalizado();//Manda a mensagem de cadastro finalizado 
		
		} catch (Exception e) {e.printStackTrace();} finally {
		if (resultSet != null)try {resultSet.close();} catch (Exception e) {}
		if (statement != null)try {statement.close();} catch (Exception e) {}
		if (connection != null)try {connection.close();} catch (Exception e) {}
		}
		return insere;	
		
	}
	public void procProfConsul() { //Procura na tabela profissionais e seleciona todos
		NovaConsulta consul = new NovaConsulta();
		System.out.println("Iniciou procuraProf em Conecta!");
		caso = 6; 
		selectSql = String.format("SELECT NomeProf from Profissional where IDProf = '%d'",consul.chatID);
		System.out.println(selectSql);
		Consulta();
		procuraUsuProf();		
	}
	public void procuraUsuProf() { //Procura na tabela profissionais e seleciona todos
		System.out.println("Iniciou procuraUsuProf em Conecta!");
		caso = 5; 
		selectSql = String.format("SELECT PrimeiroNome from usuariostelegram where Profissional = '%s'",resultado);
		Consulta();
	}
	
	public void Consulta() {
		System.out.println("Iniciou consulta em Conecta!");
		resultado=null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noshow?useSSL=false", "root","");
			//selectSql = select;
			
		switch (caso) {	
			case 1: //Procura
				System.out.println("Iniciou caso 1 em Conecta!");	
				selectSql = select;
				statement = connection.createStatement();
				resultSet = statement.executeQuery(selectSql);
				
				while (resultSet.next()) {
		            System.out.println(resultSet.getString("PrimeiroNome"));
		            resultado = resultSet.getString("PrimeiroNome");
		            System.out.println("Resultado em caso 1 em consulta "+resultado);
		            z = 2;
		        }
				break;

			case 2: // Insere
				System.out.println("Iniciou caso 2 em Conecta!");
				selectSql = select;
				statement = connection.createStatement();
				insere = statement.executeUpdate(selectSql);
				System.out.println(insere);
				break;
			
			case 3: //Insere os profissionais no array e chama o adiciona prof 
				System.out.println("Iniciou caso 3 em Conecta!");
				statement = connection.createStatement();
				resultSet = statement.executeQuery(selectSql);
				while (resultSet.next()) {
						profissionais.add(resultSet.getString("NomeProf"));   		            
		        }
				
				System.out.println(profissionais);
				adicionaprof.AdicionaProf();
				profissionais.clear();
				break; 
			case 4: //Insere os profissionais no array e chama o adiciona prof 
				System.out.println("Iniciou caso 4 em Conecta!");
				statement = connection.createStatement();
				resultSet = statement.executeQuery(selectSql);
				while (resultSet.next()) {
						profissionais.add(resultSet.getString("NomeProf"));   		            
		        }
				
				System.out.println(profissionais);
				addID.adicionaIDProf();
				profissionais.clear();
				break; 
			case 5: //Insere os profissionais no array e chama o adiciona prof 
				NovaConsulta consul = new NovaConsulta();
				System.out.println("Iniciou caso 5 em Conecta!");
				statement = connection.createStatement();
				resultSet = statement.executeQuery(selectSql);
				while (resultSet.next()) {
						usuarios.add(resultSet.getString("PrimeiroNome"));   		            
		        }
				
				System.out.println(usuarios);
				consul.tecladoConsulta();
				usuarios.clear();
				break;
			case 6: //Procura
				System.out.println("Iniciou caso 1 em Conecta!");				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(selectSql);
				
				while (resultSet.next()) {
		            System.out.println(resultSet.getString("NomeProf"));
		            resultado = resultSet.getString("NomeProf");
		            System.out.println("Resultado em caso 1 em consulta "+resultado);
		        }
				break;

			}
		
		} catch (Exception e){e.printStackTrace();
		} finally {
			// Close the connections after the data has been handled.
			if (resultSet != null)
				try {resultSet.close();} catch (Exception e) {}
			if (statement != null)
				try {statement.close();} catch (Exception e) {}
			if (connection != null)
				try {connection.close();} catch (Exception e) {}
			System.out.println("Fechou a conexão no consulta em conecta!");
			}

	}
	
}