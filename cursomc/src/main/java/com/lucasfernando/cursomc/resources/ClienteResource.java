package com.lucasfernando.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasfernando.cursomc.domain.Cliente;
import com.lucasfernando.cursomc.dto.ClienteDTO;
import com.lucasfernando.cursomc.dto.ClienteNewDTO;
import com.lucasfernando.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService service;
			
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> get() {
		List<Cliente> lista = service.findAll();
		List<ClienteDTO> listDTO = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> get(@PathVariable Integer id) {
		Cliente cliente = service.find(id);
		return ResponseEntity.ok(cliente);
	}
	
	@RequestMapping(value="/page",method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> get(
			@RequestParam(value="page", defaultValue="0") Integer page, //RequestParam = URL queryString  
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cliente> lista = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = lista.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok(listDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> post(@Valid @RequestBody ClienteNewDTO objDTO) {
		Cliente obj = service.fromDto(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> put(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente obj = service.fromDto(clienteDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
