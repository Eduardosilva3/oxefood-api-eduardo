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

@RequestMapping("/api/entregador")
@RestController
@CrossOrigin
public class EntregadorController {
    @Autowired
   private EntregadorService service;

   @PostMapping
   public ResponseEntity<Entregador> save(@RequestBody EntregadorRequest request) {

       Entregador entregador = service.save(request.build());
       return new ResponseEntity<Entregador>(entregador, HttpStatus.CREATED);
   }

   
    @GetMapping
    public List<Entregador> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obterPorID(@PathVariable Long id) {
        Entregador entregador = service.obterPorID(id);
        if(entregador ==null){
            return new ResponseEntity<Object>("Entregador não Existe!", HttpStatus.OK);
        }

        return new ResponseEntity<Object>(entregador, HttpStatus.OK);
 
    }

    @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
       return ResponseEntity.ok().build();
   }
}

