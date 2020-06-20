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
  <h1>Roles Management</h1>
        <h2>
       
        
         <a href="list">List All Roles</a>
         <br />
        
        </h2>
 </center>
    <div align="center">
  <c:if test="${access != null}">
   <form action="updateaccess" method="post">
        </c:if>
        <c:if test="${access == null}">
   <form action="insertaccess" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${access != null}">
               Edit Role
              </c:if>
              <c:if test="${access == null}">
               Add New Roles
              </c:if>
             </h2>
            </caption>
          <c:if test="${access != null}">
           <input type="hidden" name="id" value="<c:out value='${access.id}' />" />
          </c:if>            
            <tr>
                <th>Role Name: </th>
                <td>
                 <input type="text" name="name" size="45"
                   value="<c:out value='${access.name}' />"
                   required="true"
                  />
                </td>
            </tr>
            <tr>
                <th> Role Access: </th>
                <td>
                <input type="checkbox" name="access" size="45"
                   value="<c:out value='READ' />"                  
                 />
                 READ  <br />
                 <input type="checkbox" name="access" size="45"
                   value="<c:out value='WRITE' />"                  
                 /> 
                 WRITE <br />
                 <input type="checkbox" name="access" size="45"
                   value="<c:out value='DELETE' />"                  
                 /> 
                 DELETE <br />
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