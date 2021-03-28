<%@page import="pt.core.seguranca.aGov.Scopes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	//caso j� tenha passado por aqui...
	if(null != session.getAttribute("autenticacaoGOV_state")) session.removeAttribute("autenticacaoGOV_state"); 
	//gerar um Identificador �nico universal (UUID)
	final String state = java.util.UUID.randomUUID().toString();
	//guarda-lo na variavel de sess�o, esta a��o � fundamental para prote��o de falsifica��o de solicita��o entre sites (CSRF)
	session.setAttribute("autenticacaoGOV_state", state);
	
	//n�mero fornecido pela AMA
	String clientID = "123";

	String atributos =  
		Scopes.Apelido.toString() + " " +
		//Scopes.CodigoPostal3.toString() + " " +
		//Scopes.CodigoPostal4.toString() + " " +
		Scopes.DataEmissao.toString() + " " +
		Scopes.DataNascimento.toString() + " " +
		//Scopes.DataValidade.toString() + " " +	//-> ERRO
		//Scopes.Foto.toString() + " " +			//-> ERRO
		Scopes.Idade.toString() + " " +
		Scopes.IdentificacaoCivil.toString() + " " +
		Scopes.IdentificacaoFiscal.toString() + " " +
		Scopes.IdentificacaoSegSocial.toString() + " " +
		Scopes.IdentificacaoServicoNacionalSaude.toString() + " " +
		//Scopes.LocalidadePostal.toString() + " " +
		Scopes.Nacionalidade.toString() + " " +
		Scopes.NomeCompleto.toString() + " " +
		Scopes.NomeProprio.toString();

	//ender�o de callback ap�s atentica��o aGov
	//dever� ser um dns
	// n�o pode ser localhost ou IP (nota: pode-se "martelar" os hosts)
	String callback = "http://dsv.dominio.pt/Auth/callback.jsp";


//System.out.println("atributos: " + atributos);
//System.out.println("state: " + state);
%>
<!DOCTYPE html>
<html lang="pt-pt">
<head>
	<title>DGEG - Autentica��o Gov</title>
	<meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/x-icon" href="https://javaservletstaticfiles.z22.web.core.windows.net/favicon.ico">
    <script src='//code.jquery.com/jquery-1.11.2.min.js'></script>
    <script type="text/javascript">
    var queryStringData = {
			response_type : "token",
			client_id : "<%=clientID%>",
			redirect_uri : "<%=callback%>",
			scope : "<%=atributos%>",
			state : "<%=state%>"
      	};
		var url = "https://preprod.autenticacao.gov.pt/OAuth/AskAuthorization?" + jQuery.param(queryStringData);
console.log(url);
      	window.location.replace(url);
    </script>
</head>
<body>

</body>
</html>