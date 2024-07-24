package br.com.ifpe.oxefood.api.promocao;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.promocao.Promocao;
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
public class PromocaoRequest {


    @NotNull(message = "O titulo é de preenchimento obrigatório")
    @NotBlank(message = "O titulo é de preenchimento obrigatório")
    private String titulo;

    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;


    @NotNull(message = "A regra é de preenchimento obrigatório")
    @NotBlank(message = "A Regra é de preenchimento obrigatório")
    private String regra;

    @NotNull(message = "O valor de desconto é de preenchimento obrigatório")
    @NotBlank(message = "O valor de desconto é de preenchimento obrigatório")
    private String valorDesconto;



    public Promocao build(){
        return Promocao.builder()
        .titulo(titulo)
        .dataInicio(dataInicio)
        .dataFim(dataFim)
        .regra(regra)
        .valorDesconto(setMoney())
        .build();
    }

    private Double setMoney(){

        
        if(valorDesconto!=null){
            String desconto = valorDesconto.replace(",", ".");
            return Double.parseDouble(desconto);
        }

        return 0.0;
    }





    
}
