<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="domainModel.Produto" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Listagem de Produtos</title>
</head>
<body>
    
<%
    List produtos = (List) request.getAttribute("produtos");
    if(produtos!= null) {
	   %>
	   <table>
	   <tr><td>ID</td><td>Nome</td><td>Preço</td></tr>
	   <%
	   Iterator it = produtos.iterator();
	   while(it.hasNext()){
		   Produto c = (Produto)it.next();
		   %>
		   <tr>
		   		<td><%=c.getCodigo() %></td>
		   		<td><%=c.getNome() %></td>
		   		<td><%=c.getPrecoProduto()%></td>
		   		<td><a href="/Loja/Produtos?edit=<%=c.getCodigo() %>">Editar</a></td>
		   		<td><a href="/Loja/Produtos?del=<%=c.getCodigo() %>">Apagar</a></td>
		   </tr>
		 <% 
	   }
	   %>
	   </table>
	   <a href="/Loja/Produtos?edit=new">Novo Produto</a>
	<%
	}else
			
    %>
</body>
</html>