package cz.sefware.jchem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chemaxon.formats.MolFormatException;
import chemaxon.formats.MolImporter;
import chemaxon.struc.Molecule;
import cz.sefware.jchem.model.MoleculeInfo;
import cz.sefware.jchem.service.SimpleMoleculeService;

/**
 * Servlet implementation for handling file uploads of Molecule files.
 * 
 * @author jg
 * 
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = "/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = -2690570472811965423L;

	private SimpleMoleculeService service = new SimpleMoleculeService();

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UploadServlet.class);

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.debug("Received file upload request.");
		Part part = request.getPart("file");
		try {
			byte[] bytes = IOUtils.toByteArray(part.getInputStream());
			Molecule molecule = MolImporter.importMol(bytes);

			MoleculeInfo info = service.saveMolecule(bytes, molecule);
			ObjectMapper om = new ObjectMapper();
			om.writeValue(response.getOutputStream(), info);
			LOGGER.debug("File {} sucessfuly saved in {}.", info.getId(),
					info.getFilename());
		} catch (MolFormatException e) {
			LOGGER.error("Invalid Molecule file received.", e);
			throw new ServletException("Invalid molecule file.", e);
		}

	}

}
