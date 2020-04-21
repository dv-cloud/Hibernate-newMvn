package dao;

import entities.Discipline;
import entities.User;
import enums.TaskStatus;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.List;

public class ImplementationDAO implements ToFromDB {
    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public List<User> getUserByRoleName(){
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("from User u join fetch u.roles r where r.name = 'Java Engineer' ");
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }


    public List<User> getUserByDiscipline(String discipline){
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("From User u Join fetch u.discipline d Where d.name =: discipline" );
            query.setString("discipline", String.valueOf(discipline));
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public List<User> getUserByTaskStatus(){
        Session s = null;
        Transaction t = null;
        List<User> list = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("select t.user from Task t Where t.status = 'TODO' ");
            list = query.list();
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }

    public void changeTheHeadOfDiscipline(User user, Discipline discipline){
        Session s = null;
        Transaction t = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            discipline.setHeadOfDiscipline(user);
            s.update(discipline);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
    }

    public void deleteUser(User user){
        Session s = null;
        Transaction t = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            s.delete(user);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
    }
}
