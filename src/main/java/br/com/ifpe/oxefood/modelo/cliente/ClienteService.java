package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.Endereco.EnderecoCliente;
import br.com.ifpe.oxefood.modelo.Endereco.EnderecoClienteRepository;
import br.com.ifpe.oxefood.modelo.mensagens.EmailService;
import br.com.ifpe.oxefood.util.exception.BadRequestException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

   @Autowired
   private ClienteRepository repository;

   @Autowired
   private EnderecoClienteRepository enderecoRepository;

   
    @Autowired
    private EmailService emailService;


   @Transactional
   public Cliente save(Cliente cliente) {

       Optional<Cliente> clienteExiste = repository.findByCpf(cliente.getCpf());

       if(clienteExiste.isPresent()){
        throw new BadRequestException("Cliente já Existe!");
       }

       cliente.setHabilitado(Boolean.TRUE);
       cliente.setVersao(1L);
       cliente.setDataCriacao(LocalDate.now());
       Cliente clienteSalvo = repository.save(cliente);

       emailService.enviarEmailConfirmacaoCadastroCliente(clienteSalvo);

       return clienteSalvo;
   }

   public List<Cliente> listarTodos() {
  
    return repository.findAll();
}

public Cliente obterPorID(Long id) {

    return repository.findById(id).orElse(null);
}

    @Transactional
    public void update(Long id, Cliente clienteAlterado) {

        Cliente cliente = repository.findById(id).get();
        cliente.setNome(clienteAlterado.getNome());
        cliente.setDataNascimento(clienteAlterado.getDataNascimento());
        cliente.setCpf(clienteAlterado.getCpf());
        cliente.setFoneCelular(clienteAlterado.getFoneCelular());
        cliente.setFoneFixo(clienteAlterado.getFoneFixo());

        cliente.setVersao(cliente.getVersao() + 1);
        repository.save(cliente);
    }


@Transactional
public void delete(Long id) {

    Cliente cliente = repository.findById(id).get();
    cliente.setHabilitado(Boolean.FALSE);
    cliente.setVersao(cliente.getVersao() + 1);

    repository.save(cliente);
}

 @Transactional
   public EnderecoCliente adicionarEnderecoCliente(Long clienteId, EnderecoCliente endereco) {

       Cliente cliente = this.obterPorID(clienteId);
      
       //Primeiro salva o EnderecoCliente:

       endereco.setCliente(cliente);
       endereco.setHabilitado(Boolean.TRUE);
       enderecoRepository.save(endereco);
      
       //Depois acrescenta o endereço criado ao cliente e atualiza o cliente:

       List<EnderecoCliente> listaEnderecoCliente = cliente.getEnderecos();
      
       if (listaEnderecoCliente == null) {
           listaEnderecoCliente = new ArrayList<EnderecoCliente>();
       }
      
       listaEnderecoCliente.add(endereco);
       cliente.setEnderecos(listaEnderecoCliente);
       repository.save(cliente);
      
       return endereco;
   }


   @Transactional
   public EnderecoCliente atualizarEnderecoCliente(Long id, EnderecoCliente enderecoAlterado) {

       EnderecoCliente endereco = enderecoRepository.findById(id).get();
       endereco.setRua(enderecoAlterado.getRua());
       endereco.setNumero(enderecoAlterado.getNumero());
       endereco.setBairro(enderecoAlterado.getBairro());
       endereco.setCep(enderecoAlterado.getCep());
       endereco.setCidade(enderecoAlterado.getCidade());
       endereco.setEstado(enderecoAlterado.getEstado());
       endereco.setComplemento(enderecoAlterado.getComplemento());

       return enderecoRepository.save(endereco);
   }

   @Transactional
    public void removerEnderecoCliente(Long id) {

    EnderecoCliente endereco = enderecoRepository.findById(id).get();
    endereco.setHabilitado(Boolean.FALSE);
    enderecoRepository.save(endereco);

    Cliente cliente = this.obterPorID(endereco.getCliente().getId());
    cliente.getEnderecos().remove(endereco);
    repository.save(cliente);
}

public List<EnderecoCliente> buscarEnderecoCliente(Long clienteId) {

    Cliente cliente = this.obterPorID(clienteId);
    return cliente.getEnderecos();
}



}

