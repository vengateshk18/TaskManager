package TaskManager;
import java.util.*;


public  class main {
    public static void main(String args[]){
        User user1=new User("venky");
        User user2=new User("rocky");
        Task task1=user1.createTask(TaskType.ARCHITECTURE);
        Task atsk2=user1.createTask(TaskType.MEETING);
        Sprint spObj=user1.createSprint(10,20);
        spObj.addTask(task1);
        task1.setTaskStatus(Status.IN_PROGRESS);
        spObj.printDetails();
        user1.printTasks();
    }
}

enum Status {
    OPEN, IN_PROGRESS, CLOSED
}
enum TaskType{
    BUG,NEW_FEATURE,ARCHITECTURE,DATABASE_INTEGRATION,MEETING
}
class User{
    private int ID;
    private String name;
    private static int IdCounter=100;
    private List<Sprint> sprints;
    private List<Task> tasks;
    User(String name){
        this.ID=this.IdCounter++;
        this.name=name;
        this.tasks=new ArrayList<>();
        this.sprints=new ArrayList<>();
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getId(){
        return this.ID;
    }

    public void printTasks(){
        for(Sprint sp:this.sprints){
            for(Task task:sp.getTasks()){
                System.out.println(task.toString());
            }
        }
    }
    public void changeTaskStatus(int taskId, Enum status){
        for(Sprint sp:this.sprints){
            for(Task task:sp.getTasks()){
                if(task.getId()==taskId){
                    task.setTaskStatus(status);
                    break;
                }
            }
        }
    }
    public void addTaskToSprint(int sprintId,Task task){
        for(Sprint sp:this.sprints){
            if(sp.getId()==sprintId){
                sp.addTask(task);
                break;
            }
        }
    }
    public Sprint createSprint(int start, int end){
        Sprint sprintObj=new Sprint(start, end,"food delivery api coding sprint");
        this.sprints.add(sprintObj);
        return sprintObj;
    }
    public Task createTask(int sprintId){
        Task taskObj=new Task("database integration","Creating a sql database and then integrate into development environment");
        for(Sprint sp:this.sprints){
            if(sp.getId()==sprintId){
                sp.addTask(taskObj);
                this.tasks.add(taskObj);
                break;
            }
        }
        return taskObj;
    }
    public void createTask(){
        Task taskObj=new Task("database integration","Creating a sql database and then integrate into development environment");
        this.tasks.add(taskObj);
    }
    public Task createTask(Enum taskType){
        Task taskObj=new Task(taskType);
        this.tasks.add(taskObj);
        return taskObj;
    }

}
class Sprint{
    private int start;
    private int Id;
    private static int IdCounter=100;
    private int end;
    private List<Task> tasks;
    private String name;
    Sprint(int start, int end,String name){
        this.start=start;
        this.end=end;
        this.Id=IdCounter++;
        this.tasks=new ArrayList<>();
    }
    public int getId(){
        return this.Id;
    }
    public void addTask(Task task){
        this.tasks.add(task);
    }
    public void setName(String name){
        this.name=name;
    }
    public int getStart(){
        return this.start;
    }
    public int getEnd(){
        return this.end;
    }
    public void printDetails(){
        System.out.println("The sprit named"+name+" has startdate"+this.start+" to the end date"+this.end);

        for(Task currTask:this.tasks){
            System.out.println(currTask.toString());
        }
    }
    public List<Task> getTasks(){
        return this.tasks;
    }

    public boolean removeTask(int taskId){
        int index=-1;
        for(int i=0;i<this.tasks.size();i++){
            if(this.tasks.get(i).getId()==taskId){
                index=i;
                break;
            }
        }
        if(index!=-1){
            this.tasks.remove(index);
            return true;
        }
        return false;
    }

}

class Task{
    private String title;
    private int id;
    private static int IdCounter;
    private String description;
    private Enum status;
    private Enum type;
    private User handler;
    private List<SubTask> subTasks;
    Task(String title,String description,Enum status, Enum type, User handler){
        this.id=this.IdCounter++;
        this.title=title;
        this.description=description;
        this.status=status;
        this.type=type;
        this.handler=handler;
    }
    Task(String title,String description){
        this.id=IdCounter++;
        this.title=title;
        this.description=description;
    }
    Task(Enum taskType){
        this.id=IdCounter++;
        this.type=taskType;
    }
    public int getId(){
        return this.id;
    }
    public String getDescription(){
        return this.description;
    }
    public User getHandler(){
        return this.handler;
    }
    public void setTaskType(Enum type){
        this.type=type;
    }
    public void setTaskStatus(Enum status){
        this.status=status;
    }
    public void setHandler(User handler){
        this.handler=handler;
    }
    public Enum getStatus(){
        return this.status;
    }
    public Enum getType(){
        return this.type;
    }
    public String toString(){
        return "The task is of id"+this.id+", description"+this.description+", handler"+this.handler+", type"+this.type+", current status "+this.status;
    }

}
class SubTask{
    private int Id;
    private String description;
    private static int IdCounter;
    private Enum type;
    private User handler;
    private Enum status;

    SubTask(){
        this.Id=this.IdCounter++;
    }
    public int getId(){
        return this.Id;
    }
    public String getDescription(){
        return this.description;
    }
    public User getHandler(){
        return this.handler;
    }
    public void setTaskType(Enum type){
        this.type=type;
    }
    public void setTaskStatus(Enum status){
        this.status=status;
    }
    public void setHandler(User handler){
        this.handler=handler;
    }
    public Enum getStatus(){
        return this.status;
    }
    public Enum getType(){
        return this.type;
    }
    public String toString(){
        return "The task is of id"+this.Id+", description"+this.description+", handler"+this.handler+", type"+this.type+", current status "+this.status;
    }
}