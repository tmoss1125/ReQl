package tableHandling;

import java.nio.file.Path;
import java.nio.file.Paths;

import models.Table;

public class TableCreation {

	//CREATE TABLE 'appointments' (patient_name,doctor_name,apt_date,apt_time,topic): line format /(\w*);(\w*);([^ ]*) ([^ ]*);(.*$) /file 'C:/appts.txt';
	
	public static Table createTable(String creationQuery) {
		String tableName = creationQuery.substring(14, creationQuery.indexOf("'", 14));
		String[] columnNames = sortColumnNames(creationQuery);
		String[] columnFormats = sortColumnFormats(creationQuery);
		Path filePath = Paths.get(creationQuery.substring((creationQuery.indexOf("$") + 10), creationQuery.lastIndexOf("'")));
		
		Table t = new Table(tableName, columnNames, columnFormats, filePath);
		
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
}
