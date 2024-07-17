package br.com.ifpe.oxefood.api.produto;

import java.util.List;

import javax.lang.model.element.ModuleElement.ProvidesDirective;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ifpe.oxefood.modelo.categoriaproduto.CategoriaProdutoService;
import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/api/produto")
@RestController
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @Operation(summary = "Serviço responsável por salvar um produto no sistema.", description = "Exemplo de descrição de um endpoint responsável por inserir um produto no sistema.")
    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoRequest request) {

        Produto produtoNovo = request.build();
        produtoNovo.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
        Produto produto = service.save(produtoNovo);
        return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
 
    }

    @Operation(summary = "Serviço responsável por buscar todos os produtos no sistema.", description = "Exemplo de descrição de um endpoint responsável por buscasr todos os produtos no sistema.")
    @GetMapping
    public List<Produto> listarTodos() {
        return service.listarTodos();
    }

    @Operation(summary = "Serviço responsável por buscar um produto no sistema pelo identificador.", description = "Exemplo de descrição de um endpoint responsável por buscar um produto no sistema pelo identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<Object> obterPorID(@PathVariable Long id) {
        Produto produto = service.obterPorID(id);
        if (produto == null) {
            return new ResponseEntity<Object>("Entregador não Existe!", HttpStatus.OK);
        }

        return new ResponseEntity<Object>(produto, HttpStatus.OK);

    }

    @Operation(summary = "Serviço responsável por atualizar um Entregador no sistema pelo identificador.", description = "Exemplo de descrição de um endpoint responsável por atualizar um Entregaor no sistema pelo identificador.")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody ProdutoRequest request) {

       Produto produto = request.build();
       produto.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
       service.update(id, produto);
      
       return ResponseEntity.ok().build();
   }


    @Operation(summary = "Serviço responsável por deletar um produto no sistema pelo identificador.", description = "Exemplo de descrição de um endpoint responsável por buscar um deletar no sistema pelo identificador.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
