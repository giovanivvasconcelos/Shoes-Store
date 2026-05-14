package br.com.shoestore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shoes")
public class Shoe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Marca é obrigatória")
    private String brand;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private Double price;

    @NotNull(message = "Tamanho é obrigatório")
    @Min(value = 30, message = "Tamanho mínimo é 30")
    @Max(value = 50, message = "Tamanho máximo é 50")
    private Integer size;

    @NotBlank(message = "Cor é obrigatória")
    private String color;
}
