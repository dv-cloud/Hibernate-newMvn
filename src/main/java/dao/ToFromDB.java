package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.List;

public interface ToFromDB<E> {

    default void toDB(List<E> list){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session s = null;
        Transaction t = null;

        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            for (E c : list)
                s.save(c);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
    }

    default List<E> FomDB(E e){
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session s = null;
        Transaction t = null;
        List<E> list = null;
        s = sessionFactory.openSession();
        t = s.beginTransaction();
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Query query  = s.createQuery("from "+e.getClass().getCanonicalName());
            list = query.list();
            t.commit();
        } catch (Exception ex) {
            t.rollback();
        } finally {
            if (s != null)
                s.close();
        }
        return list;
    }
}
