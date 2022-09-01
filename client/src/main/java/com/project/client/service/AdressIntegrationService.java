package com.project.client.service;

import java.text.MessageFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.project.client.exception.DomainException;
import com.project.client.util.GenericIntegrationApiUtil;
import com.project.client.util.PropertiesUtil;
import com.project.client.vo.integration.AddressCepApiVO;
import com.project.client.vo.integration.CityIbgeVO;
import com.project.client.vo.integration.FUIbgeVO;


@Service
public class AdressIntegrationService {
	
	private static Logger logger = LogManager.getLogger(AdressIntegrationService.class);
	
	private static final String URL_FUS = PropertiesUtil.getUrlValue("url.integration.ibge.fus");
	private static final String URL_CITY = PropertiesUtil.getUrlValue("url.integration.ibge.cities");
	private static final String URL_CEP = PropertiesUtil.getUrlValue("url.integration.api.cep.address");
	 
	/**
	 * All UFs from Brazil
	 * 
	 * @return
	 * @throws DomainException
	 */

	public List<FUIbgeVO> listFus() throws DomainException {

		String url = URL_FUS;

		try {
			List<FUIbgeVO> allFus = new GenericIntegrationApiUtil<FUIbgeVO>().realizeCallList(url);
			return allFus;
		} catch (Exception e) {
			logger.error("Erro ao carregar estados", e);
			throw new DomainException("Erro ao acessar api para buscar estados. Tente novamente");
		}

	}
	
	/**
	 * Cities by UF
	 */
	public List<CityIbgeVO> listCityByFU(Long idFU) throws DomainException {

		String url =  MessageFormat.format( URL_CITY, idFU);

		try {
			List<CityIbgeVO> cities = new GenericIntegrationApiUtil<CityIbgeVO>().realizeCallList(url);
			return cities;
		} catch (Exception e) {
			logger.error("Erro ao carregar cidades", e);
			throw new DomainException("Erro ao acessar api para buscar cidades. Verifique o identificador e tente novamente");
		}

	}
	
	public AddressCepApiVO findAddresByZipcode(String zipcode) throws DomainException {

		String url = URL_CEP + zipcode;

		try {
			AddressCepApiVO address = new GenericIntegrationApiUtil<AddressCepApiVO>().realizeCallOneObject(url, AddressCepApiVO.class);
			return address;
		} catch (Exception e) {
			logger.error("Erro ao carregar endereço por cep", e);
			throw new DomainException("Erro ao acessar api para buscar endereço por cep. Tente novamente");
		}

	}
	
}
