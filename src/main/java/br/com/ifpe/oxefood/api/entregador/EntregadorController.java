package br.com.ifpe.oxefood.api.entregador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import br.com.ifpe.oxefood.modelo.entregador.EntregadorService;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/api/entregador")
@RestController
@CrossOrigin
public class EntregadorController {
    @Autowired
    private EntregadorService service;

    @Operation(summary = "Serviço responsável por salvar um entregador no sistema.", description = "Exemplo de descrição de um endpoint responsável por inserir um entregador no sistema.")
    @PostMapping
    public ResponseEntity<Entregador> save(@RequestBody EntregadorRequest request) {

        Entregador entregador = service.save(request.build());
        return new ResponseEntity<Entregador>(entregador, HttpStatus.CREATED);
    }

    @Operation(summary = "Serviço responsável por buscar todos os entregadores no sistema.", description = "Exemplo de descrição de um endpoint responsável por buscar todos os entregadores no sistema.")
    @GetMapping
    public List<Entregador> listarTodos() {
        return service.listarTodos();
    }

    @Operation(summary = "Serviço responsável por buscar um Entregador no sistema pelo identificador.", description = "Exemplo de descrição de um endpoint responsável por buscar um Entregador no sistema pelo identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<Object> obterPorID(@PathVariable Long id) {
        Entregador entregador = service.obterPorID(id);
        if (entregador == null) {
            return new ResponseEntity<Object>("Entregador não Existe!", HttpStatus.OK);
        }

        return new ResponseEntity<Object>(entregador, HttpStatus.OK);

    }

    @Operation(summary = "Serviço responsável por deletar um Entregador no sistema pelo identificador.", description = "Exemplo de descrição de um endpoint responsável por deletar um Entregador no sistema pelo identificador.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
