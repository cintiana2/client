package com.project.client;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.client.exception.DomainException;
import com.project.client.service.AddressIntegrationService;
import com.project.client.vo.integration.AddressCepApiVO;
import com.project.client.vo.integration.CityIbgeVO;
import com.project.client.vo.integration.FUIbgeVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ClientApplication.class })

class AddressIntegrationServiceTests {

	@Autowired
	private AddressIntegrationService service;

	@Test
	public void testFindAddressByZipcode() throws DomainException {
		String zipcode = "70094902";
		AddressCepApiVO address = service.findAddresByZipcode(zipcode);
		assertNotNull(address.getCity(), "Não foi encontrado endereço");
	}

	@Test
	public void testListFus() throws DomainException {

		List<FUIbgeVO> ufs = service.listFus();
		assertTrue("Erro ao buscar estados", ufs!= null && ufs.size() >0);
	}
	
	@Test
	public void testListCitiesByFus() throws DomainException {

		List<FUIbgeVO> ufs = service.listFus();
		assertTrue("Erro ao buscar estados", ufs!= null && ufs.size() >0);
		if(ufs!= null && ufs.size() >0) {
			List<CityIbgeVO> cities = service.listCityByFU(ufs.get(0).getId());
			assertTrue("Erro ao buscar cidades", cities!= null && cities.size() >0);
		}	
		
	}

}
