import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public  static  void main(String [] args) throws ParserConfigurationException, IOException {
    	
		//file for writing weekly report
    	String filename = "zp.csv";
    	
        FirmManipulator firm = new FirmManipulator(filename);
        //getting firm workers
        ArrayList<Employee> firmWorkers =firm.getEmployeeFactory();
        //working process modeling
        firm.WorkingProcessSimulator(firmWorkers);
    
    }
}