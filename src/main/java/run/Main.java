package run;

import models.Table;
import tableHandling.TableCreation;

public class Main {

	public static void main(String[] args) {
		
		String creationQuery = "CREATE TABLE 'appointments' (patient_name,doctor_name,apt_date,apt_time,topic): line format /(\\w*);(\\w*);([^ ]*) ([^ ]*);(.*$) /file 'C:/appts.txt'";
		
		Table t = TableCreation.createTable(creationQuery);
		
		System.out.println(t.toString());

	}

}
