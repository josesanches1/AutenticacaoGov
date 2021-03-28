package pt.core.seguranca.aGov;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pt.core.seguranca.aGov.conexaoHTTP.SendGet;
import pt.core.seguranca.aGov.conexaoHTTP.SendPost;
import pt.core.seguranca.aGov.entity.AGovAtributos;
import pt.core.seguranca.aGov.entity.AGovResposta;

/**
 * Servlet implementation class Callback
 */
@WebServlet("/Callback")
public class Callback extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Callback() {
        super();
System.out.println("SERVLET: Callback");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("[Callback] doGet");
		getInfo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("[Callback] doPost");
		getInfo(request, response);
	}
	
	private void getInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ler o token do request
		String token = request.getParameter("token");
		
		//ler o state do request
		String state = request.getParameter("state");
		//obter a sessão
		HttpSession sessao = request.getSession(true);
		if(null != sessao.getAttribute("autenticacaoGOV_state")) {
			//O nome da vareiavel de sessão [autenticacaoGOV_state], foi definida na JSP de autenticação (com a opção autenticação Gov)
			
			//ler o state -> Identificador único universal (UUID) que deverá estar em sessão
			String _stateSession = (String) sessao.getAttribute("autenticacaoGOV_state");
			
			//Se não forem iguais (onque está em sessão) e a resposta da aGov -> problemas
			if(!_stateSession.equals(state)) {
				//TODO -> pagina de erro
				System.out.println("STATE UUID diferentes");
				setErro("O valor se state (UUID) e o devolvido pela aGov são diferentes", request, response);
				return;
			}
			
		}else {
			//algo correu mal....
			//TODO -> enviar para pagina de erro
			System.out.println("***** ERRO DE SESSÃO *****");
			setErro("Sem sessão ativa..... ", request, response);
			return;			
		}
		
		
		
		System.out.println(token);
		System.out.println(state);
		System.out.println(request.getParameter("tokenType"));
		System.out.println(request.getParameter("expires"));
		
		String endpoint = "https://preprod.autenticacao.gov.pt/OAuthResourceServer/Api/AttributeManager"; //"https://preprod.autenticacao.gov.pt/OAuth/Authorized";
		StringBuffer json = new StringBuffer();
		
		json
			.append("{")
			.append("\"token\":")	.append("\"" + token + "\"")
			.append("}");
		System.out.println("JSON: " + json);
		AGovResposta aGovresp = new SendPost().post(endpoint, json.toString());
		if(null != aGovresp) {
			String param = "?token=" + aGovresp.getToken() +"&authenticationContextId=" + aGovresp.getAuthenticationContextId();
			AGovAtributos _resp = new SendGet().get(endpoint + param);
			if(null != _resp) {
				respostaHTML(_resp, request, response);
				return;
			}
		}
	}
	
	private void respostaHTML(AGovAtributos _resp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/html;charset=UTF-8");

	    try (PrintWriter out = response.getWriter()) {

	    	out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Servlet</title>");            
	        out.println("</head>");
	        out.println("<body>");

	        out.println("<h1> Dados do cartão de cidadão</h1>");
	        
	        out.println("CP4: " + _resp.getCodigoPostal4() + "<br>");
	        out.println("CP3: " + _resp.getCodigoPostal3() + "<br>");
	        out.println("Localidade: "+_resp.getLocalidadePostal() + "<br>");

			out.println("Nome completo: " + _resp.getNomeCompleto() + "<br>");
			out.println("Nome prórpio: " + _resp.getNomeProprio() + "<br>");
			out.println("Apelido: " + _resp.getNomeApelido() + "<br>");
			out.println("Foto: " + _resp.getFoto() + "<br>");
			out.println("Data nascimento: " + _resp.getDataNascimento() + "<br>");
			out.println("Idade: " + _resp.getIdade() + "<br>");
			out.println("Nacionalidade: " + _resp.getNacionalidade() + "<br>");

			out.println("Número de identificação civil: " + _resp.getIdentificacaoCivil() + "<br>");
			out.println("Data emissão: " + _resp.getDataEmissao() + "<br>");
			out.println("Data validade: " + ": " + _resp.getDataValidade() + "<br>");

			out.println("NIF: " + _resp.getNIF() + "<br>");
			out.println("Número de identificação seg. social: " + _resp.getNISS() + "<br>");
			out.println("Número serviço nacional saúde: " + _resp.getNSNS() + "<br>");

	        out.println("</body>");
	        out.println("</html>");
	    }
	}
	
	private void setErro(String erro, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/html;charset=UTF-8");

	    try (PrintWriter out = response.getWriter()) {

	    	out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Servlet</title>");            
	        out.println("</head>");
	        out.println("<body>");

	        out.println("<h1> Erro cartão de cidadão</h1>");
	        
	        out.println(erro);

	        out.println("</body>");
	        out.println("</html>");
	    }
	}

}
