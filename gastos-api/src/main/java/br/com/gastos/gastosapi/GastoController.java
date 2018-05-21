package br.com.gastos.gastosapi;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/gastos")
public class GastoController {

	private final GastoRepository gastoRepository;

	@Autowired
	public GastoController(GastoRepository gastoRepository) {
		this.gastoRepository = gastoRepository;
	}

	// @RequestMapping(method = RequestMethod.POST, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Gasto gasto) {

		gasto.setId(UUID.randomUUID());
		Gasto result = this.gastoRepository.save(gasto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}