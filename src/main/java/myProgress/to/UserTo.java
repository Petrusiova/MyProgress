package myProgress.to;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserTo {

    private Integer id;

    private String name;

    private String email;

    private String password;
}
