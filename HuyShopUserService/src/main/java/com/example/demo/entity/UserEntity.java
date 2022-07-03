package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_tb")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "dob")
    Date dob;
    @Column(name = "phone")
    String phone;
    @Column(name = "email")
    String email;
    @Column(name = "address")
    String address;
    @Column(name = "status")
    boolean status;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "pass_word")
    private String passWord;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleId;

//    @OneToOne(mappedBy = "user")
//    private Cart cart;

    @PrePersist
    void onPrePersist() {
        if (status==false) {
            status=true;
        }
    }
}
