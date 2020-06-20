<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<center>
  <h1>User Management</h1>
        <h2>
         <a href="newaccess">Add New User Role</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list">List All Users and role</a>
         
        </h2>
 </center>
    <div align="center">
  <c:if test="${user != null}">
   <form action="updateaccesstouser" method="post">
        </c:if>
        <c:if test="${user == null}">
   <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${user != null}">
               Update User Role
              </c:if>
              <c:if test="${user == null}">
               Add New User
              </c:if>
             </h2>
            </caption>
                   
            <tr>
                <th>User Name: </th>
                <td>
                 <input type="text" name="name" size="45"
                   value="<c:out value='${user.name}' />"
                   required="true"
                  />
                  <input type="hidden" name="userid" value="<c:out value='${user.id}' />">
             	
                </td>
            </tr>
            
            <tr>
            	<td colspan="2" align="center">
            	
            		<select name="access" required>
            			<option value="0">Select User Access</option>
					    <c:forEach items="${listAccess}" var="access">
					        <option value="${access.id}" ${access.id ==usersaccid ? 'selected="selected"' : ''} > ${access.name}</option>
					    </c:forEach>
					</select>
            	</td>
            </tr>
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Save" />
             </td>
            </tr>
        </table>
        </form>
    </div> 
</body>
</html>