package Y2023.nov4;

import java.util.*;

/**
 * @author Himanshu Bhardwaj
 * @Date 11/4/2023
 */
public class Solution {
    public static void main(String[] args) {

        Logger logger = new Logger();
        TreeSet<Task> todoTaskRepo = new TreeSet<>();
        // assume we have to utill libraty to mainipulate fate from ectual format to epoch and vice versa
        Task task1 = new Task(1,"First Task",100l);
        Task task2 = new Task(2,"Second Task",101l);
        Task task3 = new Task(3,"third Task",102l);

        // Add tasks
        TaskI taskService = new TaskService(logger, todoTaskRepo);
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);

        // Print all tasks
        //System.out.println(taskService.getAlltasks());

        // Get Task by TaskID
        //System.out.println(taskService.getTask(2));

        /*
        //Modify Task
        task1 = new Task(1,"Modified First Task", 101);
        taskService.modifyTask(task1);
        System.out.println(taskService.getAlltasks());
        */

        /*
        taskService.removeTask(task1.taskId);
        taskService.markComplete(task2);
        System.out.println(taskService.getAlltasks());
        */

        task1.setTag("P0");
        task1.setTag("P1");
        task2.setTag("P1");
        task3.setTag("P1");

        taskService.modifyTask(task1);
        System.out.println(taskService.listTasks("P1", Comparator.reverseOrder()));


        System.out.println();
        System.out.println("Printing activities");
        LoggerOperationsI loggerOperations = new LoggerOperations(logger);
        loggerOperations.printActivityLog(2,4);
    }
}

class TaskService implements TaskI {

    // Assume there is a repo class which provide interface of operations which this tree set provides
    private TreeSet<Task> todoTaskRepo;

    private Logger logger;

    public TaskService(Logger logger, TreeSet<Task> todoTaskRepo) {
        // Repo and logger is injected to decouple solution
        this.todoTaskRepo = todoTaskRepo;
        this.logger = logger;
    }

    @Override
    public void addTask(Task task) {
        // This service implementation expects input parameters to be in correct format.
        // Example, it can not be null etc

        logger.add(new Log(logger.getLogId(), DateTimeUtil.getCurrentTime(),"addTask"));
        this.todoTaskRepo.add(task);
    }

    @Override
    public Task getTask(long taskId) {
        logger.add(new Log(logger.getLogId(), DateTimeUtil.getCurrentTime(),"getTask"));
        Task dummyTask = new Task(taskId,"", 0l);

        if (todoTaskRepo.contains(dummyTask)) {
            return todoTaskRepo.floor(dummyTask);
        }

        return null;
    }

    @Override
    public void modifyTask(Task task) {
        logger.add(new Log(logger.getLogId(), DateTimeUtil.getCurrentTime(),"modifyTask"));
        if (todoTaskRepo.contains(task)) {
            todoTaskRepo.remove(task);
        }
        todoTaskRepo.add(task);
    }

    @Override
    public void removeTask(long taskId) {
        logger.add(new Log(logger.getLogId(), DateTimeUtil.getCurrentTime(),"removeTask"));
        Task dummyTask = new Task(taskId,"",0l);
        if (todoTaskRepo.contains(dummyTask)) {
            todoTaskRepo.remove(dummyTask);
        }
    }

    @Override
    public void markComplete(Task task) {
        logger.add(new Log(logger.getLogId(), DateTimeUtil.getCurrentTime(),"removeTask"));
        if (todoTaskRepo.contains(task)) {
            Task oTask = todoTaskRepo.floor(task);
            // oTask and task has to be same.
            oTask.setTaskState(TaskState.COMPLETED);
            todoTaskRepo.remove(oTask);
        } else {
            throw new IllegalArgumentException("Task does not exist");
        }
    }

    @Override
    public List<Task> listTasks(String filterValue, Comparator sortCriteria) {
        logger.add(new Log(logger.getLogId(), DateTimeUtil.getCurrentTime(),"listTasks"));
        ArrayList<Task> taskList = new ArrayList<>();

        for (Task task: todoTaskRepo) {
            if (task.containsTag(filterValue)) {
                taskList.add(task);
            }
        }
        Collections.sort(taskList, sortCriteria);

        return taskList;
    }

    @Override
    public List<Task> getAlltasks() {
        logger.add(new Log(logger.getLogId(), DateTimeUtil.getCurrentTime(),"getAlltasks"));
        return new ArrayList<>(this.todoTaskRepo);
    }

    public Logger getLogger() {
        return logger;
    }
}

class Task implements Comparable<Task>{
    // Deadline is represented in epoch time.
    private long deadline;
    private long taskId;
    private String taskDescription;

    private HashSet<String> tags;
    private TaskState taskState;

    public Task(Task task) {
        this.taskId = task.taskId;
        this.deadline = task.deadline;
        this.tags = task.tags;
        this.taskState = task.taskState;
        this.taskDescription = task.taskDescription;
    }

    public Task(long taskId, String taskDescription, long deadline) {
        this.taskId = taskId;
        this.deadline = deadline;
        this.tags = new HashSet<>();
        this.taskState = TaskState.NOT_COMPETED;
        this.taskDescription = taskDescription;
    }

    public boolean containsTag(String tag) {
        return this.tags.contains(tag);
    }

    public void setTag(String tag) {
        this.tags.add(tag);
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public void setTags(List<String> tags) {
            this.tags.addAll(tags);
    }

    @Override
    public int compareTo(Task o) {
        return Long.compare(this.taskId, o.taskId);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskDescription='" + taskDescription + '\'' +
                "}\n";
    }
}

interface  TaskI {
    void addTask(Task task);
    Task getTask(long taskId);
    void modifyTask(Task task);
    void removeTask(long taskId);

    void markComplete(Task task);
    List<Task> listTasks(String filterValue, Comparator sortCriteria);

    List<Task> getAlltasks();
}

enum TaskState {
    NOT_COMPETED, COMPLETED;
}

class Log implements Comparable<Log> {
    private long logId;
    private long timeStamp;
    // We can have different other things link log level etc.
    private String Value;

    public Log(long logId, long timeStamp, String value) {
        this.logId = logId;
        this.timeStamp = timeStamp;
        Value = value;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", timeStamp=" + timeStamp +
                ", Value='" + Value + '\'' +
                "}\n";
    }

    @Override
    public int compareTo(Log o) {
        if (Long.compare(this.timeStamp,o.timeStamp) != 0) {
            return Long.compare(this.timeStamp,o.timeStamp);
        }
        return Long.compare(this.logId,o.logId);
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}

class Logger {
    private TreeSet<Log> logger;

    public Logger() {
        this.logger = new TreeSet<>();
    }

    long getLogId() {
        return logger.size()+1;
    }

    public void add(Log addTask) {
        this.logger.add(addTask);
    }

    public TreeSet<Log> getLogger() {
        return logger;
    }
}

class DateTimeUtil {
    private static long currentTime = 0;

    static long getCurrentTime() {
        currentTime++;
        return currentTime;
    }
}

interface LoggerOperationsI {
    void printActivityLog(long startTime, long endTime);
}

class LoggerOperations implements LoggerOperationsI {
    private Logger logger;

    public LoggerOperations(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }

    @Override
    public void printActivityLog(long startTime, long endTime) {
        for (Log log : logger.getLogger()) {
            if (log.getTimeStamp() >= startTime && log.getTimeStamp() <= endTime) {
                System.out.print(log);
            }
        }
    }
}