package com.codeWithMerald.RoyalSealLearningSystem.models.user;

import com.codeWithMerald.RoyalSealLearningSystem.models.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends DateAudit {
    @NonNull

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    private String email;

    @JsonIgnoreProperties
    @Size(min = 6)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Role> roles;

    @JsonIgnore
    private String emailVerificationToken;

    @JsonIgnore
    private EmailVerificationStatus emailVerificationStatus = EmailVerificationStatus.UNVERIFIED;

}
