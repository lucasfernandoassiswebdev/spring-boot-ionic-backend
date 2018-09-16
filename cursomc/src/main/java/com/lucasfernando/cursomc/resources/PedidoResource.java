package com.lucasfernando.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasfernando.cursomc.domain.Pedido;
import com.lucasfernando.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> get() {
		List<Pedido> lista = service.get();
		return ResponseEntity.ok(lista);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> get(@PathVariable Integer id) {
		Pedido pedido = service.get(id);
		return ResponseEntity.ok(pedido);
	}
}
