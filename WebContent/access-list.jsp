<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
  <h1>Role Management</h1>
        <h2>
         <a href="newaccess">Add New Userrole</a>
         &nbsp;&nbsp;&nbsp;
         <a href="listaccess">List All Usersrole</a>
          <br />
         <br />
         <a href="list">User Managment</a>
        </h2>
 </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Roles</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                
                <th>Actions</th>
            </tr>
            <c:forEach var="access" items="${listAccess}">
                <tr>
                    <td><c:out value="${access.id}" /></td>
                    <td><c:out value="${access.name}" /></td>                  
                   
                    <td>
                     <a href="editaccess?id=<c:out value='${access.id}' />">Edit</a>
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="deleteaccess?id=<c:out value='${access.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>