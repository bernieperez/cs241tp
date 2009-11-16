<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Cache JSP Samples</title>
	<%@ taglib uri="/cache" prefix="cache" %>
  </head>

  <body>
    <h1>Cache JSP Samples</h1>

	  <cache:connect namespace="SAMPLES" user="_SYSTEM" password="SYS"
		autoload="true"/>

		<h2>Search for People: </h2>

		<a href="searchoptions.jsp">Start Search </a>

		<hr>

	  <h2>View all: </h2>

	  <form method = "GET" name = "list" action = "listtable.jsp">
		
		<SELECT size="2" name="classname" title="Class" id="listtable">
		  <OPTION value="Sample.Person">People</OPTION>
		  <OPTION value="Sample.Employee">Employes</OPTION>
		  <OPTION value="Sample.Company">Companies</OPTION>
		</SELECT>	  
		
		<input name="list" type="submit" value="List">
		<input type=reset value="Reset">
	  </form>

	  <h2>Create new Object</h2>

	  <form method = "GET" name = "new" action = "new.jsp">
		
		<SELECT size="3" name="classname" title="Class" id="newobjclass">
		  <OPTION value="Sample.Person">Person</OPTION>
		  <OPTION value="Sample.Employee">Employee</OPTION>
		  <OPTION value="Sample.Company">Company</OPTION>
		  <OPTION value="Sample.Vendor">Vendor</OPTION>
		</SELECT>	  
		
		<input name="create" type="submit" value="Create">
		<input type=reset value="Reset">
	  </form>

	  <h2>View an existing Object by its ID</h2>

	  <form method = "GET" name = "view" action = "view.jsp">
		
		<SELECT size="2" name="classname" title="Class" id="viewobjclass">
		  <OPTION value="Sample.Person">People</OPTION>
		  <OPTION value="Sample.Company">Companies</OPTION>
		</SELECT>	  
		
		<input name="id" type="text" value="">

		<input name="view" type="submit" value="View">
		<input type=reset value="Reset">
	  </form>

    <hr>
		<a href="disconnect.jsp"> Close Connection to Database </a>
    <hr>
    <address><a href="mailto:mbouzin@intersys.com">Michael A Bouzinier</a></address>
<!-- Created: Mon Aug 26 01:37:22 Eastern Daylight Time 2002 -->
<!-- hhmts start -->
Last modified: Thu Nov 14 17:22:57 Eastern Standard Time 2002
<!-- hhmts end -->
  </body>
</html>
