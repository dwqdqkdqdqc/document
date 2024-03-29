package ru.sitronics.tn.document.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(callSuper = true, exclude = {"password"})
@Table(name = "nci_users")
public class NciUser extends BaseEntity {
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "organization")
    private String organization;
/*

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "nameRole"}, name = "user_roles_unique")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;
*/
    @Column(name = "role")
    private String role;
    @Column(name = "personal_phone_number")
    private String personalPhoneNumber;
    @Column(name = "work_phone_number")
    private String workPhoneNumber;
    @Column(name = "mail")
    private String mail;
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "ip_address")
    private String ipAdress;
    @Column(name = "event")
    private String event;
    @Column(name = "work_permission")
    private Boolean workPermission;
    @Column(name = "user_tasks")
    private String userTasks;
    @OneToMany(mappedBy = "author")
    @JsonBackReference(value = "author")
    private List<Document> createdDocuments = new java.util.ArrayList<>();
}


