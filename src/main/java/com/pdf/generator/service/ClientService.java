package com.pdf.generator.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdf.generator.constants.FileConstants;
import com.pdf.generator.exception.RecordNotFoundException;
import com.pdf.generator.model.ClientEntity;
import com.pdf.generator.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository repository;
	
	@Autowired
	TemplateService templateService;

	public List<ClientEntity> getAllOrder() {
		final List<ClientEntity> clientList = repository.findAll();

		if (clientList.size() > 0) {
			return clientList;
		} else {
			return new ArrayList<ClientEntity>();
		}
	}

	public ClientEntity getClientById(Long id) throws InvalidFormatException, IOException, RecordNotFoundException, InterruptedException, ExecutionException  {
		Optional<ClientEntity> client = repository.findById(id);
		final ClientEntity entity = client.get();

		if (client.isPresent()) {

			final Long cnmbr = entity.getCnmbr();
			final String cno = Long.toString(cnmbr);
			final String name = entity.getName();
			final String address = entity.getAddress();
			final String phn = entity.getPhnnmbr();
			final Date dob = entity.getDob();
			final DateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
			final String bd = sdfr.format(dob);
			
			templateService.clientTemplate(cno, name, address, phn, bd);

			return client.get();
			
		} else {
			throw new RecordNotFoundException(FileConstants.NO_RECORD);
		}

	}

	public ClientEntity createOrUpdateOrder(ClientEntity entity) throws RecordNotFoundException {
		Optional<ClientEntity> client = repository.findById(entity.getCnmbr());

		if (client.isPresent()) {
			ClientEntity newEntity = client.get();
			newEntity.setName(entity.getName());
			newEntity.setAddress(entity.getAddress());
			newEntity.setPhnnmbr(entity.getPhnnmbr());
			newEntity.setDob(entity.getDob());

			newEntity = repository.save(newEntity);

			return newEntity;
		} else {
			entity = repository.save(entity);

			return entity;
		}
	}

	public void deleteOrderById(Long id) throws RecordNotFoundException {
		Optional<ClientEntity> client = repository.findById(id);

		if (client.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException(FileConstants.NO_RECORD);
		}
	}
}