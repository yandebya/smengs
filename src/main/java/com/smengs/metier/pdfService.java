package com.smengs.metier;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;


@Service
public class pdfService {

	public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
		Document d = new Document(PageSize.A4);
		
		PdfWriter.getInstance(d, response.getOutputStream());
		d.open();
		d.add(new Paragraph("generer pdf fff "));
		d.close();
	}
}
