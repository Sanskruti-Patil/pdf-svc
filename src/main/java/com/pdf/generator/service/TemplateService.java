package com.pdf.generator.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
	
	@Autowired
	PdfService pdfService;
	
	public void clientTemplate(String cno, String name, String address, String phn, String bd) throws IOException, InterruptedException, ExecutionException {
		
		final String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		FileInputStream fis = new FileInputStream(new File("ClientTemplate.docx"));
		XWPFDocument doc = new XWPFDocument(fis);
		
		List<IBodyElement> bodyElements = doc.getBodyElements();
		for (IBodyElement bodyElement : bodyElements) {
	        if (bodyElement.getElementType().compareTo(BodyElementType.PARAGRAPH) == 0) {
	        	for (XWPFParagraph p : doc.getParagraphs()) {
	    			List<XWPFRun> runs = p.getRuns();
	    			if (runs != null) {
	    				for (XWPFRun r : runs) {
	    					String text = r.getText(r.getTextPosition());
	    					if (text != null && text.contains("cnmbr")) {
	    						text = text.replace("cnmbr", cno);
	    						r.setText(text, 0);
	    					}
	    				}
	    				for (XWPFRun r : runs) {
	    					String text = r.getText(r.getTextPosition());
	    					if (text != null && text.contains("cname")) {
	    						text = text.replace("cname", name);
	    						r.setText(text, 0);
	    					}
	    				}
	    				for (XWPFRun r : runs) {
	    					String text = r.getText(r.getTextPosition());
	    					if (text != null && text.contains("cadd")) {
	    						text = text.replace("cadd", address);
	    						r.setText(text, 0);
	    					}
	    				}
	    				for (XWPFRun r : runs) {
	    					String text = r.getText(r.getTextPosition());
	    					if (text != null && text.contains("cphn")) {
	    						text = text.replace("cphn", phn);
	    						r.setText(text, 0);
	    					}
	    				}
	    				for (XWPFRun r : runs) {
	    					String text = r.getText(r.getTextPosition());
	    					if (text != null && text.contains("cdob")) {
	    						text = text.replace("cdob", bd);
	    						r.setText(text, 0);
	    					}
	    				}
	    				for (XWPFRun r : runs) {
	    					String text = r.getText(r.getTextPosition());
	    					if (text != null && text.contains("date")) {
	    						text = text.replace("date", date);
	    						r.setText(text, 0);
	    					}
	    				}
	    			}
	    		}
	        }
	    	for (XWPFTable tbl : doc.getTables()) {
				for (XWPFTableRow row : tbl.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							List<XWPFRun> runs = p.getRuns();
							for (XWPFRun r : runs) {
								String text = r.getText(r.getTextPosition());
								if (text != null && text.contains("t1")) {
									text = text.replace("t1", "d6565d454");
									r.setText(text, 0);
								}
							}
							for (XWPFRun r : runs) {
								String text = r.getText(r.getTextPosition());
								if (text != null && text.contains("t2")) {
									text = text.replace("t2", "4df5f5556");
									r.setText(text, 0);
								}
							}
							for (XWPFRun r : runs) {
								String text = r.getText(r.getTextPosition());
								if (text != null && text.contains("t3")) {
									text = text.replace("t3", "3sfr51215f");
									r.setText(text, 0);
								}
							}
							for (XWPFRun r : runs) {
								String text = r.getText(r.getTextPosition());
								if (text != null && text.contains("t4")) {
									text = text.replace("t4", "f9659eg2e");
									r.setText(text, 0);
								}
							}
							for (XWPFRun r : runs) {
								String text = r.getText(r.getTextPosition());
								if (text != null && text.contains("a1")) {
									text = text.replace("a1", "2000");
									r.setText(text, 0);
								}
							}
							for (XWPFRun r : runs) {
								String text = r.getText(r.getTextPosition());
								if (text != null && text.contains("a2")) {
									text = text.replace("a2", "3000");
									r.setText(text, 0);
								}
							}
							for (XWPFRun r : runs) {
								String text = r.getText(r.getTextPosition());
								if (text != null && text.contains("a3")) {
									text = text.replace("a3", "5000");
									r.setText(text, 0);
								}
							}
							for (XWPFRun r : runs) {
								String text = r.getText(r.getTextPosition());
								if (text != null && text.contains("a4")) {
									text = text.replace("a4", "7000");
									r.setText(text, 0);
								}
							}
							for (XWPFRun r : runs) {
								String text = r.getText(r.getTextPosition());
								if (text != null && text.contains("a5")) {
									text = text.replace("a5", "17000");
									r.setText(text, 0);
								}
							}
						}
					}
				}
			}
	    }
		doc.write(new FileOutputStream("Client"+cno+".docx"));
		doc.close();
		ResourceBundle.clearCache();
		pdfService.createClientPdf(cno);
	}

}
