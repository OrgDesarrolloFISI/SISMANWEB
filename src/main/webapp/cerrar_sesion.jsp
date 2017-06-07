<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ page session="true" %> 
<% 
    session.removeAttribute("sesion"); 
    session.invalidate(); 
    response.sendRedirect("login.html"); 
%>
    
</html>
