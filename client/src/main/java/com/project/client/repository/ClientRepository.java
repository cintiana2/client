package com.project.client.repository;



import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.client.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	
	@Query(value = "select c from Client c where c.status = 'ENABLE' order by id ")
    Page<Client> listActiveClients(Pageable pageable);
	
	@Query(value = "select c from Client "
			+ "c where c.status = 'ENABLE' "
			+ " and lower(c.email) like lower(concat('%', :emailLikeCase,'%'))"
			+ " order by c.email ")
    Page<Client> listActiveClientsByEmail(String emailLikeCase , Pageable pageable);
	
	@Query(value = "select c "
			+ " from Client c "
			+ "where c.status = 'ENABLE' and lower(c.name) like lower(concat('%', :nameLikeCase,'%'))"
			+ " order by c.name ")
    Page<Client> listActiveClientsByName(String nameLikeCase , Pageable pageable);
	
	@Query(value = "select c "
			+ " from Client c "
			+ "where c.status = 'ENABLE' and lower(c.name) like lower(concat('%', :nameLikeCase,'%'))"
			+ " and lower(c.email) like lower(concat('%', :emailLikeCase,'%'))"
			+ " order by c.name ")
    Page<Client> listActiveClientsByNameAndEmail(String nameLikeCase , String emailLikeCase , Pageable pageable);
	
	@Query(value = "select c "
			+ " from Client c "
			+ "where c.status = 'ENABLE' and  (c.birthday between :begin and :end) "
			+ " order by c.id ")
    Page<Client> listActiveClientsByBirthday(LocalDate begin, LocalDate end, Pageable pageable);
	
	@Query("SELECT c.id from Client c WHERE c.email = :email ")
    Long getIdClienteByEmail(String email);

}
