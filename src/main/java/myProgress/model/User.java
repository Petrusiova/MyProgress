package myProgress.model;

import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles;

    private Set<Integer> accessUserIds;

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRegistered(), u.getRoles(), u.getAccessUserIds());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Set<Integer> accessUserIds, Role role, Role... roles) {
        this(id, name, email, password, true, new Date(), EnumSet.of(role, roles), accessUserIds);
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
        this(id, name, email, password, enabled, new Date(), roles, Set.of());
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Date registered,
                 Collection<Role> roles, Set<Integer> accessUserIds) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
        addAccessUserIds(accessUserIds.toArray(Integer[]::new));
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public void addAccessUserIds(Integer...accessUserIds) {
        this.accessUserIds.addAll(Collections.singletonList(this.getId()));
        this.accessUserIds.addAll(Arrays.asList(accessUserIds));

    }
}
