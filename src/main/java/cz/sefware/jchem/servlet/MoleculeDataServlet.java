package cz.sefware.jchem.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chemaxon.formats.MolExporter;
import chemaxon.struc.Molecule;
import cz.sefware.jchem.model.MoleculeInfo;
import cz.sefware.jchem.service.MoleculeDatastore;
import cz.sefware.jchem.service.SimpleMoleculeDatastore;

/**
 * Simple servlet for handling molecule requests. Returns JSON serialized list
 * of Molecule info for requests without 'id' parameter or Molecule image
 * presentation if id is given.
 * 
 * @author jg
 */
@WebServlet(urlPatterns = "/MoleculeData", name="MoleculeDataServlet", initParams = { @WebInitParam(name = "baseDirectory", value = SimpleMoleculeDatastore.DEFAULT_DIRECTORY) })
public class MoleculeDataServlet extends GenericServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private MoleculeDatastore store;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MoleculeDataServlet.class);

	@Override
	public void init() throws ServletException {
		store = new SimpleMoleculeDatastore(getInitParameter("baseDirectory"));
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String requestId = request.getParameter("id");
		if (requestId == null || requestId.isEmpty()) {
			LOGGER.debug("Received request for file list.");
			List<MoleculeInfo> infos = store.getMoleculeInfos();
			// serialize list to JSON and return to client
			ObjectMapper om = new ObjectMapper();
			om.writeValue(response.getOutputStream(), infos);
			LOGGER.debug("Returned {} items", infos.size());
		} else if (requestId != null) {
			LOGGER.debug("Received request for molecule image.");
			// TODO: handle variable image size
			Long id = Long.valueOf(requestId);
			Molecule molecule = store.getMolecule(id);
			response.setContentType("image/jpeg");
			// TODO: use new API for image output
			IOUtils.write(MolExporter.exportToBinFormat(molecule,
					"jpeg:setcolors,w800,h600,Q96"), response.getOutputStream());
			LOGGER.debug("Successfuly returned molecule image.");
		}

	}

}
