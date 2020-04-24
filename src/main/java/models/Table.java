package models;

import java.nio.file.Path;

public class Table {

	private String tableName;
	private String[] columnNames;
	private String[] columnFormats;
	private Path filePath;
	
	public Table(String tableName, String[] columnNames, String[] columnFormats, Path filePath) {
		this.setTableName(tableName);
		this.setColumnNames(columnNames);
		this.setColumnFormats(columnFormats);
		this.setFilePath(filePath);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public String[] getColumnFormats() {
		return columnFormats;
	}

	public void setColumnFormats(String[] columnFormats) {
		this.columnFormats = columnFormats;
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Table: " + getTableName() + ": ");
		sb.append("Column Names: " + printArrays(getColumnNames()) + ", ");
		sb.append("Column Formats: " + printArrays(getColumnFormats()) + ", ");
		sb.append("File Path: " + filePath);
		
		return sb.toString();
	}
	
	public String printArrays(String[] array) {
		StringBuilder sb = new StringBuilder();
		
		for(String s : array) {
			sb.append(s + " ");
		}
		
		return sb.toString().trim();
	}
}
