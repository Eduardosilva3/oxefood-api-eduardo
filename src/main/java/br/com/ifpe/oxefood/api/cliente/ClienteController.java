package br.com.ifpe.oxefood.api.cliente;

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
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Serviço responsável por salvar um cliente no sistema.", description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema.")
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody ClienteRequest request) {

        Cliente cliente = clienteService.save(request.build());
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    @Operation(summary = "Serviço responsável por buscar todos os cliente no sistema.", description = "Exemplo de descrição de um endpoint responsável por buscasr todos os clientes no sistema.")
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @Operation(summary = "Serviço responsável por buscar um cliente no sistema pelo identificador.", description = "Exemplo de descrição de um endpoint responsável por buscar um cliente no sistema pelo identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<Object> obterPorID(@PathVariable Long id) {
        Cliente cliente = clienteService.obterPorID(id);
        if (cliente == null) {
            return new ResponseEntity<Object>("Cliente não Existe!", HttpStatus.OK);
        }

        return new ResponseEntity<Object>(cliente, HttpStatus.OK);

    }

    @Operation(summary = "Serviço responsável por deletar um cliente no sistema pelo identificador.", description = "Exemplo de descrição de um endpoint responsável por deletar um cliente no sistema pelo identificador.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

}
