<%@page import="javafx.scene.control.Alert"%>
<%@page import="java.util.concurrent.Delayed"%>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.sun.jndi.ldap.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    
            
       
        <%
          //String aux="ACTIVO";
          String usuario=request.getParameter("email");
          String contra=request.getParameter("pass");
          Class.forName("com.mysql.jdbc.Driver");
          
          try {
                //java.sql.ResultSet rs=null;
               java.sql.Connection miConexion= java.sql.DriverManager.getConnection("jdbc:mysql://127.0.0.1/modelogeneralfisi", "root", "");
               //java.sql.Statement stmt=null;
               //stmt=miConexion.createStatement();
               
               //rs=stmt.executeQuery("SELECT PERSONA_CODIGO,PERSONA_PASSWORD_SISTEMA FROM `modelogeneralfisi`.`persona` where PERSONA_CODIGO=? and PERSONA_PASSWORD_SISTEMA =?;");
               
               java.sql.PreparedStatement c_prep=miConexion.prepareStatement("SELECT PERSONA_CODIGO,PERSONA_PASSWORD_SISTEMA FROM `modelogeneralfisi`.`persona` where PERSONA_CODIGO=? and PERSONA_PASSWORD_SISTEMA =?;" );
               c_prep.setString(1,usuario);
               c_prep.setString(2, contra);
               
               java.sql.ResultSet miREsul=c_prep.executeQuery();
               
               if(miREsul.absolute(1)){
                    
                   response.sendRedirect("Gestion_Alumno.jsp");//redirecciona 
                    
                    session.setAttribute("sesion",usuario);//crea sesion
                   }
                else {
                   
                  response.sendRedirect("login.html");
                  
                   
                   
               }
               
              } catch (Exception e) {
                  out.print("ERROR");
                  out.println(e.getMessage());
              }



        %>