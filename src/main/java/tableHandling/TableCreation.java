package tableHandling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import models.Table;

public class TableCreation {
	
	public static Table createTable(String creationQuery) {
		Table t = null;
		boolean isValid = false;
		

				
		String tableName = creationQuery.substring(14, creationQuery.indexOf("'", 14));
		String[] columnNames = sortColumnNames(creationQuery);
		String[] columnFormats = sortColumnFormats(creationQuery);
		Path filePath = Paths.get(creationQuery.substring((creationQuery.indexOf("$") + 10), creationQuery.lastIndexOf("'")));
				
		t = new Table(tableName, columnNames, columnFormats, filePath);

		
		saveSchema(creationQuery);
		
		return t;
	}
	
	public static Table createTableFromSave(String savedSchema) {
		Table t = null;
		boolean isValid = false;
		
		do {
			
			try {
				
				String tableName = savedSchema.substring(14, savedSchema.indexOf("'", 14));
				String[] columnNames = sortColumnNames(savedSchema);
				String[] columnFormats = sortColumnFormats(savedSchema);
				Path filePath = Paths.get(savedSchema.substring((savedSchema.indexOf("$") + 10), savedSchema.lastIndexOf("'")));
				
				t = new Table(tableName, columnNames, columnFormats, filePath);
				
				isValid = true;
				
			}catch(Exception e) {
				System.out.println("There was an error with your creation query, please try again");
			}
			
		}while (!isValid);
		
		return t;
	}
	
	private static String[] sortColumnFormats(String query) {
		String s1 = query.substring(query.indexOf("/"));
		String s2 = s1.substring(1, (s1.lastIndexOf(")") + 1));
		
		String[] result = s2.split(";");
		
		return result;
	}
	
	private static String[] sortColumnNames(String query) {
		String s1 = query.substring((query.indexOf("(") + 1), query.indexOf(")"));
		String[] sf = s1.split(",");
		
		return sf;
	}
	
	private static void saveSchema(String t) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("schemas.txt", true));
			
			writer.newLine();
			writer.write(t);
			writer.close();
			
			System.out.println("Schema saved successfully!");
		} catch (IOException e) {
			System.out.println("There was an error while trying to save your schema");
			e.printStackTrace();
		}
	}
}
