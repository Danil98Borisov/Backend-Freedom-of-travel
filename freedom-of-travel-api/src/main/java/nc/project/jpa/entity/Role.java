package nc.project.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import nc.project.const_enum.ERole;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;

    public Role(Long id, ERole name) {
        this.id = id;
        this.name = name;
    }

}

