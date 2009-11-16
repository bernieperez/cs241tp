<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>List found People</title>
	<%@ taglib uri="/cache" prefix="cache" %>
  </head>
	
	<body>
	  <h1>List found People</h1>

	  <cache:connect namespace="SAMPLES" user="_SYSTEM" password="SYS"/>
		
		<cache:history table="Sample.Employee"/>
		  <hr>
		<% String tableName = request.getParameter("classname");%>
	  
		  There are currently <cache:count table="Sample.Employee"
			useHistory = "true" /> elements in table.

			<p>

		  <form method = "GET" name = "view" action = "view.jsp">

			<cache:list table="Sample.Employee" property="ID" 
			size = "20" forceselection = "true" none = "false"
			useHistory = "true" name = "ID">
			</cache:list>
			
			<input name="find" type="submit" value="View Selected"  
				 align="middle"
				   style="font-family: Arial, Times New Roman; font-size: medium"> 
			<input type=reset value="Reset Selection">

			<input type="hidden" name="classname" value="Sample.Employee">
			<input type = "hidden" name = "origin" value = "listpeople.jsp">
			<input type = "hidden" name = "origintext" 
				   value = "List other found people">

		  </form>

			<hr>
			
			<a href="searchoptions.jsp" target="_top">To Search Options</a>
			<br>
			<a href="index.jsp" target="_top">Back to Home</a>
    <hr>
    <address><a href="mailto:mbouzin@intersys.com">Michael A Bouzinier</a></address>
<!-- Created: Mon Jul 15 18:18:17 Eastern Daylight Time 2002 -->
<!-- hhmts start -->
Last modified: Mon Sep 08 17:29:57 Eastern Standard Time 2003
<!-- hhmts end -->
  </body>
</html>
