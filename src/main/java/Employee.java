public class Employee {
	
	final private static int WORKING_HOURS=8;
    private String name;
    private String surname;
    private double hourSalary;
    private String profession;
    private int workingHour;
    private double moneyEarned;
    boolean isAbleTowork;
    
   static Randomizer rnd = new Randomizer();
   static XmlWorker xml = new XmlWorker();
    
    public Employee() {

        this.name = rnd.randomCharName(8);
        this.surname = rnd.randomCharName(7);
        this.hourSalary = rnd.randomNumber(75,100);
        this.profession = xml.getVacancy();
        this.workingHour = WORKING_HOURS;
        this.moneyEarned = 0;
        this.isAbleTowork = true;  
        
    }
    

	public Employee(String proffesion) {

        this.name = rnd.randomCharName(8);
        this.surname = rnd.randomCharName(7);
        this.hourSalary = rnd.randomNumber(75,100);
        this.profession = proffesion;
        this.workingHour = WORKING_HOURS;
        this.moneyEarned = 0;
        this.moneyEarned = 0;
        this.isAbleTowork = true;  
    }

    int countHour(Employee employee, int hours){
        int hourLeft = employee.getWorkingHour() - hours;
        employee.setWorkingHour(hourLeft);

        return hourLeft;
    }
    
    public int recalculateWorkingHours(int hours) {
		return WORKING_HOURS - hours;
	}
    
    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public double getHourSalary() {
		return hourSalary;
	}


	public void setHourSalary(double hourSalary) {
		this.hourSalary = hourSalary;
	}


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public int getWorkingHour() {
		return workingHour;
	}


	public void setWorkingHour(int workingHour) {
		this.workingHour = workingHour;
	}


	public double getMoneyEarned() {
		return moneyEarned;
	}


	public void setMoneyEarned(double moneyEarned) {
		this.moneyEarned += moneyEarned;
	}


	public static XmlWorker getXml() {
		return xml;
	}


	public static void setXml(XmlWorker xml) {
		Employee.xml = xml;
	}


	public boolean isAbleTowork() {
		return isAbleTowork;
	}


	public void setAbleTowork(boolean isAbleTowork) {
		this.isAbleTowork = isAbleTowork;
	}
	
		@Override
		public String toString() {
			return "Employee [hourSalary=" + hourSalary + ", profession=" + profession + ", workingHour=" + workingHour
					+ ", moneyEarned=" + moneyEarned + ", isAbleTowork=" + isAbleTowork 
					 + "]";
		}
   
}