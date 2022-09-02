package com.project.client.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.client.entity.Address;
import com.project.client.entity.Client;
import com.project.client.exception.DomainException;
import com.project.client.repository.ClientRepository;
import com.project.client.util.AddressUtil;
import com.project.client.util.StringUtil;
import com.project.client.util.enums.StatusClientEnum;
import com.project.client.vo.ClientVO;
import com.project.client.vo.SimpleClientVO;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;


	public Long save(ClientVO vo) throws DomainException {
		if (StringUtil.isValid(vo.getName()) && vo.getBirthday() != null) {
			Client client = new Client(vo);
			try {
				checkClient(client);
				checkAndCorrectAdressClient(client);
				repository.save(client);
				return client.getId();
			} catch (Exception s) {
				throw new DomainException(s.getLocalizedMessage());
			}
		} else {
			throw new DomainException("Nome e data de nascimento do cliente são obrigatórias");
		}
	}
	
	
	public void delete(Long id) throws DomainException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new DomainException(e.getLocalizedMessage());
		}
	}

	/**
	 * disable client
	 * @param id
	 * @throws DomainException
	 */
	public void logicalDelete(Long id) throws DomainException {
		try {
			ClientVO vo = getById(id);
			if(vo !=null) {
				Client client = new Client(vo);
				if (client != null) {
					client.setStatus(StatusClientEnum.DISABLE);
					repository.save(client);
				}
			}
		} catch (Exception e) {
			throw new DomainException(e.getLocalizedMessage());
		}
	}

	/**
	 * Get client in all status
	 * @param id
	 * @return
	 * @throws DomainException
	 */
	public ClientVO getById(Long id) throws DomainException {
		try {
			
			return repository.getVoById(id);

		} catch (EntityNotFoundException e) {
			return null;
		}
	}
	
	/**
	 * Show only client is enable
	 * @param id
	 * @return
	 * @throws DomainException
	 */
	public ClientVO getActiveClientById(Long id) throws DomainException {
		try {
			
			return repository.getActiveVoById(id);

		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	/**
	 * Search Only enable clients
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SimpleClientVO> list(int page, int size) {
		
		Pageable pageable = createPageable(page, size);
		Page<Client> pageClient = repository.listActiveClients(pageable);
		
		return trasformPageClientInPageVO(pageable, pageClient);

	}
	
	/**
	 * Search Only enable clients
	 * @param email
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SimpleClientVO> searchByEmail(String email, int page, int size) {
		Pageable pageable = createPageable(page, size);
		Page<Client> pageClient = repository.listActiveClientsByEmail(email, pageable);

		return trasformPageClientInPageVO(pageable, pageClient);

	}
	
	/**
	 * Search Only enable clients
	 * @param name
	 * @param email
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SimpleClientVO> searchByNameAndEmail(String name, String email, int page, int size) {
		Pageable pageable = createPageable(page, size);
		Page<Client> pageClient = repository.listActiveClientsByNameAndEmail(name,email, pageable);

		return trasformPageClientInPageVO(pageable, pageClient);

	}
	
	/**
	 * Search Only enable clients
	 * @param begin
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SimpleClientVO> searchByBirthday(LocalDate begin, LocalDate end, int page, int size) {
		Pageable pageable = createPageable(page, size);
		Page<Client> pageClient = repository.listActiveClientsByBirthday(begin, end,pageable);

		return trasformPageClientInPageVO(pageable, pageClient);

	}
	
	/**
	 * Search Only enable clients
	 * @param name
	 * @param email
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<SimpleClientVO> searchByName(String name, int page, int size) {
		Pageable pageable = createPageable(page, size);
		Page<Client> pageClient = repository.listActiveClientsByName(name, pageable);

		return trasformPageClientInPageVO(pageable, pageClient);

	}

	// private methods
	private Page<SimpleClientVO> trasformPageClientInPageVO(Pageable pageable, Page<Client> pageClient) {
		Page<SimpleClientVO> pageVO = new PageImpl<SimpleClientVO>(transformListClientInListVO(pageClient.getContent()), pageable,
				pageClient.getTotalElements());
		return pageVO;
	}

	private List<SimpleClientVO> transformListClientInListVO(List<Client> listClient) {
		List<SimpleClientVO> listVo = null;
		if (listClient != null) {
			listVo = new ArrayList<SimpleClientVO>();
			for (Client c : listClient) {
				listVo.add(new SimpleClientVO(c.getId(), c.getName(), c.getEmail(), c.getBirthday()));
			}
		}

		return listVo;

	}
	
	private void checkClient(Client client) throws DomainException {
			
			if(StringUtil.isValid(client.getEmail())) {
				if(  !StringUtil.isValidEmail(client.getEmail()) ) {
					throw new DomainException("E-mail não é válido");
				}	
					
				if(existEmailWithAnotherClient(client.getId(), client.getEmail()) ) {
					throw new DomainException("Já existe um cliente com esse e-mail");
				}	
			}
		}
	
	private void checkAndCorrectAdressClient(Client client) throws DomainException {
		
		if(client.getAdress() == null) {
			client.setAdress(new Address());
		}
		 Address address = client.getAdress();
		
		if(!StringUtil.isValid(address.getCountry())){
			client.getAdress().setCountry(Address.DEFAULT_COUNTRY);//inicia país se ele está inválido
		}
		
		if(StringUtil.isValid(address.getZipcode()) ) { 
			address.setZipcode(cleanAndValidZipcode(address.getZipcode()));
		}
		
		if(StringUtil.isValid(address.getFu()) && !AddressUtil.isValidState(address.getFu())){
			throw new DomainException("A sigla do Estado/UF não é válida.");
		}
		
	}
	
	private String cleanAndValidZipcode(String zipcode) throws DomainException {
		zipcode = StringUtil.cleanLettersAndSimbols(zipcode); // limpa caracteres não numéricos
		if(!AddressUtil.isValidZipcode(zipcode)) {
			throw new DomainException("CEP não é válido.");
		}
		
		return zipcode;
	}
	
	private boolean existEmailWithAnotherClient(Long idClient, String email) {
		
		Long idEmail = repository.getIdClienteByEmail(email);
		
		if(idClient == null && idEmail !=null) {
			return true;
		} else if(idClient !=null && idEmail !=null  && idClient != idEmail) {
			return true;
		}
		
		return false;
	}

	private Pageable createPageable(int page, int size) {
		return PageRequest.of(page, size);
	}

}
