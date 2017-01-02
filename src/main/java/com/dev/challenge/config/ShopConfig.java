package com.dev.challenge.config;

import static springfox.documentation.builders.PathSelectors.regex;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tapan
 *
 */
@Configuration
@EnableSwagger2
@PropertySource(value = {"classpath:googlemap_geo.properties"})
public class ShopConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScripts("ddl/CREATE_SHOP_DDL.sql").build();
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean(name = "restTemplate")
	public RestTemplate restTemplate(@Value("${googlemap.timeout}") int readTimeOut, @Value("${googlemap.timeout}") int connectTimeOut){
		return new RestTemplate(clientHttpRequestFactory(readTimeOut, connectTimeOut));
	}
	
	private ClientHttpRequestFactory clientHttpRequestFactory(int readTimeOut,int connectTimeOut) {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeOut);
        factory.setConnectTimeout(connectTimeOut);
        return factory;
    }
	
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("testswager").apiInfo(apiInfo()).select()
				.paths(regex("/shopapi.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Java Developer Challenge - Spring Boot")
				.description(
						"Retail Manager can use this API to GET and POST shop and its address details. It also consumes Google Maps Geocoding API to retrieve the longitude and latitude for the provided shopAddress")
				.termsOfServiceUrl("http://www.some-site.com")
				.contact(new Contact("Tapan Maheshwari", "www.some-site.com", "tapan.maheshwari@yahoo.com"))
				.title("Retail Manager Shop Location API").version("1.0").build();
	}	
	
}
