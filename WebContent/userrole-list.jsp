<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
 <title>Userrole Management Application</title>
</head>
<body>
 <center>
  <h1>User Management</h1>
        <h2>
         <a href="new">Add New User</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list">List All Users</a>
         <br />
         <br />
         <a href="listaccess">Role Managment</a>
        </h2>
 </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Role</th>
                
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td>
                    
                    <c:out value="${user[2]}" />
                    
                    </td>
                    <td><c:out value="${user[1]}" /></td>
                    <td><a href="editaccesstouser?id=<c:out value="${user[2]}" />">
                    	
                    	<c:if test="${empty user[3]}">
							    Assign Role Access
							</c:if>
							
							<c:if test="${not empty user[3]}">
							   <c:out value="${user[0]}" />
							</c:if>
                    </a>
                    </td>
                   
                    <td>
                     <a href="edit?id=<c:out value='${user[2]}' />">Edit</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="delete?id=<c:out value='${user[2]}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>