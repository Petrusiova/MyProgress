package myProgress.model;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserAccessRight> accessUserIds;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("date DESC")
    private List<Measurement> measurements;

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRegistered(), u.getRoles(), u.getAccessUserIds());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Set<UserAccessRight> accessUserIds, Role role, Role... roles) {
        this(id, name, email, password, true, new Date(), EnumSet.of(role, roles), accessUserIds);
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
        this(id, name, email, password, enabled, new Date(), roles, Set.of());
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Date registered,
                 Collection<Role> roles, Set<UserAccessRight> accessUserIds) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
//        addAccessUserIds(accessUserIds.toArray(Integer[]::new));
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public User addAccessUserIds(User user, int[] accessUserIds) {
//        if (user.getAccessUserIds() != null && user.getAccessUserIds().contains())
        user.accessUserIds.addAll(Collections.singletonList(new UserAccessRight(this, this.getId())));
        Arrays.stream(accessUserIds).forEach(item ->
                user.accessUserIds.addAll(Collections.singletonList(new UserAccessRight(this, item))));
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", accessUserIds=" + accessUserIds +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
