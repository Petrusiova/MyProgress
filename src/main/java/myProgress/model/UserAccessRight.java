package myProgress.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_access_rights")
public class UserAccessRight extends AbstractBaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private User user;

    @Column(name = "access_right")
    private int accessRight;

    public UserAccessRight(int accessRight) {
        this(null, accessRight);
    }

    public UserAccessRight(Integer id, int accessRight) {
        super(id);
        this.accessRight = accessRight;
    }

    @Override
    public String toString() {
        return "UserAccessRight{" +
                "accessRight=" + accessRight +
                ", id=" + id +
                '}';
    }
}
