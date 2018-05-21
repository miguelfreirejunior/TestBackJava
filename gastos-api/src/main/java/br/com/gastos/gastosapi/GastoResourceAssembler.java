package br.com.gastos.gastosapi;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * 
 * @author miguel.freire
 *
 * Classe auxiliar para usar hateoas com gastos
 */
public class GastoResourceAssembler extends ResourceAssemblerSupport<Gasto, GastoResource> {

	public GastoResourceAssembler() {
		super(GastoController.class, GastoResource.class);
	}

	@Override
	public GastoResource toResource(Gasto entity) {
		GastoResource resource = createResourceWithId(entity.getId(), entity);
		resource.setCodigousuario(entity.getCodigousuario());
		resource.setData(entity.getData());
		resource.setDescricao(entity.getDescricao());
		resource.setValor(entity.getValor());
		return resource;
	}

}
