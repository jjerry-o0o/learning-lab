package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MBR")
public class Member2 {

    @Id
    private Long id;

    @Column(name = "name")
    private Long username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;

    public Member2() {}
}
