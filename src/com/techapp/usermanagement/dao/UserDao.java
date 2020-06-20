package com.techapp.usermanagement.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.techapp.usermanagement.model.Access;
import com.techapp.usermanagement.model.Userrole;
import com.techapp.usermanagement.util.HibernateUtil;

public class UserDao {
	 /**
     * Save User
     * @param user
     */
    public void saveUser(Userrole user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
        	System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    /**
     * Update User
     * @param user
     */
    public void updateUser(long id, Userrole usrdtls) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            Userrole userrole = (Userrole) session.get(Userrole.class, id);
            userrole.setDesignation(usrdtls.getDesignation());
            userrole.setEmail(usrdtls.getEmail());
            userrole.setName(usrdtls.getName());
            session.update(userrole);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    /**
     * Delete User
     * @param id
     */
    public void deleteUser(long id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            Userrole user = session.get(Userrole.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("user is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } 
    }
    
    /**
     * Get User By ID
     * @param id
     * @return
     */
    public Userrole getUser(long id) {
    	System.out.println("ccc");
    	System.out.println(id);
    	System.out.println("<===");
        Transaction transaction = null;
        Userrole user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.get(Userrole.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
        	System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }
    
    
    public void checkCu() {
    	
    	SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query qry= session.createQuery("select c.customerName, c.customerCity, i.itemName,i.price from Customer c "
                + "left join c.items i");
        List l = qry.list();
        Iterator it=l.iterator();
        while(it.hasNext())
        {
            Object rows[] = (Object[])it.next();
            System.out.println(rows[0]+ " -- " +rows[1] + "--"+rows[2]+"--"+rows[3]);
        }
        session.clear();
        session.close();
    }
    
    
    /**
     * Get all Users
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Userrole> getAllUser() {
    	checkCu();
    	//Session session = null;
        Transaction transaction = null;
        List <Userrole> listOfUser = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

           Query listO =  session.createQuery("select a.name as aname, u.name as uname, u.id as uid, a.id as aid from Access a right join a.userRole u");

           listOfUser = listO.getResultList();

    
            transaction.commit();
        } catch (HibernateException e) {
        	System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }
    
    
}
