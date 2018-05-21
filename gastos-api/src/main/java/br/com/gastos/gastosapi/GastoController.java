package br.com.gastos.gastosapi;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author miguel.freire
 *
 * Controlador das operações com a entidade gasto
 */
@RestController
@RequestMapping("/api/v1/gastos")
public class GastoController {

	private final GastoResourceAssembler assembler = new GastoResourceAssembler();
	
	private final GastoRepository gastoRepository;

	@Autowired
	public GastoController(GastoRepository gastoRepository) {
		this.gastoRepository = gastoRepository;
	}

	/**
	 * Efetua a criação de um novo gasto
	 * 
	 * @param gasto
	 * @return url para recuperar o gasto
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Gasto gasto) {
		gasto.setId(UUID.randomUUID());
		Gasto result = this.gastoRepository.save(gasto);
		
		return ResponseEntity.created(linkTo(GastoController.class).slash(result).toUri()).build();
	}

	/**
	 * Recupera um gasto específico
	 * 
	 * @param uuid
	 * @return O gasto requisitado
	 */
	@GetMapping("/{id}")
	public HttpEntity<GastoResource> query(@PathVariable UUID id) {
		return this.gastoRepository.findById(id).map(result -> {
			GastoResource resource = this.assembler.toResource(result);
//			resource.add(linkTo(GastoController.class).slash(result).withSelfRel());
			return ResponseEntity.ok(resource);
		}).orElse(ResponseEntity.noContent().build());
	}
}