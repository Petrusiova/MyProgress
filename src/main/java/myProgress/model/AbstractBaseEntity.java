package myProgress.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractBaseEntity {
    public static final int START_SEQ = 100000;

    protected Integer id;

    public boolean isNew() {
        return this.id == null;
    }
}
