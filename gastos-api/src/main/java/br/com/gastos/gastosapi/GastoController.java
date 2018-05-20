package br.com.gastos.gastosapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gastos")
public class GastoController{
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create() {
		return ResponseEntity
				.status(HttpStatus.CREATED).build();
				// .body(this.graphRepository.save(new Graph(routes.getData())));
	}
}