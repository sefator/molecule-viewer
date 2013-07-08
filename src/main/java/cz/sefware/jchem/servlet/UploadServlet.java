package cz.sefware.jchem.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import chemaxon.formats.MolFormatException;
import chemaxon.formats.MolImporter;
import chemaxon.struc.Molecule;
import cz.sefware.jchem.model.MoleculeInfo;
import cz.sefware.jchem.service.SimpleMoleculeService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = "/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = -2690570472811965423L;
	@Inject
	private SimpleMoleculeService service;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		for (Part part : request.getParts()) {
			try {
				byte[] bytes = IOUtils.toByteArray(part.getInputStream());
				Molecule molecule = MolImporter.importMol(bytes);
				MoleculeInfo info = new MoleculeInfo();
				info.setFilename(part.getName());
				info.setName(molecule.getFormula());
				info.setFormat(molecule.getInputFormat());
				service.saveMolecule(bytes, info);
			} catch (MolFormatException e) {
				// wrong file type
			}
			// response.setContentType("image/jpeg");
			// IOUtils.write(molecule.toBinFormat("jpeg:setcolors,w800,h600,Q96"),
			// response.getOutputStream());
		}
	}

}
