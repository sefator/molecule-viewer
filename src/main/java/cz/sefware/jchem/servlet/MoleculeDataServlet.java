package cz.sefware.jchem.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import cz.sefware.jchem.service.SimpleMoleculeService;

/**
 * Servlet implementation class MoleculeDataServlet
 */
@WebServlet(urlPatterns = "/MoleculeData")
public class MoleculeDataServlet extends GenericServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SimpleMoleculeService service;

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

	}

}
