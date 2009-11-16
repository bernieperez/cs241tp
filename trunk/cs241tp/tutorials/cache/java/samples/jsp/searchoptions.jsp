<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Test Page</title>
	<%@ taglib uri="/cache" prefix="cache" %>
  </head>
	
	<body>
	  <h1>Test Page</h1>

	  <cache:connect namespace="SAMPLES" user="_SYSTEM" password="SYS"/>
		
		<h3>Select History frame:</h3>
		<cache:history table="Sample.Employee"/>
		  <hr>

	  
		  There are currently 
		  <cache:count table="Sample.Employee" useHistory = "true" /> 
			people in table.

			<p>

			<form method = "GET" name = "search" action = "search.jsp" 
			  target="_top">

			Home City:
			<cache:list table="Sample.Employee" property="Home_City" size = "10"
			  useHistory = "true" multiple = "true">
			</cache:list>

			Home ZIP:
			<cache:list table="Sample.Employee" property="Home_Zip" size = "10"
			  useHistory = "true" multiple = "true">
			</cache:list>

			Home State:
			<cache:list table="Sample.Employee" property="Home_State" size = "10"
			  useHistory = "true" multiple = "true">
			</cache:list>

			Company:
			<cache:crosslist table="Sample.Employee" property="Company.Name" 
			  size = "10" useHistory = "true" multiple = "true">
			</cache:crosslist>
			
			Office City:
			<cache:list table="Sample.Employee" property="Office_City" size = "10"
			  useHistory = "true" multiple = "true">
			</cache:list>

			DOB:
			<cache:list table="Sample.Employee" property="DOB" size = "10"
			  useHistory = "true" multiple = "true">
			</cache:list>

				
				<input name="find" type="submit" value="Find Selected"  
					 align="middle"
					 style="font-family: Arial, Times New Roman; font-size: medium"> 
				<input type=reset value="Reset Selection">
			  </form>

    <hr>
    <address><a href="mailto:mbouzin@intersys.com">Michael A Bouzinier</a></address>
<!-- Created: Mon Jul 15 18:18:17 Eastern Daylight Time 2002 -->
<!-- hhmts start -->
Last modified: Sun Sep 14 22:16:57 Eastern Standard Time 2003
<!-- hhmts end -->
  </body>
</html>
