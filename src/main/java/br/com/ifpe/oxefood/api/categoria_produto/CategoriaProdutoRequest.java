package br.com.ifpe.oxefood.api.categoria_produto;

import org.hibernate.validator.constraints.Length;

import br.com.ifpe.oxefood.modelo.categoriaproduto.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProdutoRequest {

    @NotNull(message = "A Descrição é obrigatório")
    @NotBlank(message = "A Descrição é obrigatório")
    @Length(max = 100, message = "A Descrição deverá ter no máximo {max} caracteres")
    private String descricao;


    public CategoriaProduto build(){
        return CategoriaProduto.builder()
        .descricao(descricao)
        .build();
    }
    
}

