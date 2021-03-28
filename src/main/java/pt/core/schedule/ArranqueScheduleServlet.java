package pt.core.schedule;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import pt.core.log.LogAutenticacaoGov;

@WebServlet(name="ArranqueScheduleServlet", urlPatterns="/ArranqueScheduleServlet", loadOnStartup=1)
public class ArranqueScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		// inicialização de Logs
		new LogAutenticacaoGov().init();
	}
}