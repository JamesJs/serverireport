package br.edu.devmedia.servelet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.devmedia.conexao.ConexaoUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Servlet implementation class RelatorioServelet
 */
@WebServlet("/relatorio")
public class RelatorioServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathRelatorios = getServletContext()
				.getRealPath("/relatorios/")+"\\"; 
		Map parametros = new HashMap<>();
		parametros.put("SUBREPORT_DIR",pathRelatorios + "\\");
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(pathRelatorios + 
					"charts.jasper", parametros,ConexaoUtil.getConexao());
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} catch (ClassNotFoundException | JRException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
