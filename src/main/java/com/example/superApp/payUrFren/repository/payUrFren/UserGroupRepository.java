package com.example.superApp.payUrFren.repository.payUrFren;

import com.example.superApp.payUrFren.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserGroupRepository  extends JpaRepository<UserGroup, UUID> {
}
