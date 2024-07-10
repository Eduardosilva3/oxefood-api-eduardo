package br.com.ifpe.oxefood.modelo.promocao;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.ifpe.oxefood.util.exception.BadRequestException;
import jakarta.transaction.Transactional;

@Service
public class PromocaoService {

    @Autowired
    private PromocaoRepository repository;


    @Transactional
   public Promocao save(Promocao promocao) {

      
    promocao.setHabilitado(Boolean.TRUE);
    promocao.setVersao(1L);
    promocao.setDataCriacao(LocalDate.now());
       return repository.save(promocao);
   }

   public List<Promocao> listarTodos() {
  
    return repository.findAll();
}

public Promocao obterPorID(Long id) {

    return repository.findById(id).orElse(null);
}


@Transactional
public void delete(Long id) {

    Promocao promocao = repository.findById(id).orElseThrow(() ->{
        throw new BadRequestException("Promocao não Existe!");
    });
    promocao.setHabilitado(Boolean.FALSE);
    promocao.setVersao(promocao.getVersao() + 1);

    repository.save(promocao);
}

    
}
