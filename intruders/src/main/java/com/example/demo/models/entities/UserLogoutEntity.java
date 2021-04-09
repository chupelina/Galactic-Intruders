package com.example.demo.models.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

@Entity
@Table(name = "last_logouts")
public class UserLogoutEntity  extends BaseEntity {
    @OneToOne
    private UserEntity user;
    private LocalDateTime logoutDate;

    public UserEntity getUser() {
        return user;
    }

    public UserLogoutEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getLogoutDate() {
        return logoutDate;
    }

    public UserLogoutEntity setLogoutDate(LocalDateTime logoutDate) {
        this.logoutDate = logoutDate;
        return this;
    }
}
