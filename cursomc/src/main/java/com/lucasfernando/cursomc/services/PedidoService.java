package com.lucasfernando.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasfernando.cursomc.domain.Pedido;
import com.lucasfernando.cursomc.repositories.PedidoRepository;
import com.lucasfernando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;

	public List<Pedido> get() {
		List<Pedido> lista = repository.findAll();
		return lista;
	}

	public Pedido get(int id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
