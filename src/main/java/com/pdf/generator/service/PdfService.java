package com.pdf.generator.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class PdfService {

	public void createClientPdf(String cno) throws IOException {

		try { // docx to pdf

			if (!new java.io.File("Client" + cno + ".docx").isFile())
				throw new IllegalArgumentException("Client" + cno + ".docx" + " is not file");

			Runtime r = Runtime.getRuntime();
			Process process = r.exec("soffice --headless --convert-to pdf " + "Client" + cno + ".docx");
			process.waitFor();

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
