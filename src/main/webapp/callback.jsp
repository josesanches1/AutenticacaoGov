<%@page import="pt.core.seguranca.aGov.Scopes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	/*
	NOTA:
		O callback deverá ser uma página html/jsp
		A resposta do access_token | state | token_type | expires_in é feito através de um fragmento na URL.
		isto significa é impossivel ler esta informação no servidor aplicacional.
		
		A implimentação de "grant implicit" em oauth2 implica o envio de fragmento na URL
	*/
%>

<!DOCTYPE html>
<html lang="pt-pt">
<head>
	<title>Autenticação Gov - callback</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/x-icon" href="https://javaservletstaticfiles.z22.web.core.windows.net/favicon.ico">
    <script src='//code.jquery.com/jquery-1.11.2.min.js'></script>
</head>
<body>
<script>
    function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\#&]" + name + "=([^&#]*)"),
          results = regex.exec(location.hash);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }

    if(window.location.hash) {
        console.log(location.hash);
        
        var form = $("<form action='Callback' method='post' enctype='application/x-www-form-urlencoded'></form>");
        form.append("<input type='hidden' name='token' value='" + getParameterByName('access_token') + "'>");
        form.append("<input type='hidden' name='state' value='" + getParameterByName('state') + "'>");
        form.append("<input type='hidden' name='tokenType' value='" + getParameterByName('token_type') + "'>");
        form.append("<input type='hidden' name='expires' value='" + getParameterByName('expires_in') + "'>");
        
       
        //access_token=efa09639-4fb7-4b51-874f-a61eebbfcca2&token_type=bearer&expires_in=86400&state=
alert("Resposta: " + location.hash);
        
        //location.hash=''
        form.appendTo("body") .submit();
    }
    </script>
</body>
</html>