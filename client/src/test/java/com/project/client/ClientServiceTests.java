package com.project.client;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.client.exception.DomainException;
import com.project.client.service.ClientService;
import com.project.client.vo.AddressVO;
import com.project.client.vo.ClientVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ClientApplication.class})

class ClientServiceTests {
	
	
	@Autowired
	private ClientService clientService;
 
	@Test
	public void testCreateAndRemoveClient() throws DomainException {
	  ClientVO client = saveClient(null, null,false);
	  assertNotNull(client.getId(), "Erro ao criar");
	  long id = client.getId();
	  removeClient(id);
	  ClientVO searchClient = clientService.getById(id);
	  assertNull("Erro ao excluir ", searchClient);
	 }
	
	@Test
	public void testGetById() throws DomainException {
		
		LocalDate date = LocalDate.of(1984, Month.APRIL, 1);
		ClientVO client = saveClient("Maria", date, false);
		
		ClientVO searchClient =clientService.getById(client.getId());
		assertNotNull(searchClient, "Erro ao buscar por id" );	
		assertNotNull(searchClient.getAge(), "Erro ao calcular idade" );	
		
		removeClient(client.getId());
	}
	
	public void testCreateWithAddress() throws DomainException {
		  ClientVO client = saveClient(null, null,true);
		  assertNotNull(client.getId(), "Erro ao criar");
		  long id = client.getId();
		  ClientVO searchClient =clientService.getById(client.getId());
		  assertNotNull( searchClient.getAdress().getZipcode(), "Erro ao criar endereço");
		  removeClient(id);
		  
	}
	
	@Test
	public void testUpdateClient() throws DomainException {
	  ClientVO client = saveClient("Jose", null,false);
	  long id = client.getId();
	  client =clientService.getById(id);
	  String oldName = client.getName();
	  String newName = "João";
	  client.setName(newName);
	  clientService.save(client);
	  client = clientService.getById(id);
	  assertTrue( "Erro ao editar Cliente", !oldName.equals(client.getName()) || newName.equals(client.getName()));
	  removeClient(id);
	 }
	
	@Test
	public void testList() throws DomainException {
		Long totalBefore = clientService.list(0, 10).getTotalElements();
		ClientVO clientVO = saveClient(null, null, false);
		Long totalAfter = clientService.list(0, 10).getTotalElements();
		assertTrue( "Erro ao listar clientes", totalAfter > totalBefore);
		removeClient(clientVO.getId());
	}
	
	@Test
	public void testSearchByName() throws DomainException {
		String name = "Carol da Silva";
		Long totalBefore = clientService.searchByName(name, 0, 10).getTotalElements();
		ClientVO clientVO = saveClient(name, null, false);
		Long totalAfter = clientService.searchByName(name, 0, 10).getTotalElements();
		assertTrue( "Erro ao listar clientes por nome", totalAfter > totalBefore);
		removeClient(clientVO.getId());
	}
	
	@Test
	public void testSearchByEmail() throws DomainException {
		String email = "mariasksksj783733@oliveira.com";
		Long totalBefore = clientService.searchByEmail(email, 0, 10).getTotalElements();
		ClientVO clientVO = createClient(null, null);
		clientVO.setEmail(email);
		Long id = clientService.save(clientVO);
		Long totalAfter = clientService.searchByEmail(email, 0, 10).getTotalElements();
		assertTrue( "Erro ao listar clientes por email", totalAfter > totalBefore);
		removeClient(id);
	}
	
	
	@Test
	public void testSearchByNameAndEmail() throws DomainException {
		String email = "mariasksksj783733@oliveira.com";
		String name = "Carol da Silva";
		Long totalBefore = clientService.searchByNameAndEmail(name, email, 0, 10).getTotalElements();
		ClientVO clientVO = createClient(name, null);
		clientVO.setEmail(email);
		Long id = clientService.save(clientVO);
		Long totalAfter = clientService.searchByNameAndEmail(name, email, 0, 10).getTotalElements();
		assertTrue( "Erro ao listar clientes por nome e email", totalAfter > totalBefore);
		removeClient(id);
	}
	
	@Test
	public void testSearchByBirthday() throws DomainException {
		LocalDate date = LocalDate.of(1984, Month.APRIL, 1);
		LocalDate begin = LocalDate.of(1984, Month.MARCH, 31);
		LocalDate end = LocalDate.of(1984, Month.APRIL, 2);
		Long totalBefore = clientService.searchByBirthday(begin, end, 0, 10).getTotalElements();
		ClientVO clientVO = createClient(null, null);
		clientVO.setBirthday(date);
		Long id = clientService.save(clientVO);
		Long totalAfter = clientService.searchByBirthday(begin, end, 0, 10).getTotalElements();
		assertTrue( "Erro ao listar clientes por aniversário", totalAfter > totalBefore);
		removeClient(id);
	}
	
	@Test
	public void testDeleteLogical() throws DomainException {
		
		ClientVO clientVO = saveClient(null, null, false);
		Long id = clientVO.getId();
		Long totalBefore = clientService.list(0, 10).getTotalElements();
		clientService.logicalDelete(clientVO.getId());
		Long totalAfter = clientService.list(0, 10).getTotalElements();
	     clientVO =clientService.getById(id);
		assertTrue( "Erro ao listar clientes", totalAfter < totalBefore && clientVO != null && clientVO.getId() != null );
		removeClient(id);
	}
	
	@Test
	public void testGetActiveClientById() throws DomainException {
		
		ClientVO clientVO = saveClient(null, null, false);
		Long id = clientVO.getId();
		clientService.logicalDelete(clientVO.getId());
	    clientVO = clientService.getActiveClientById(id);
		assertTrue( "Erro ao listar clientes",  clientVO == null || clientVO.getId() == null );
		removeClient(id);
	}
	
		
	/*
	 *  Métodos para carregar client e limpar
	 */
	
	/**
	 * Se o nome for null, um outro nome ou data são colocados
	 * @param name
	 * @return
	 * @throws DomainException
	 */
	private ClientVO saveClient(String name, LocalDate date, boolean withAddress) throws DomainException {
		ClientVO clientVo = withAddress ? createClientWithAdress(name, date) :createClient(name, date);
		
		Long id = clientService.save(clientVo);
		clientVo.setId(id);
		
		return clientVo;
	}
	
	private ClientVO createClientWithAdress(String name, LocalDate date) {
		ClientVO vo = createClient(name, date);
		vo.setAdress(createAddress());
		return vo;
	}
	
	private ClientVO createClient(String name, LocalDate date) {
		ClientVO vo = new ClientVO();
		if(name == null || name.isBlank()) {
			vo.setName("Pessoa da Silva");
		}else {
			vo.setName(name);
		}
		
		if(date == null) {
			date = LocalDate.now();
		}
		
		vo.setBirthday(date);
		return vo;
	}
	
	private AddressVO createAddress() {
		AddressVO addressVo = new AddressVO();
		addressVo.setCity("City");
		addressVo.setComplement("complement");
		addressVo.setDistrict("District");
		addressVo.setFu("UF");
		addressVo.setNumber(10);
		addressVo.setZipcode("11111111");
		return addressVo;
	}
	
	private void removeClient(Long id) throws DomainException {
		clientService.delete(id);
	}
	
}
