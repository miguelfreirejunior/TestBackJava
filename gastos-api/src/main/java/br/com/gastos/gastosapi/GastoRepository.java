package br.com.gastos.gastosapi;

import org.springframework.data.repository.CrudRepository;

public interface GastoRepository extends CrudRepository<Gasto, Long> {
}