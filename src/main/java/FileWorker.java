import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWorker {
	
	 void appender(String filename,BufferedWriter bw) throws IOException{
		PrintWriter out = new PrintWriter(bw);		
	}
	
	BufferedWriter getWeeklyContent(int week,ArrayList<Employee> employees, String filename) throws IOException{
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename,true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bw = new BufferedWriter(fw);
		
		for(int i=0;i<employees.size();i++){
			bw.append(System.getProperty("line.separator"));
			bw.append(String.valueOf(week));
			bw.append(";");
			bw.append(employees.get(i).getName());
			bw.append(";");
			bw.append(employees.get(i).getSurname());
			bw.append(";");
			bw.append(employees.get(i).getProfession());
			bw.append(";");
			bw.append(String.valueOf(employees.get(i).getMoneyEarned()));
			bw.append(";");
		}
		bw.close();
		return bw;
	} 
	
	void DeletingFileContent(String filename) throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(filename);
		writer.print("");
		writer.close();
	}
		
}

