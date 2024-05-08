package com.smengs.controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smengs.dao.FaqRepository;
import com.smengs.entite.faq;

@Controller
@RequestMapping(value = "index")
public class Faq {
	
	@Autowired
	private FaqRepository fr;
	
	@RequestMapping(value = "creationFaq")
	public String creationFq(faq f) {
		fr.save(f);
		return "redirect:listeFaq";
	}
	@RequestMapping(value = "creerFaq")
	public String creerFq(Model model) {
		faq f = new faq();
		model.addAttribute("faq", f);
		return "faq/creerFaq";
	}
	@RequestMapping(value = "listeFaq")
	public String listeFq(Model model) {
		List<faq> faqs = fr.findAll();
		model.addAttribute("faq", faqs);
		
		return "faq/listeFaq";
	}

}
