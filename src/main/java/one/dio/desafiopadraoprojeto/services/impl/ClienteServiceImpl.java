package one.dio.desafiopadraoprojeto.services.impl;

import one.dio.desafiopadraoprojeto.model.Cliente;
import one.dio.desafiopadraoprojeto.model.ClienteRepository;
import one.dio.desafiopadraoprojeto.model.Endereco;
import one.dio.desafiopadraoprojeto.model.EnderecoRepository;
import one.dio.desafiopadraoprojeto.services.ClienteService;
import one.dio.desafiopadraoprojeto.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        clientePorCep(cliente);
    }


    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteDb = clienteRepository.findById(id);
        if (clienteDb.isPresent()) {
            clientePorCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
    private void clientePorCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}