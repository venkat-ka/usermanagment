package com.techapp.usermanagement.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.techapp.usermanagement.model.Access;
import com.techapp.usermanagement.model.Userrole;
import com.techapp.usermanagement.util.HibernateUtil;

public class userAccessDao {
	 /**
     * Save Access
     * @param user
     */
    public void saveAccess(Access access) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the userrole object
            session.save(access);
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
     * Update User
     * @param user
     */
    public void updateUser(Userrole user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(user);
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
    public void deleteAccess(long id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            Access access = session.get(Access.class, id);
            if (access != null) {
                session.delete(access);
                System.out.println("Access is deleted");
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
    public Access getAccess(long id) {

        Transaction transaction = null;
        Access userac = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            userac = session.get(Access.class, id);
            
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return userac;
    }
    
    
    /**
     * Get Access By userID
     * @param id
     * @return
     */
    public Long getAccessByUserId(long id) {

        Transaction transaction = null;
        Long userac = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
          //Class<Access>  useracc = Access.class;
           // Userrole userrole = (Userrole) session.get(Userrole.class, id); 
          //  Set<Userrole> usr = (Set<Userrole>) new Userrole();
            
//           usr.getId()
//            Access accData = (Access) session.get(Access.class, accesid);
//            Set<Userrole> useracc = (Set<Userrole>) session.get(Userrole.class, id);
//            accData.setUserRole(useracc);
            
            Query getAccessid =  session.createQuery("select a.id from Access a inner join a.userRole u where u.id=:userid");
            getAccessid.setParameter("userid", id);
            List l = getAccessid.list();
            Iterator it=l.iterator();
            while(it.hasNext())
            {
                Object rows = it.next();
                userac = (Long) rows;
                System.out.println(rows+ " -d- ");
            }
           
//          Userrole userrole = (Userrole) session.get(Userrole.class, id);
//          userac.setId(id);
            		//session.get(Access.class, id);
            
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
        	System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return userac;
    }
    
    public Access updateAccessToUser(long id, long accesid) {

        Transaction transaction = null;
        Access userac = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            
            System.out.println(id+"cccccccccc vvv");
            
            Access acces = (Access) session.get(Access.class, accesid);
            
            Userrole usrl = (Userrole) session.get(Userrole.class, id);
            
            acces.addUserrole(usrl);
            session.update(acces);
            /*
             * Userrole udata = (Userrole) session.get(Userrole.class, id);
            
            udata.setId(id);
            
            HashSet<Userrole> us = new HashSet<>();
            us.add(udata);
            Access accData = (Access) session.get(Access.class, accesid);
            accData.setUserRole(us);
            
             session.update(accData);
     
           
            Query qry = session.createQuery("update Userrole u set u.acc_id = :aid where u.id=:uid");
            qry.setParameter("uid", id);
            qry.setParameter("aid", accesid);
            qry.executeUpdate();
            */
            
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
        	System.out.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return userac;
    }
    /**
     * Get all Users
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Access> getallAccess() {
    	  
        Transaction transaction = null;
        List <Access> listOfAccess = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            
            listOfAccess = session.createQuery("from Access").getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
        	
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfAccess;
    }
    
    
}
