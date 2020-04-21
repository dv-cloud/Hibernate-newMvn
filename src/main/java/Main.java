import dao.ImplementationDAO;
import dao.ToFromDB;
import entities.Discipline;
import entities.Role;
import entities.Task;
import entities.User;
import enums.TaskStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.*;


public class Main {

    private static ImplementationDAO dao = new ImplementationDAO();

    public static void main(String[] args) {
        List<Role> roles = roleFactory();
        dao.toDB(roles);
        List<Task> tasks = taskFactory();
        dao.toDB(tasks);
        List<User> users = userFactory(tasks, roles);
        dao.toDB(users);
        List<Discipline> disciplines = disciplineFactory(users);
        dao.toDB(disciplines);


        printSeparator();
        List<User> list= dao.getUserByRoleName();
        System.out.println("User with role Java Engineer");
        for (User e : list){
            System.out.println(e);
        }

        printSeparator();
        List<User> list2= dao.getUserByDiscipline("Applications Management");
        System.out.println("User in Applications Management");
        for (User e : list2){
            System.out.println(e);
        }

        printSeparator();
        List<User> list3= dao.getUserByTaskStatus();
        System.out.println("User with TODO tasks");
        for (User e : list3){
            System.out.println(e);
        }


        dao.changeTheHeadOfDiscipline(users.get(2), disciplines.get(0));

        dao.deleteUser(users.get(0));


    }

    public static List<Role> roleFactory(){
        List<Role> list = new ArrayList<>();
        list.add(new Role("Scrum Master"));
        list.add(new Role("Java Engineer"));
        list.add(new Role("DevOps"));
        list.add(new Role("Analyst"));
        return list;
    }

    public static List<Task> taskFactory(){
        List<Task> list = new ArrayList<>();
        list.add(new Task("Create authentication", "Detailed authentication process", new Date(),
                new Date(), TaskStatus.TODO));
        list.add(new Task("Create authorization", "Detailed authorization process", new Date(),
                new Date(), TaskStatus.TODO));
        list.add(new Task("Create login credentials process", "Detailed login process", new Date(),
                new Date(), TaskStatus.TODO));
        return list;
    }

    public static List<User> userFactory(List<Task> tasks, List<Role> roles){
        List<User> list = new ArrayList<>();
        Set<Task> taskList = new HashSet<>(tasks);
        list.add(new User("John", "Doe", "john.doe@mail.en", "johnny", new Date(), true,
                new HashSet<Role>(Arrays.asList(roles.get(0),roles.get(1))), taskList));
        list.add(new User("Jane", "Doe", "jane.doe@mail.en", "jenny", new Date(), true,
                new HashSet<Role>(Arrays.asList(roles.get(0),roles.get(1))), taskList));
        list.add(new User("Terry", "Fudge", "terry.fudge@mail.en", "tudge", new Date(), true,
                new HashSet<Role>(Arrays.asList(roles.get(0),roles.get(2))), taskList));
        list.add(new User("Sam", "Dover", "sam.dover@mail.en", "somer", new Date(), true,
                new HashSet<Role>(Arrays.asList(roles.get(0),roles.get(1))), taskList));
        return list;
    }

    public static List<Discipline> disciplineFactory(List<User> userList) {
        List<Discipline> list = new ArrayList<>();
        Set<User> users = new HashSet<>(userList);
        list.add(new Discipline("Applications Management", users, userList.get(3)));

        return list;
    }

    public static void printSeparator(){
        System.out.println("---------------------------------------------------------------------");
    }

}
