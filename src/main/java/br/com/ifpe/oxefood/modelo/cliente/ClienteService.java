package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.exception.BadRequestException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

   @Autowired
   private ClienteRepository repository;

   @Transactional
   public Cliente save(Cliente cliente) {

       Optional<Cliente> clienteExiste = repository.findByCpf(cliente.getCpf());

       if(clienteExiste.isPresent()){
        throw new BadRequestException("Cliente já Existe!");
       }

       cliente.setHabilitado(Boolean.TRUE);
       cliente.setVersao(1L);
       cliente.setDataCriacao(LocalDate.now());
       return repository.save(cliente);
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

}

