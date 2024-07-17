package br.com.ifpe.oxefood.modelo.categoriaproduto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoriaProdutoService {

    @Autowired
    private CategoriaProdutoRepository repository;

   @Transactional
   public CategoriaProduto save(CategoriaProduto categoriaproduto) {

    categoriaproduto.setHabilitado(Boolean.TRUE);
    categoriaproduto.setVersao(1L);
    categoriaproduto.setDataCriacao(LocalDate.now());

    
    return repository.save(categoriaproduto);
   }


public List<CategoriaProduto> listarTodos() {
  
    return repository.findAll();
}

public CategoriaProduto obterPorID(Long id) {

    return repository.findById(id).orElse(null);
}
}
