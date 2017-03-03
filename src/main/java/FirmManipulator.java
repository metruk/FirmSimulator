import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FirmManipulator {

    final private static int MINIMUM_EMPLOYERS=10;
    final private static int MAXIMUM_EMPLOYERS=15;
    final private static  String [] MANDATORY_WORKERS={
    		"director",
    		"bookeper",
    		"manager"
    };
    final private static int AMOUNT_OF_WEEKS_IN_MONTH = 4;
    
    private static  String filename;
    
    private static Randomizer random = new Randomizer();

    private static XmlWorker xml = new XmlWorker();
    FirmManipulator(String filename){
    	this.filename = filename;	
    }
    //for all workers make hours left to work = 8 
    void restoreWorkersHourAbility(ArrayList<Employee> emp){
    	int wholeDayworkingHours = 8;
    	for(int i=0;i<emp.size();i++) {
    		emp.get(i).setWorkingHour(wholeDayworkingHours);
        }
    }
    
    void clearWorkersWeeklySalary(ArrayList<Employee> emp){
    	for(int i=0;i<emp.size();i++) {
    		emp.get(i).setMoneyEarned(0);
        }
    }
    
    //detecting indexes of Profession name in Array List pf workers
    ArrayList<Integer> detectWorkerIndexes(ArrayList<Employee> emp,String vacancyForDetecting){
    	ArrayList<Integer> indexSaver = new ArrayList<Integer>();
    	for(int i=0;i<emp.size();i++) {
    		if(emp.get(i).getProfession().contains(vacancyForDetecting)){
            	indexSaver.add(i);
            }
        }
    	return indexSaver;
    }
    
    //checking if bookeeper, manager, director are present in list of workers and adding if no
    void mandatoryEmployeeChecker(ArrayList<Employee> employees,String vacancy){
    	boolean isWokerExists = false;
    	
    	for(int i=0;i<employees.size();i++){
    		if(employees.get(i).getProfession().equals(vacancy)){
            	isWokerExists = true;
            }
    	}
    	
    	if(!isWokerExists){
    		employees.add(new Employee(vacancy));
    	}
    }
    
    //deleting director, manager, bookeper if present more then 1 vacancy for each of them
    ArrayList<Employee> deleteDublicateByIndex(ArrayList<Employee> emp,ArrayList<Integer> duplicatedVacancies){

    	if(duplicatedVacancies.size()>1){
    		
    		for(int i=1;i<duplicatedVacancies.size();i++) {
    			int indexForDelete =  duplicatedVacancies.get(i).intValue();
    				if(i!=1){
    					indexForDelete = indexForDelete - i ;	
    				}	
    			emp.remove(indexForDelete);
        	}
    	}
    	return emp;
    }
    
    //generating list of workers
    ArrayList<Employee> getEmployeeFactory() {
        
    	int employers = random.randomNumber(MINIMUM_EMPLOYERS,MAXIMUM_EMPLOYERS);
        ArrayList<Employee> employees = new ArrayList<Employee>();

        for(int i = 0; i < employers; i++){
            employees.add(new Employee());
        }
            
        for(int i = 0; i < MANDATORY_WORKERS.length; i++){
        	ArrayList<Integer> list = detectWorkerIndexes(employees, MANDATORY_WORKERS[i]);
        	employees = deleteDublicateByIndex(employees, list);
        }
        
        for(int i = 0; i < MANDATORY_WORKERS.length; i++){
        	mandatoryEmployeeChecker(employees, MANDATORY_WORKERS[i]);       
        }
        
        return employees;
    }



    void WorkingProcessSimulator(ArrayList<Employee> emloyees) throws IOException{
    	FileWorker  file = new FileWorker();
    	file.DeletingFileContent(filename);
    	//working days in week from Monday to Friday
    	ArrayList<String> workingdays= new ArrayList<String>();
		workingdays.add("Mon");
		workingdays.add("Tue");
		workingdays.add("Wed");
		workingdays.add("Thur");
		workingdays.add("Fri");
		
		
		
		for(int i=1;i<=AMOUNT_OF_WEEKS_IN_MONTH;i++){
			System.out.println("Week "+i);
			
			//list of freelancers(when workers not enough to take all the tasks)
			ArrayList<Employee> freelancers = new ArrayList<Employee>();
			
			for (int j=0;j<workingdays.size();j++ ){
				System.out.println("================");
				System.out.println("Day of week "+workingdays.get(j));
				for(int h=1;h<=8;h++){
					System.out.println("================");
					System.out.println("Hour of working day: " + h);
					System.out.println("----------------");
					
					//generating random number of tasks(minimum = amount of workers, maximum = minimum + 5)
					int amountOfTasks = random.randomNumber(emloyees.size(),emloyees.size() + 5);
					System.out.println("Amount of tasks "+ amountOfTasks);
					
					//list of tasks on current hour
					ArrayList<Task> tasks = random.taskGenerator(amountOfTasks);
					
					//counter of tasks that were missed
					int counterOfMissedTasks = 0;
					int timeForWork = 0;
					//getting tasks on current hour
					    for(int e=0;e<tasks.size();e++){
					    	try{
					    		//checking if employee is director, because he don't participate in working proccess
					    		if(emloyees.get(e).getProfession().contains("director")){
					    			System.out.println("Director don't participate in working proccess ");
						    		counterOfMissedTasks++;
						    		continue;
					    		}
					    		
					    		//checking if previous employee's task implementation = 2 
					    		if(emloyees.get(e).isAbleTowork()== false){
					    			emloyees.get(e).isAbleTowork = true;
					    			counterOfMissedTasks++;
					    			System.out.println("Worker "+emloyees.get(e)+ " is involved to previous task!");
					    			continue;
					    		}
					    		
					    		System.out.println(emloyees.get(e)+" doing: "+tasks.get(e).getTaskName());
			
					    		//time for task execution
					    		timeForWork = tasks.get(e).getTimeForTask();
					    		System.out.println("It will take: "+timeForWork+" hours");
					    		
					    		//how much hours worker is able to work this day
					    		int hoursLeftToWork = emloyees.get(e).getWorkingHour() - timeForWork;
					    		emloyees.get(e).setWorkingHour(hoursLeftToWork);
					    		//counting of earned moneys 
					    		emloyees.get(e).setMoneyEarned(timeForWork * emloyees.get(e).getHourSalary());
					    		
					    		//block employee on next iteration(task distribution on the next hour)
					    		if(timeForWork == 2){
					    			emloyees.get(e).setAbleTowork(false);
					    		}
					    		
					    	}catch(java.lang.IndexOutOfBoundsException ex){
					    		System.out.println("Freelancer needed ");
					    		freelancers.add(new Employee("freelancer"));
					    		Employee currentFreelancer = freelancers.get(freelancers.size()-1);
					    		//earned money for the task
					    		currentFreelancer.setMoneyEarned(timeForWork * currentFreelancer.getHourSalary());
					    		//ability to work
					    		currentFreelancer.setWorkingHour(currentFreelancer.getWorkingHour()- timeForWork);
					    		System.out.println("Freelancer "+currentFreelancer+" is involved with task ");
					    	}
					    }
					    System.out.println("MISSED "+counterOfMissedTasks);
			    		for(int missedTasks = 0;missedTasks < 2; missedTasks++){
			    			freelancers.add(new Employee("freelancer"));
			    			
				    		Employee currentFreelancer = freelancers.get(freelancers.size()-1);
				    		//earned money for the task
				    		currentFreelancer.setMoneyEarned(timeForWork * currentFreelancer.getHourSalary());
				    		//ability to work
				    		currentFreelancer.setWorkingHour(currentFreelancer.getWorkingHour()- timeForWork);
				    		System.out.println("Freelancer "+currentFreelancer+" is involved with task ");
			    			
			    		}
				}
				System.out.println("Day ended");
				restoreWorkersHourAbility(emloyees);

			}
			System.out.println("Week Ended");
			
			BufferedWriter bw = null;
			//writing to file workers
			bw = file.getWeeklyContent(i, emloyees, filename);
			file.appender(filename,bw);
			
			//writing to file freelancers
			bw = file.getWeeklyContent(i, freelancers, filename);
			file.appender(filename,bw);
			
		}

    }

}