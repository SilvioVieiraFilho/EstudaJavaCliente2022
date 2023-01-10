package com.example.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cliente.entity.BaseResponse;
import com.example.cliente.entity.Cliente;
import com.example.cliente.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired

	private ClienteRepository clienteRepository;

	public BaseResponse  salvar(Cliente cliente) {
		
		BaseResponse response = new BaseResponse();

		
		if(cliente.getNome()  ==  "") {
			
			response.message = " Preencha o nome corretamente";
			response.statusCode = 400;
		
		}
		else {
		
			
		 
			clienteRepository.save(cliente);
			 
			 response.message = "Dependente salvo";
				response.statusCode = 200;

		}
		
		 return response;
	}

	public List<Cliente> ListaCliente() {
		return clienteRepository.findAll();

	}

	public Optional<Cliente> BuscarPorId(Long id) {
		return clienteRepository.findById(id);

	}

	public void deletarPorId(Long id) {
		clienteRepository.deleteById(id);

	}

	public Cliente update(Long id, Cliente obj) {

		Cliente newObj = new Cliente();
		
		
        newObj.setId(id);
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
		newObj.setEmail(obj.getEmail());

		return clienteRepository.save(newObj);

	}

}
