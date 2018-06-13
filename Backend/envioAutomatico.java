import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import oracle.net.aso.a;

public class envioAutomatico {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	String selectSql;
	ArrayList<String> nomesClientes =  new ArrayList<>();
	static String dataAmanha;
	static int[] ids;
	String nome = null;
	String[] arrayNome;
	Calendar dathj = Calendar.getInstance();
	Calendar proxDat = Calendar.getInstance();
	static long IDUsua;
	static long IDProf;
	static String nomeProf;
	
	

	public void envioDiario() {
		DateFormat dat = new SimpleDateFormat("yyyy/MM/dd");
		
			if (dathj.get(Calendar.YEAR) == proxDat.get(Calendar.YEAR) && dathj.get(Calendar.DAY_OF_YEAR) == proxDat.get(Calendar.DAY_OF_YEAR)) {
				proxDat.add(Calendar.DATE, 1);
				dataAmanha = dat.format(proxDat.getTime());
				System.out.println("Novo dia!--Executando o processo de envio automatico--Data do dia seguinte: "+dataAmanha);
				procuraData();
			}
	}

	public void procuraData() {
			System.out.println("Inicio da procura pela data do dia seguinte na tabela TodasConsultas");
		try {
			selectSql = String.format("SELECT nomeCliente from todasconsulta WHERE Data = '%s' ", dataAmanha); 
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noshow?useSSL=false", "root","");
			statement = connection.createStatement();
	    	resultSet = statement.executeQuery(selectSql);
	    	
	    	if(resultSet.next()==true) {
	    		System.out.println("Yay tem consulta!-- Os clientes são:");
	    		nomesClientes.add(resultSet.getString("nomeCliente")); //Se nao tiver essa linha antes do while, o programa nao vai exibir o primeiro da tabela.
	    		System.out.println(resultSet.getString("nomeCliente")); 
	    		while (resultSet.next()==true) {
	    			System.out.println(resultSet.getString("nomeCliente"));
	    			nomesClientes.add(resultSet.getString("nomeCliente"));		
	    		} 
	    		pegaOsID();
	    	}else if (resultSet.next()==false) {
	    		System.out.println("Nenhuma consulta no dia seguinte");
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
	}
	public void pegaOsID() {
		System.out.println("Inicio da procura pelos IDs dos clientes para o envio automatico!");
		int i = nomesClientes.size();
		arrayNome = new String[i];
		arrayNome=nomesClientes.toArray(arrayNome);
		ids= new int[i];
		
		for (i = nomesClientes.size(); i > 0; i--) {
			nome = arrayNome[i-1];
			try {
				selectSql = String.format("SELECT ID from usuariostelegram WHERE PrimeiroNome = '%s'", nome); 
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noshow?useSSL=false", "root","");
				statement = connection.createStatement();
		    	resultSet = statement.executeQuery(selectSql);
		    	if(resultSet.next()==true) {
		    		ids[i-1]=resultSet.getInt("ID");
		    		while (resultSet.next()==true) {
		    			ids[i-1]=resultSet.getInt("ID");
		    			i++;
		    		} 
		    	} 	
			} catch (Exception e) {e.printStackTrace();
			} finally {
			if (resultSet != null)try {resultSet.close();} catch (Exception e) {}
			if (statement != null)try {statement.close();} catch (Exception e) {}
			if (connection != null)try {connection.close();} catch (Exception e) {}
			}			
		}
		MandaMSG man = new MandaMSG();
		man.enviaMSGAuto();
		arrayNome=null;
		ids=null;
		nomesClientes.clear();
	}
	
	public void envioRespProf() {
		FuncionaBotao fun = new FuncionaBotao();
		MandaMSG man = new MandaMSG();
		IDUsua= fun.chat_id;	
		try {
			selectSql = String.format("SELECT Profissional from usuariostelegram WHERE ID = '%d'", IDUsua); 
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noshow?useSSL=false", "root","");
			statement = connection.createStatement();
	    	resultSet = statement.executeQuery(selectSql);
	    	
	    	while (resultSet.next()==true) {
	    		nomeProf = resultSet.getString("Profissional");
	    		System.out.println(nomeProf);
	    		selectSql = String.format("SELECT IDProf from profissional WHERE NomeProf = '%s'", nomeProf); 
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/noshow?useSSL=false", "root","");
				statement = connection.createStatement();
		    	resultSet = statement.executeQuery(selectSql);
		    	while (resultSet.next()==true) {
		    		IDProf = resultSet.getLong("IDProf");
		    		man.confConsulProf();
		    	}
	    	} 	
		} catch (Exception e) {e.printStackTrace();
		} finally {
		if (resultSet != null)try {resultSet.close();} catch (Exception e) {}
		if (statement != null)try {statement.close();} catch (Exception e) {}
		if (connection != null)try {connection.close();} catch (Exception e) {}
		}
	}
}
