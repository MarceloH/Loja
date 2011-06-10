<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page import="domainModel.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Criar Venda</title>
</head>
<body>
<%

List clientes = (List) request.getAttribute("clientes");
if(clientes != null){
%>
<form method="post" action="/Loja/Venda">
<label for="clientes">Clientes: </label>
<select nome="clientes">
<%
		Iterator it = clientes.iterator();
		while(it.hasNext()){
	   	Cliente c = (Cliente)it.next();
%>

<option value="<% c.getId(); %></option>"><%if(c !=null){ out.print(c.getNome());}%> </option>
<% 
		}
%>

</select>
<%
}
%>
<input type="submit" value="Criar" />
</body>
</html>