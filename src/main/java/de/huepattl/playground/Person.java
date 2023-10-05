package de.huepattl.playground;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.Id;

import java.time.LocalDate;

@JsonbPropertyOrder({"id", "name"})
public record Person(
        @Id
        long id,
        String name,
        boolean active,
        LocalDate dateOfBirth
) { }
