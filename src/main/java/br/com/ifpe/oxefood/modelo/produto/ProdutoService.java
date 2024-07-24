package br.com.ifpe.oxefood.modelo.produto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.exception.ProdutoException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
    
     @Autowired
    private ProdutoRepository repository;

   @Transactional
   public Produto save(Produto produto) {

    
        if (produto.getValorUnitario() < 10) {
	        throw new ProdutoException(ProdutoException.MSG_VALOR_MINIMO_PRODUTO);
    	}


       produto.setHabilitado(Boolean.TRUE);
       produto.setVersao(1L);
       produto.setDataCriacao(LocalDate.now());
       return repository.save(produto);
   }


   public List<Produto> listarTodos() {
  
    return repository.findAll();
}

public Produto obterPorID(Long id) {

    return repository.findById(id).orElse(null);
}


    @Transactional
    public void update(Long id, Produto produtoAleterado) {

        if (produtoAleterado.getValorUnitario() < 10) {
	        throw new ProdutoException(ProdutoException.MSG_VALOR_MINIMO_PRODUTO);
    	}


        Produto produto = repository.findById(id).get();



        produto.setCodigo(produtoAleterado.getCodigo());
        produto.setDescricao(produtoAleterado.getDescricao());
        produto.setTitulo(produtoAleterado.getTitulo());
        produto.setValorUnitario(produtoAleterado.getValorUnitario());
        produto.setTempoEntregaMaximo(produtoAleterado.getTempoEntregaMaximo());
        produto.setTempoEntregaMinimo(produtoAleterado.getTempoEntregaMinimo());
        produto.setCategoria(produtoAleterado.getCategoria());

        produto.setVersao(produto.getVersao() + 1);
        repository.save(produto);
    }

@Transactional
public void delete(Long id) {

    Produto produto = repository.findById(id).get();
    produto.setHabilitado(Boolean.FALSE);
    produto.setVersao(produto.getVersao() + 1);

    repository.save(produto);
}

}
