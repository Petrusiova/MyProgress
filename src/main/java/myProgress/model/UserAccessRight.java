package myProgress.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_access_rights")
public class UserAccessRight extends AbstractBaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User user;

    @Column(name = "access_right")
    private int accessRight;

    @Override
    public String toString() {
        return "UserAccessRight{" +
                "accessRight=" + accessRight +
                ", id=" + id +
                '}';
    }
}
