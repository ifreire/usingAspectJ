package br.com.ifreire.f1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.time.Instant;

import com.db4o.*;

import br.com.ifreire.entities.Pilot;

public class Main
{
	final static String DB4OFILENAME = "db/mtc.db4o";
	static ObjectContainer DB;
	
	static String driverPostgresql = "org.postgresql.Driver";
	static String urlPostgresql    = "jdbc:postgresql://127.0.0.1:5432/ubuntu";
	static String userPostgresql   = "ubuntu";
	static String senhaPostgresql  = "";
	static Connection conPostgresql;
	
	static String driverMariaDB = "org.postgresql.Driver";
	static String urlMariaDB    = "jdbc:postgresql://127.0.0.1:5432/ubuntu";
	static String userMariaDB   = "ubuntu";
	static String senhaMariaDB  = "";
    
	public static void main(String[] args)
	{
		new File(DB4OFILENAME).delete();
		
		int qtDB4O = 100000;
		int qtPGSQL = 10000;
		
		execDB4O(qtDB4O);
		
		execPostgreSQL(qtPGSQL);
    }
	
///////////////////////////////////////////////////////////
//                                                       //
//               DB4O               INÍCIO               //
//                                                       //
///////////////////////////////////////////////////////////
    
	private static void execDB4O(int qt)
	{
		try
		{
			inicio();
			startDb4o();
			
			storePilotsDB4O(qt);
	    	//retrieveAllPilotsDB4O(true);
	    	//updateAllPilotsDB4O();
	    	//retrieveAllPilotsDB4O(true);
	    	//deleteAllPilotsDB4O();
		}
		finally
		{
			closeDb4o();
			fim();
		}
	}

	private static void startDb4o()
	{
		DB = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
    }
	
	private static void closeDb4o()
	{
		DB.close();
	}
	
	private static void storePilotsDB4O(int qt)
	{
		int id = 0;
		
		for (int i = 0; i < qt; i++)
		{
			id++;
			Pilot p = new Pilot("Piloto ".concat(Integer.toString(id)), id);
			DB.store(p);
		}
		
		System.out.println(Integer.toString(qt).concat(" pilotos adicionados!"));
	}
	
	private static ObjectSet<Pilot> retrieveAllPilotsDB4O(Boolean isToList)
    {
        ObjectSet<Pilot> result = DB.queryByExample(Pilot.class);
        
        if (isToList) listResult(result);
        
        return result;
    }
	
	private static void updateAllPilotsDB4O()
	{
        ObjectSet<Pilot> result = retrieveAllPilotsDB4O(false);
        
        for (Pilot pilot : result)
        {
			pilot.addPoints(1000);
			DB.store(pilot);
		}
        
        System.out.println(Integer.toString(result.size()).concat(" pilotos alterados!"));
    }
	
	private static void deleteAllPilotsDB4O()
    {
        ObjectSet<Pilot> result = retrieveAllPilotsDB4O(false);
        
        for (Pilot pilot : result)
        	DB.delete(pilot);
        
        System.out.println(Integer.toString(result.size()).concat(" pilotos excluídos!"));
    }
	
///////////////////////////////////////////////////////////
//                                                       //
//               DB4O                 FIM                //
//                                                       //
///////////////////////////////////////////////////////////
	
	private static void execPostgreSQL(int qt)
	{
		try
		{
			inicio();
			startPGSQL();
			
			storePilotsPGSQL(qt);
	    	//retrieveAllPilotsDB4O(true);
	    	//updateAllPilotsDB4O();
	    	//retrieveAllPilotsDB4O(true);
	    	//deleteAllPilotsDB4O();
		}
		finally
		{
			closePGSQL();
			fim();
		}
	}
	
	private static void startPGSQL()
	{
		try
		{
			//Class.forName(driverPostgresql);
			conPostgresql = (Connection) DriverManager.getConnection(urlPostgresql, userPostgresql, senhaPostgresql);
			System.out.println("Conexão PostgreSQL realizada com sucesso.");
			
		}
		//catch (ClassNotFoundException ex)
        //{
        //    System.err.print(ex.getMessage());
        //} 
        catch (SQLException e)
        {
            System.err.print(e.getMessage());
        }
	}
	
	private static void storePilotsPGSQL(int qt)
	{
		String sql;
		int id = 0;
		
		try
		{
			Statement stmt = (Statement)conPostgresql.createStatement();
			
			for (int i = 0; i < qt; i++)
			{
				id++;
				sql = String.format("INSERT INTO PILOTS(NAME, POINTS) VALUES ('%s', %d)", "Piloto ".concat(Integer.toString(id)), id);
				stmt.execute(sql);
			}
			
			System.out.println(Integer.toString(qt).concat(" pilotos adicionados!"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void closePGSQL()
	{
		try
		{
			if (!conPostgresql.isClosed()) conPostgresql.close();
			System.out.println("Conexão PostgreSQL finalizada com sucesso.");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static void listResult(ObjectSet<Pilot> result)
    {
		for(Pilot pilot : result)
			printPilot(pilot);
	}
	
	private static void printPilot(Pilot pilot)
	{
		System.out.println(pilot.getName() + ": " + pilot.getPoints() + " pontos.");
	}
	
	private static void inicio()
	{
		Instant inicio = Instant.now();
		System.out.println(inicio);
	}
	
	private static void fim()
	{
		Instant fim = Instant.now();
		System.out.println(fim);
	}
}