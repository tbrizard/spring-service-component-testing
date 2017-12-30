package com.blizzardcomputing.cart.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.blizzardcomputing.cart.persistence.dto.PriceDTO;

@Repository
public class PriceDAOImpl implements PriceDAO {

	// just a simple example showing this service as a REST consumer. in a real service we would have caching, use Feign, etc.
	@Autowired
	private RestTemplate restTemplate;
	
	// in a real service we would externalize the URL and set the wiremock server and port in a profile application properties file
	@Value("${wiremock.server.port}")
	private Integer wiremockPort;
	
	@Override
	public PriceDTO getProductPrice(String productId) {
		
		try {
			return restTemplate.getForObject("http://localhost:" + wiremockPort + "/price-service/products/"+productId, PriceDTO.class);
		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
				return null;
			} else {
				throw e;
			}
		}
	}

}
