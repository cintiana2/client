package com.project.client.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.client.entity.Planet;
import com.project.client.vo.PlanetVO;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
	
	@Query("select new com.project.client.vo.PlanetVO(p.id, p.name, p.climate, p.ground) from Planet p"
			+ "   where lower(p.name) like lower(concat('%', :nameLikeCase,'%'))")
	List<PlanetVO> findByPartName(String nameLikeCase);
	
	@Query("select new com.project.client.vo.PlanetVO(p.id, p.name, p.climate, p.ground) from Planet p")
	List<PlanetVO> listAllVo();
	 
	@Query("select new com.project.client.vo.PlanetVO(p.id, p.name, p.climate, p.ground) from Planet p"
			+ " where p.id = :id ")
	PlanetVO getVoById(Long id);
	

}
