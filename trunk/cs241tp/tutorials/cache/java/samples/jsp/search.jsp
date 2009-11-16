<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Searching...</title>
  <%@ taglib uri="/cache" prefix="cache" %>
  </head>
		  <cache:pushview table="Sample.Employee"/>
	  <frameset rows="50%,50%" frameborder="YES" border="2" framespacing="0"> 
		<frame name="SearchPanel" scrolling="YES" src="searchoptions.jsp">
		  <frame name="ListOfPeople" scrolling="YES" src="listpeople.jsp">
	  </frameset>
	  <noframes>
		<body>
			
			<a href="listpeople.jsp">List Results</a> 
			
			Or 
			
			<a href="searchoptions.jsp">Search in Results</a> 
			
			
			<hr>
			<address><a href="mailto:mbouzin@intersys.com">Michael A Bouzinier</a></address>
			<!-- Created: Wed Jul 17 20:49:08 Eastern Daylight Time 2002 -->
			<!-- hhmts start -->
Last modified: Sun Sep 14 22:16:43 Eastern Standard Time 2003
<!-- hhmts end -->
		</body>
	  </noframes>
</html>
