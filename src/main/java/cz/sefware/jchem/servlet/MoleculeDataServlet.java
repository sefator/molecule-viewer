package cz.sefware.jchem.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

import chemaxon.struc.Molecule;
import cz.sefware.jchem.model.MoleculeInfo;
import cz.sefware.jchem.service.SimpleMoleculeService;

/**
 * Servlet implementation class MoleculeDataServlet
 */
@WebServlet(urlPatterns = "/MoleculeData")
public class MoleculeDataServlet extends GenericServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SimpleMoleculeService service = new SimpleMoleculeService();

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String requestId = request.getParameter("id");
		if (requestId == null || requestId.isEmpty()) {
			List<MoleculeInfo> infos = service.getMoleculeInfos();
			ObjectMapper om = new ObjectMapper();
			om.writeValue(response.getOutputStream(), infos);
		} else {
			Long id = Long.valueOf(requestId);
			Molecule molecule = service.getMolecule(id);
			response.setContentType("image/jpeg");
			IOUtils.write(molecule.toBinFormat("jpeg:setcolors,w800,h600,Q96"),
					response.getOutputStream());
		}

	}

}
