package br.com.ifpe.oxefood.modelo.entregador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.transaction.Transactional;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository repository;
    
    @Transactional
   public Entregador save(Entregador entregador) {

    entregador.setHabilitado(Boolean.TRUE);
    entregador.setVersao(1L);
    entregador.setDataCriacao(LocalDate.now());
       return repository.save(entregador);
   }

   
   public List<Entregador> listarTodos() {
  
    return repository.findAll();
}

public Entregador obterPorID(Long id) {

    return repository.findById(id).orElse(null);
}


@Transactional
public void delete(Long id) {

    Entregador entregador = repository.findById(id).get();
    entregador.setHabilitado(Boolean.FALSE);
    entregador.setVersao(entregador.getVersao() + 1);

    repository.save(entregador);
}
}
