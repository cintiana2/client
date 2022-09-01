package com.project.client.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.client.exception.DomainException;
import com.project.client.service.ClientService;
import com.project.client.vo.ClientVO;

@RestController
@RequestMapping(value = "/api/v1/client")
public class ClientController
{
	
	@Autowired
	private ClientService clientService;
	
	
	/**
	 * Método pode ser usado para editar também, assim o salvar
	 *  não precisa diferenciar se está editando ou criando
	 * @param client
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody ClientVO client) {
		try {
			Long id = clientService.save(client);
			return ResponseEntity.ok(id);
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	/**
	 * Como foi pedido na definição, foi criado um método 
	 * editar específico, mas não seria necessário pois o método acima
	 * pode ser usado na função
	 * @param client
	 * @return
	 */
	@PutMapping 
	public ResponseEntity<?> updated(@RequestBody ClientVO client) {
		try {
			if(client.getId() !=null) 
			{
				Long id = clientService.save(client);
				return ResponseEntity.ok(id);
			}else {
				return catchExceptionDomain(new DomainException("Mande um cliente com id válido"));
			}
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	/**
	 * Não foi pedido, mas coloquei para
	 * deixar mais completo e facilitar o uso
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			clientService.delete(id);
			return ResponseEntity.ok(true);
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	/**
	 * Caso se opte pela exclusão lógica, esse metódo
	 * pode ser usado para excluir em vez de acima, pois
	 * muda de ativo para inativo
	 * @param id
	 * @return
	 */
	@PutMapping("deleteLogical/{id}")
	public ResponseEntity<?> deleteLogical(@PathVariable Long id) {
		try {
			clientService.logicalDelete(id);
			return ResponseEntity.ok(true);
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		
		try {
			return ResponseEntity.ok(clientService.getById(id));
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	@GetMapping("list")
	public ResponseEntity<?> list(@RequestParam("page") int page, @RequestParam("sizePage") int sizePage ) {
		
		return ResponseEntity.ok(clientService.list(page, sizePage));
		
	}
	
	@GetMapping("find-by-name")
	public ResponseEntity<?> findByName(@RequestParam("name") String name, 
			@RequestParam("page") int page, @RequestParam("sizePage") int sizePage) {
	
		return ResponseEntity.ok(clientService.searchByName(name, page, sizePage));
	
	}
	
	@GetMapping("find-by-email")
	public ResponseEntity<?> findByEmail(@RequestParam("email") String email, 
			@RequestParam("page") int page, @RequestParam("sizePage") int sizePage) {
	
		return ResponseEntity.ok(clientService.searchByEmail(email, page, sizePage));
	
	}
	
	@GetMapping("find-by-name-and-email")
	public ResponseEntity<?> findByNameAndEmail(@RequestParam("name") String name ,
			@RequestParam("email") String email, @RequestParam("page") int page, 
			@RequestParam("sizePage") int sizePage) {
	
		return ResponseEntity.ok(clientService.searchByNameAndEmail(name, email, page, sizePage));
	
	}
	
	@GetMapping("find-by-birthday")
	public ResponseEntity<?> findByBirthday(@RequestParam("begin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate begin ,
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate end,
			@RequestParam("page") int page, @RequestParam("sizePage") int sizePage) {
	
		return ResponseEntity.ok(clientService.searchByBirthday(begin, end, page, sizePage));
	
	}
	
	
	private ResponseEntity<?> catchExceptionDomain(DomainException exception ){
		return ResponseEntity.badRequest().body(exception.getMessage());
	}

}
