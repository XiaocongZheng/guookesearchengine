<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="Test.res" />


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Search Result</title>
    
    <style>
	#search{
	width:78px;
	height:28px;
	font:14px "宋体"
	}
	
	#textArea{
	width:300px;
	height:30px;
	font:14px "宋体"
	}
	</style>

  </head>
  
  <body>
    
    <%	
		String keyword = new String(request.getParameter("keyword").getBytes("ISO-8859-1"),"UTF-8"); 	
	%>
	
	
    <form action="search.jsp" name="search" method="get">
	<table border="0" height="30px" width="450px" align="center">		
		<tr>
			<td><img src="dySE-logo.JPG" /></td>
			<td width ="66%"><input name="keyword" type="text" 
				maxlength="100" id="textArea" value=<%=keyword%>></td>
			<td height="29" align="center"><input type="submit" value="search" id = "search"></td>
		</tr>
	</table>
	</form>
	
	
	
	<%  
		res resp = new res();
		resp.searchresultforfile(keyword);
		
		//System.out.println("返回结果如下：");
		
		//{
	%>	
			<h2>The file which contains most frequency of the keyword is <a href=<%=resp.filename%>target="_blank"><%=resp.filename%></a></h2>
			<p>The search keyword is <%=resp.result%><p>
			<p>The frequency of this keyword in this file is <%=resp.fr%> &nbsp;&nbsp;&nbsp; The searching time is <%=resp.getdate%> ms<p>
	<%  		
		//}
	%>
    	
  </body>
</html>