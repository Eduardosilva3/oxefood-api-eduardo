package br.com.ifpe.oxefood.api.entregador;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
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
public class EntregadorRequest {
    
   
    @NotNull(message = "O Nome é de preenchimento obrigatório")
    @NotBlank(message = "O Nome é de preenchimento obrigatório")
    @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
    private String nome;
    
     @NotNull(message = "O CPF é de preenchimento obrigatório")
   @NotBlank(message = "O CPF é de preenchimento obrigatório")
   @CPF
    private String cpf;
   
    private String rg;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    
    @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre {min} e {max} caracteres")
    private String foneCelular;

    @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre {min} e {max} caracteres")
    private String foneFixo;
   
    private Integer qtdEntregasRealizadas;

    private Double valorFrete;

    @NotNull(message = "O Nome é de preenchimento obrigatório")
    @NotBlank(message = "O Nome é de preenchimento obrigatório")
    private String enderecoRua;

    @NotNull(message = "O Numero do Endereço é de preenchimento obrigatório")
    @NotBlank(message = "O Numero é de preenchimento obrigatório")
    private String enderecoNumero;

    @NotNull(message = "O Bairro é de preenchimento obrigatório")
    @NotBlank(message = "O Bairro é de preenchimento obrigatório")
    private String enderecoBairro;

    @NotNull(message = "A cidade é de preenchimento obrigatório")
    @NotBlank(message = "A cidade é de preenchimento obrigatório")
    private String enderecoCidade;

    @NotNull(message = "O CEP é de preenchimento obrigatório")
    @NotBlank(message = "O CEP é de preenchimento obrigatório")
    private String enderecoCep;

    @NotNull(message = "O UF é de preenchimento obrigatório")
    @NotBlank(message = "O UF é de preenchimento obrigatório")
    private String enderecoUf;

    private String enderecoComplemento;

    @NotNull(message = " A Opção Ativo é de preenchimento obrigatório")
    @NotBlank(message = "A Opção Ativo é de preenchimento obrigatório")
    private Boolean ativo;

    public Entregador build(){

        return Entregador.builder()
                .nome(nome)
                .cpf(cpf)
                .rg(rg)
                .dataNascimento(dataNascimento)
                .foneCelular(foneCelular)
                .foneFixo(foneFixo)
                .qtdEntregasRealizadas(qtdEntregasRealizadas)
                .valorFrete(valorFrete)
                .enderecoRua(enderecoRua)
                .enderecoUf(enderecoUf)
                .enderecoComplemento(enderecoComplemento)
                .enderecoNumero(enderecoNumero)
                .enderecoBairro(enderecoBairro)
                .enderecoCidade(enderecoCidade)
                .enderecoCep(enderecoCep)
                .ativo(ativo)
                .build();
    }
}
