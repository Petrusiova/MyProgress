package myProgress.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Getter
@Setter
@ToString
public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles;

    private Set<Integer> accessUserIds;

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Set<Integer> accessUserIds, Role role, Role... roles) {
        this(id, name, email, password, true, accessUserIds, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
        this(id, name, email, password, enabled, null, roles);
    }

    public User(Integer id, String name, String email, String password, boolean enabled,
                Set<Integer> accessUserIds, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.accessUserIds = accessUserIds;
        setRoles(roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public void setAccessUserIds(Integer...accessUserIds) {
        this.accessUserIds = Set.of(accessUserIds);
    }
}
