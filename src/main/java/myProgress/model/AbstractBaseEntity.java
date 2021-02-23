package myProgress.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public abstract class AbstractBaseEntity {
    protected Integer id;

    public boolean isNew() {
        return this.id == null;
    }
}
