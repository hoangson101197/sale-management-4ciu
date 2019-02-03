package com.codegym.fourciumanagerprocesssale.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Không được để trống!")
    private String name;

    @NotNull(message = "Không được để trống!")
    private Long unitPrice;

    private String image;
}
