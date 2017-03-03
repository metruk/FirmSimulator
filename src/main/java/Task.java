
public class Task {
	private String taskName;
	private int timeForTask;
	
	public Task(String taskName, int timeForTask) {
		super();
		this.taskName = taskName;
		this.timeForTask = timeForTask;
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public int getTimeForTask() {
		return timeForTask;
	}
	public void setTaskForTask(int taskForTask) {
		this.timeForTask = taskForTask;
	}
	
	@Override
	public String toString() {
		return "Task [taskName=" + taskName + ", taskForTask=" + timeForTask + "]";
	}

	

}
