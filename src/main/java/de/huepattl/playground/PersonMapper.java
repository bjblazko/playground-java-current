package de.huepattl.playground;

public class PersonMapper {
    public static Person fromEntityToDto(PersonEntity entity) {
        return new Person(entity.id, entity.name, entity.active, entity.dateOfBirth);
    }

    public static PersonEntity fromDtoToEntity(Person dto) {
        var entity = new PersonEntity();
        entity.id = dto.id();
        entity.name = dto.name();
        entity.active = dto.active();
        entity.dateOfBirth = dto.dateOfBirth();
        return entity;
    }
}
