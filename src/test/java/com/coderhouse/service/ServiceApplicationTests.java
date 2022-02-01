package com.coderhouse.service;

import com.coderhouse.service.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServiceApplicationTests {

	private String url;

	@LocalServerPort
	private int port;
	private final ObjectMapper objectMapper = new ObjectMapper();

	private static Long initTime = System.currentTimeMillis();

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeAll
	static void setup() {
		System.out.println("@BeforeAll - se ejecuta antes de todos los test");
	}

	@BeforeEach
	public void init() {
		url = String.format("http://localhost:%d/api/", port);
		System.out.println("@BeforeEach - se ejecuta antes de cada uno de los test");
	}

	@AfterAll
	public static void finish() {
		System.out.printf("@AfterAll - se ejecuta luego de todos los test. Duración: %.2fs %n %n", (float)(System.currentTimeMillis() - initTime) / 1000);
	}

	@Test
	public void obtenerTodosLosProductos() {
		String urlTest = url + "products";
		var productsResult = this.restTemplate.getForObject(urlTest, List.class);
		Assert.notNull(productsResult, "Lista de produtos no nula");
		Assert.notEmpty(productsResult, "Lista de produtos con elementos");
		Assert.isTrue(productsResult.size() == 6, "Size is ok == 6");
	}

	@Test
	public void obtenerProductoById() {
		String urlTest = String.format(url + "products/%d", 1);
		Product productResult = this.restTemplate.getForObject(urlTest, Product.class);
		Assert.notNull(productResult, "Produto no nula");
		Assert.isTrue(productResult.getId() == 1, "Product id ok");
		Assert.isTrue(productResult.getTitle().equals("shoes"), "Product title ok");
	}

	@Test
	public void crearProducto() {
		String urlTest = url + "products";
		Product productPost = new Product(6L, "Producto Id Test", 150);
		Product productResult = this.restTemplate.postForObject(urlTest, productPost, Product.class);
		Assert.notNull(productResult, "Produto no nula");
		Assert.isTrue(productResult.getId() == 6, "Product id ok");
		Assert.isTrue(productResult.getTitle().equals("Producto Id Test"), "Product title ok");
		Assert.isTrue(productResult.getPrice() == 150, "Product price ok");
	}


	@Test
	public void obtenerTodosLosProductosHttpRequestStatus() throws IOException {

		String urlTest = url + "products";

		// Given
		var request = new HttpGet(urlTest);

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		Assert.isTrue(
				httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK,
				"Response status OK"
		);
	}

	@Test
	public void obtenerTodosLosProductosHttpRequestHeader() throws IOException {

		// Given
		String heeaderApplicationJson = "application/json";
		String urlTest = url + "products";

		HttpUriRequest request = new HttpGet(urlTest);

		// When
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		// Then
		String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
		Assert.isTrue(heeaderApplicationJson.equals(mimeType), "Header application/json Ok");
	}

	@Test
	public void obtenerTodosLosProductosHttpRequestPayload() throws IOException {

		String urlTest = url + "products";

		// Given
		HttpUriRequest request = new HttpGet(urlTest);

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		String content = EntityUtils.toString(httpResponse.getEntity());

		var productsResult = objectMapper.readValue(content, List.class);

		// Then
		Assert.notNull(productsResult, "Lista de produtos no nula");
		Assert.notEmpty(productsResult, "Lista de produtos con elementos");
		Assert.isTrue(productsResult.size() == 5, "Size is ok == 5");
	}
}
