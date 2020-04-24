package run;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import lib.ConsoleIO;
import models.Table;
import tableHandling.TableCreation;

public class RunTime {
	
	private static String[] menuOptions = {"Create new Table", "View Saved Schemas", "Load Saved Schema"};
	
	public static void run() {
		
		Table workingTable;
		
		System.out.println("Welcome to ReQL. You currently have " + getNumberOfSchemas() + " saved schemas.");
		int selection = ConsoleIO.promptForMenuSelection(menuOptions, true);
		
		switch(selection) {
		
		case 1: String query = ConsoleIO.promptForInput("Please enter your table creation query", false);
				workingTable = TableCreation.createTable(query);
				break;
				
		case 2: getSavedSchemas();
				break;
				
		case 3: workingTable = loadSavedSchema();
				break;
				
		case 0: break;
		}
		
	}
	
	private static int getNumberOfSchemas() {
		int lines = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("schemas.txt"));
			while(reader.readLine() != null) lines++;
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}
	
	private static void getSavedSchemas() {
		ArrayList<String> schemas = new ArrayList<>();
		int counter = 1;
		
		if(getNumberOfSchemas() > 0) {
			
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader("schemas.txt"));
				String s;
				
				while((s = reader.readLine()) != null) {
					schemas.add(s);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for(String ss : schemas) {
				System.out.println(counter + ") " + ss);
				counter++;
			}
		}
	}
	
	private static Table loadSavedSchema() {
		Table t = null;
		
		if(getNumberOfSchemas() > 0) {
			String[] schemas = new String[getNumberOfSchemas()];
			int counter = 0;
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader("schemas.txt"));
				String s;
				
				while((s = reader.readLine()) != null) {
					schemas[counter] = s;
					counter++;
				}
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			int schemaSelected = ConsoleIO.promptForMenuSelection(schemas, false);
			String schemaString = schemas[schemaSelected - 1];
			
			t = TableCreation.createTableFromSave(schemaString);
		}
		
		return t;
	}
}
