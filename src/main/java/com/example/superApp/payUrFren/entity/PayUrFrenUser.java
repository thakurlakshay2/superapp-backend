package com.example.superApp.payUrFren.entity;
import com.example.superApp.userService.entity.AbstractBaseUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "payurfren_users")
@Data
@EqualsAndHashCode(callSuper = true)
public class PayUrFrenUser  extends AbstractBaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "default_currency", nullable = false)
    private String defaultCurrency = "INR";

    // PayUrFren-specific relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupMember> groupMemberships = new HashSet<>();

    // Additional PayUrFren-specific fields
    @Column(name = "total_balance", precision = 19, scale = 4)
    private BigDecimal totalBalance = BigDecimal.ZERO;


    @Column(name = "notification_preferences")
    @Enumerated(EnumType.STRING)
    private NotificationPreference notificationPreference = NotificationPreference.ALL;

    public enum NotificationPreference {
        ALL, IMPORTANT_ONLY, NONE
    }
}
