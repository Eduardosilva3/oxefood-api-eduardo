package br.com.ifpe.oxefood.api.produto;

import org.hibernate.validator.constraints.Length;

import br.com.ifpe.oxefood.modelo.produto.Produto;
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
public class ProdutoRequest  {

    
    @NotNull(message = "O ID da Categoria é de preenchimento obrigatório")
    @NotBlank(message = "O ID da Categoria é de preenchimento obrigatório")
    private Long idCategoria;

    @NotNull(message = "O codigo é de preenchimento obrigatório")
    @NotBlank(message = "O codigo é de preenchimento obrigatório")
    @Length(min = 10, max = 20, message = "O campo codigo tem que ter entre {min} e {max} caracteres")
    private String codigo;

    @NotNull(message = "O titulo é de preenchimento obrigatório")
    @NotBlank(message = "O titulo é de preenchimento obrigatório")
    private String titulo;

    @NotNull(message = "O descrição é de preenchimento obrigatório")
    @NotBlank(message = "O descrição é de preenchimento obrigatório")
    private String descricao;

    @NotNull(message = "O valor é de preenchimento obrigatório")
    @NotBlank(message = "O valor é de preenchimento obrigatório")
    private Double valorUnitario;

    @NotNull(message = "O tempo de entrega minimo é de preenchimento obrigatório")
    @NotBlank(message = "O tempo de entrega minimo obrigatório")
    private Integer tempoEntregaMinimo;

    @NotNull(message = "O tempo de entrega maximo é de preenchimento obrigatório")
    @NotBlank(message = "O tempo de entrega maximo preenchimento obrigatório")
    private Integer tempoEntregaMaximo;

    public Produto build(){
        return Produto.builder()
        .codigo(codigo)
        .descricao(descricao)
        .titulo(titulo)
        .valorUnitario(valorUnitario)
        .tempoEntregaMaximo(tempoEntregaMaximo)
        .tempoEntregaMinimo(tempoEntregaMinimo)
        .build();
    }
    
}
