package br.com.gastos.gastosapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GastoApiApplication.class)
@WebAppConfiguration
@Transactional
public class GastoControllerITTests {

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private GastoRepository gastoRepository;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void post_Repository_Called() throws Exception {
		String payload = "{ \"descricao\": \"alfanumerico\", \"valor\": double americano, \"codigousuario\": numerico, \"data\": Data dem formato UTC }";

		String actual = this.mockMvc
				.perform(post("/api/v1/gastos").contentType(MediaType.APPLICATION_JSON).content(json(payload)))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", Matchers.greaterThan(1))).andReturn().getResponse().getContentAsString();

		//Assert.assertArrayEquals(graph.getData().toArray(), expected.getData().toArray());
	}
	
	public String json(Object o) throws JsonProcessingException {
		return objectMapper.writeValueAsString(o);
	}

	public <T> T parse(String json, Class<T> type) throws Exception {
		return objectMapper.readerFor(type).readValue(json);
	}
}
