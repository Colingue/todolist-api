package com.example.todolist_api.dto;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long id;

    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(min = 3, max = 100, message = "Le titre doit comporter entre 3 et 100 caractères")
    private String title;

    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;

    private boolean completed;

    @FutureOrPresent(message = "The deadline must be today or in the future")
    private LocalDate deadline;
}
