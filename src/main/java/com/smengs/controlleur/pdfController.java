package com.smengs.controlleur;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.smengs.metier.pdfService;



@Controller
public class pdfController {

	@Autowired
	private pdfService pdfService;
	
	@RequestMapping(value="expoert")
	public void genererPdf(HttpServletResponse resoponse) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		cell.setPhrase(new Phrase(""));
		
		PdfPTable t = new PdfPTable(5);
		//writeTableHeader(t);
		
		resoponse.setContentType("application/pdf");
		String key = "content.Disposition";
		String value = "attachment ;filename= user.pdf";
		resoponse.setHeader(key, value); 
	
		pdfService.exportPdf(resoponse);
	}
}
