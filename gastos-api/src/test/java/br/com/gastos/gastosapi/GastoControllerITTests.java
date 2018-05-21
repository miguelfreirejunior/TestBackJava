package br.com.gastos.gastosapi;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GastoApiApplication.class)
@WebAppConfiguration
@CassandraDataSet(value = { "simpleWithCreateKeyspace.cql" })
@EmbeddedCassandra(timeout = 600000)
@TestExecutionListeners(listeners = { CassandraUnitDependencyInjectionTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
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
		
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	@Test
	public void post_Repository_Called() throws Exception {
		String payload = "{ \"descricao\": \"comra da semana\", \"valor\": 200.00, \"codigousuario\": 23, \"data\": \"2010-11-12T13:14:15Z\" }";
		MvcResult actual = this.mockMvc
				.perform(post("/api/v1/gastos").contentType(MediaType.APPLICATION_JSON).content(payload))
				.andExpect(status().isCreated()).andReturn();
		
		actual = this.mockMvc
			.perform(get(actual.getResponse().getHeader("location")))
			.andReturn();
			
		String s = actual.getResponse().getContentAsString();
		Assert.assertEquals(parse(payload, Gasto.class), parse(s, Gasto.class));
	}

	public String json(Object o) throws JsonProcessingException {
		return objectMapper.writeValueAsString(o);
	}

	public <T> T parse(String json, Class<T> type) throws Exception {
		return objectMapper.readerFor(type).readValue(json);
	}
}
