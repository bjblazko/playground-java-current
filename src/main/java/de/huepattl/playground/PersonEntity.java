package de.huepattl.playground;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "PERSON")
public class PersonEntity {
    @Id
    public long id;
    public String name;
    public boolean active;
    public LocalDate dateOfBirth;

}
