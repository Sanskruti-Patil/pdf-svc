package com.pdf.generator.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdf.generator.exception.RecordNotFoundException;
import com.pdf.generator.model.ClientEntity;
import com.pdf.generator.service.ClientService;

@RestController
@RequestMapping("/client")
public class Controller {
	@Autowired
	ClientService service;

	@GetMapping
	public ResponseEntity<List<ClientEntity>> getAllOrder() {
		List<ClientEntity> list = service.getAllOrder();

		return new ResponseEntity<List<ClientEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<byte[]> getClientById(@PathVariable("id") Long id) throws RecordNotFoundException,
			InvalidFormatException, IOException, InterruptedException, ExecutionException {
		ClientEntity entity = service.getClientById(id);

		File file = new File("Client" + id + ".pdf");

		byte[] fileContent = Files.readAllBytes(file.toPath());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "Client" + id + ".pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(fileContent, headers, HttpStatus.OK);
		return response;
	}

	@PostMapping
	public ResponseEntity<ClientEntity> createOrUpdateOrder(ClientEntity client) throws RecordNotFoundException {
		ClientEntity updated = service.createOrUpdateOrder(client);
		return new ResponseEntity<ClientEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteOrderById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteOrderById(id);
		return HttpStatus.FORBIDDEN;
	}

}