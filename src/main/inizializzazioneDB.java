package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBManager;

public class inizializzazioneDB 
{

	public static void main(String[] args) 
	{
		Connection conn = DBManager.getConnection();
		
		ArrayList<String> sqlqueries = new ArrayList<String>();
		
		sqlqueries.add("CREATE TABLE Studenti\n" +
				"  ( \n" + 
				"  Matricola VARCHAR(10) NOT NULL,\n" + 
				"  CFU INT UNSIGNED NOT NULL,\n" +  
				"  PRIMARY KEY(Matricola)\n" + 
				");");
		
		sqlqueries.add("CREATE TABLE Docenti( \n" + 
				"  Nome VARCHAR(100) NOT NULL,\n" +
				"  Cognome VARCHAR(100) NOT NULL,\n" +
				"  ID VARCHAR(8) NOT NULL,\n" +
				"  NumeroElaboratiAssegnati INT UNSIGNED NOT NULL,\n" + 
				"  PRIMARY KEY(ID)\n" + 
				");");
		
		sqlqueries.add("CREATE TABLE Elaborati\n" +
				"  (\n" + 
				"  idElaborato INT NOT NULL AUTO_INCREMENT,\n" +
				"  Tematica VARCHAR(200) NOT NULL,\n" +
				"  TipoElaborato VARCHAR(100) NOT NULL,\n" +
				"  Insegnamento VARCHAR(200) NOT NULL,\n" +
				"  Assegnato BOOLEAN NOT NULL,\n" + 
				"  Docente VARCHAR(200) NOT NULL,\n" +
				"  FOREIGN KEY(Docente) REFERENCES Docenti(ID),\n" +
				"  PRIMARY KEY(idElaborato)\n" + 
				");");
		try 
		{
			for(String query : sqlqueries) 
			{
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.executeUpdate();
			}
			
			System.out.println("DB Initialization Successful.");
		}
		
		catch(SQLException e) 
		{

			e.printStackTrace();
		}	
	}
}