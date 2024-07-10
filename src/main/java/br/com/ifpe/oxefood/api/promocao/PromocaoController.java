package br.com.ifpe.oxefood.api.promocao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ifpe.oxefood.modelo.promocao.Promocao;
import br.com.ifpe.oxefood.modelo.promocao.PromocaoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/promocao")
@CrossOrigin
public class PromocaoController {

    @Autowired
    private PromocaoService promocaoService;

    @Operation(summary = "Serviço responsável por salvar uma promoção no sistema.", description = "Exemplo de descrição de um endpoint responsável por inserir um promoção no sistema.")
    @PostMapping
    public ResponseEntity<Promocao> save(@RequestBody PromocaoRequest request) {

        Promocao promocao = promocaoService.save(request.build());
        return new ResponseEntity<Promocao>(promocao, HttpStatus.CREATED);
    }

    @Operation(summary = "Serviço responsável por buscar todas as promoções no sistema.", description = "Exemplo de descrição de um endpoint responsável por buscasr todas as promoções  no sistema.")
    @GetMapping
    public List<Promocao> listarTodos() {
        return promocaoService.listarTodos();
    }

   

    @Operation(summary = "Serviço responsável por deletar uma promoção no sistema pelo identificador.", description = "Exemplo de descrição de um endpoint responsável por deletar uma promoção no sistema pelo identificador.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        promocaoService.delete(id);
        return ResponseEntity.ok().build();
    }

}

