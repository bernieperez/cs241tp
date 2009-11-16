<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Table Content</title>
	<%@ taglib uri="/cache" prefix="cache" %>
  </head>
	
	<body>
		<% String tableName = request.getParameter("classname");%>
	  <h1><%=tableName%> Content</h1>

	  <cache:connect namespace="SAMPLES" user="_SYSTEM" password="SYS"/>
		
		<h3>Elements of <%=tableName%>:</h3>
	  
		  There are currently <cache:count table="FromRequest"
			useHistory = "false" /> elements in table.

			<p>

		  <form method = "GET" name = "view" action = "view.jsp">

		  <cache:list table="<%=tableName%>" property="ID" size = "20"
			useHistory = "false" forceselection = "true" none = "false"
			>
		  </cache:list>
			
		  <input name="find" type="submit" value="View Selected"  
				 align="middle"
				 style="font-family: Arial, Times New Roman; font-size: medium"> 
		  <input type=reset value="Reset Selection">
		  <input type="hidden" name="classname" value="<%=tableName%>">
		</form>

    <hr>
    <address><a href="mailto:mbouzin@intersys.com">Michael A Bouzinier</a></address>
<!-- Created: Mon Jul 15 18:18:17 Eastern Daylight Time 2002 -->
<!-- hhmts start -->
Last modified: Mon Sep 08 17:47:06 Eastern Standard Time 2003
<!-- hhmts end -->
  </body>
</html>
