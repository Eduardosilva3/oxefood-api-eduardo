package br.com.ifpe.oxefood.modelo.entregador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador,Long>{
    
}
