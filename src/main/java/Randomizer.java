import java.util.ArrayList;

public class Randomizer {

    int randomNumber(int minValue, int maxValue) {

        return minValue + (int) (Math.random() * ((maxValue - minValue) + 1));
    }

    public String randomCharName(int wordLength) {
        String symbols = "abcdefghijklmnopqrstuvwxyz";
        String name = "";
        int nameLength = wordLength;
		
        for(int i=0;i<nameLength;i++){
            int indexValue= randomNumber(0,symbols.length()-1);
            name += symbols.charAt(indexValue);
        }

        return name;
    }
    
     ArrayList<Task> taskGenerator(int tasksAmount){
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		for (int i = 1; i<=tasksAmount;i++){
			tasks.add(new Task("Task ¹ "+i,randomNumber(1,2))); 
		}
		return tasks;
	}
	
}