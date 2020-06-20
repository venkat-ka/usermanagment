package com.techapp.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techapp.usermanagement.dao.UserDao;
import com.techapp.usermanagement.dao.userAccessDao;
import com.techapp.usermanagement.model.Access;
import com.techapp.usermanagement.model.Userrole;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	private UserDao userDao;  
    
	private userAccessDao useraccessDao;
	
	public void init() {
        userDao = new UserDao();
        useraccessDao = new userAccessDao();
    }
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
        try {
            switch (action) {
                case "/new":
                	showNewForm(request, response);
                    break;
                case "/insert":
                	
                	insertUser(request, response);
                    break;
                case "/delete":
                	
                	deleteUser(request, response);
                    break;
                case "/edit":
                	
                	showEditForm(request, response);
                    break;
                case "/update":
                	 updateUser(request, response);
                	
                    break;
                case "/newaccess":
                	showNewFormaccess(request, response);
                    break;
                case "/insertaccess":
                	
                	insertAccess(request, response);
                    break;
                case "/deleteaccess":
                	
                	deleteAccess(request, response);
                    break;
                case "/listaccess":
                	listAccess(request, response);
                	
                    break;
                case "/editaccesstouser":
                	editaccesstouser(request, response);
                	
                    break;    
                case "/updateaccesstouser":
                	updateAccessToUser(request, response);
                	
                    break;    
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        
	
	}
	
	 private void listUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException, ServletException {

			       List<Userrole> listUser = userDao.getAllUser();
			       Iterator<Userrole> listUse=listUser.iterator();
			      			      
			        request.setAttribute("listUser", listUse);	
			        
			        RequestDispatcher dispatcher = request.getRequestDispatcher("userrole-list.jsp");
			        dispatcher.forward(request, response);
			    }

	 
	 
	 private void listAccess(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		 	List < Access > listaccess = useraccessDao.getallAccess();
	    	request.setAttribute("listAccess", listaccess);			        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("access-list.jsp");
	        dispatcher.forward(request, response);
	 }
	 /* Insert */
	 private void insertUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        String name = request.getParameter("name");
			        String email = request.getParameter("email");
			        String designation = request.getParameter("designation");

			        Userrole newUser = new Userrole(name,email,designation);
			        userDao.saveUser(newUser);
			        response.sendRedirect("list");
			    }
	 private void updateAccessToUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException{
		 

	        Long accesid = (long) Double.parseDouble(request.getParameter("access"));
 	        Long userid = (long) Double.parseDouble(request.getParameter("userid"));
 	        useraccessDao.updateAccessToUser(userid, accesid);
 	        response.sendRedirect("list");
	 }
	 
	 private void insertAccess(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		 	

	        String[] access = request.getParameterValues("access");
	        String name = request.getParameter("name");
	        Access newAccess = new Access(name, Arrays.toString(access));
	        useraccessDao.saveAccess(newAccess);
	        
	        response.sendRedirect("list");
	 }
	 /*End*/
	 private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
		
			        long id = Integer.parseInt(request.getParameter("id"));
			        userDao.deleteUser(id);
			        response.sendRedirect("list");
			    }
	private void deleteAccess(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		long id = Integer.parseInt(request.getParameter("id"));
        useraccessDao.deleteAccess(id);
        response.sendRedirect("listaccess");
	 }
	
	// New Usermanagment 
	 private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {

			        RequestDispatcher dispatcher = request.getRequestDispatcher("userdetails-form.jsp");
			        dispatcher.forward(request, response);
			    }
	 
	 // Access Record new
	 private void showNewFormaccess(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("user-form-access.jsp");
	        dispatcher.forward(request, response);
	 }	 
	 /* End */
	 
	 private void editaccesstouser(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, ServletException, IOException {
		 
			        long userid = Integer.parseInt(request.getParameter("id"));
			        Userrole existingUser = userDao.getUser(userid);
			      //  Access accessuser = useraccessDao.getAccess(id);
			        		
			        // here we nee access id but its userrole id
			        Long existUser = useraccessDao.getAccessByUserId(userid);
			       // existUser have to come accessid which in user table
			        List < Access > listaccess = useraccessDao.getallAccess();
			        
			        request.setAttribute("user", existingUser);
			        request.setAttribute("listAccess", listaccess);
			        request.setAttribute("usersaccid", existUser);
			        
			       
			        RequestDispatcher dispatcher = request.getRequestDispatcher("user-assign-access.jsp");
			        
			        dispatcher.forward(request, response);

			    }
	 private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, ServletException, IOException {
			        int id = Integer.parseInt(request.getParameter("id"));
			        Userrole existingUser = userDao.getUser(id);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("userdetails-form.jsp");
			        request.setAttribute("user", existingUser);
			        dispatcher.forward(request, response);

			    }
	 
	 private void updateUser(HttpServletRequest request, HttpServletResponse response)
			    throws SQLException, IOException {
			        long id = Integer.parseInt(request.getParameter("id"));
			        String name = request.getParameter("name");
			        String email = request.getParameter("email");
			        String designation = request.getParameter("designation");

			        Userrole user = new Userrole(id, name, email, designation);
			        userDao.updateUser(id, user);
			        response.sendRedirect("list");
			    }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
