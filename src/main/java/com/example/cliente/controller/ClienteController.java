package com.example.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.cliente.entity.BaseResponse;
import com.example.cliente.entity.Cliente;
import com.example.cliente.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends BaseController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)

	public ResponseEntity salvar(@RequestBody Cliente cliente) {

		try {

			BaseResponse response = clienteService.salvar(cliente);

			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {

			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}

	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)

	public List<Cliente> listaCliente() {

		return clienteService.ListaCliente();

	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente buscarClienteporid(@PathVariable Long id) {
		return clienteService.BuscarPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe"));

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerCliente(@PathVariable("id") Long id) {
		clienteService.BuscarPorId(id).map(cliente -> {
			clienteService.deletarPorId(id);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado."));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)

	public Cliente update(@PathVariable("id") Long id, @RequestBody Cliente cliente) {

		return clienteService.update(id, cliente);

	}

}