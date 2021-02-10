package com.gessco.controlleur;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gessco.dao.ServiceRepository;
import com.gessco.entite.Service;
import com.gessco.entite.apropos;



@Controller
@RequestMapping(value = "index")
public class ServiceController {
	
	@Autowired
	private ServiceRepository sr;
	
	
	@Value("${imageUtilisateur}")
	private String photoSer;
	@RequestMapping(value = "creerService")
	public String creerApp(Service app , @RequestParam(name = "picture") MultipartFile file) throws IOException {
		
		if (!(file.isEmpty())) {
			app.setPhotiService(file.getOriginalFilename());
		}
		sr.save(app);
		if (!(file.isEmpty())) {
			app.setPhotiService(file.getOriginalFilename());
			file.transferTo(new File(photoSer + app.getPhotiService()));
		}
		return "redirect:listeSerSite";
	}
	
	@RequestMapping(value = "/getPhotiService", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhotiService(String photiService) throws IOException {
		File f = new File(photoSer + photiService);
	

		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	
	@RequestMapping(value = "creationSerive")
	public String creationApp(Model model) {
		Service a = new Service();
	model.addAttribute("service", a);
	
	return "service/NewService";
	}

	@RequestMapping(value = "listeSerSite")
	public String ListeServiceSite(Model model,Long id_ser) {
	
		List<Service> lapp = sr.findAll();
		model.addAttribute("listeSer", lapp);
		footerService( model,id_ser);
	return "site/service/service";
}
	@RequestMapping(value = "listeService")
	public String ListeService(Model model,Long id_ser) {
	
		List<Service> lapp = sr.findAll();
		model.addAttribute("listeServ", lapp);
		;
	return "service/tableService";
}
	public List<Service> listeSer(Model m) {
		List<Service> lapp = sr.findAll();
		m.addAttribute("listeSer", lapp);
	return sr.findAll();
}
	
	@RequestMapping(value="footerService")
	public String footerService(Model model, Long id_ser) {
		List<Service> lapp =sr.ServiceParId(id_ser);
		model.addAttribute("service", lapp);
		listeSer(model);
		return"site/footer/service/service";
	}
	

	public List<Service> listeDert(Model model, Long id_ser) {

		return sr.ServiceParId(id_ser);
	}
	
}
