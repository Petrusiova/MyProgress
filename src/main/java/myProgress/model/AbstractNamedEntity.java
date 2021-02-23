package myProgress.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @Getter
    @Setter
    protected String name;

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

}