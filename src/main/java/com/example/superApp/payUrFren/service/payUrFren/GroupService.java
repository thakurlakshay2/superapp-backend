package com.example.superApp.payUrFren.service.payUrFren;
import com.example.superApp.payUrFren.dto.CreateGroupDTO;
import com.example.superApp.payUrFren.entity.Group;
import com.example.superApp.payUrFren.entity.User;
import com.example.superApp.payUrFren.entity.UserGroup;
import com.example.superApp.payUrFren.exception.NotFoundException;
import com.example.superApp.payUrFren.repository.payUrFren.GroupRepository;
import com.example.superApp.payUrFren.repository.payUrFren.UserGroupRepository;
import com.example.superApp.payUrFren.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GroupService {
    // inject repositories
    private  final GroupRepository groupRepository;
    private final UserGroupRepository   userGroupRepository;
    private final UserService userService;

    public Group createGroup(CreateGroupDTO dto) {
        if (dto == null) throw new IllegalArgumentException("Group data must not be null");
        if (dto.getName() == null || dto.getName().isBlank()) throw new IllegalArgumentException("Group name must not be empty");
        if (dto.getMemberIds() == null || dto.getMemberIds().isEmpty()) throw new IllegalArgumentException("Member list must not be empty");

        Group group = new Group();
        group.setName(dto.getName());
        group = groupRepository.save(group);

        for (UUID userId : dto.getMemberIds()) {
            User user = userService.getUser(userId).orElseThrow(() -> new NotFoundException("User", userId));
            UserGroup ug = new UserGroup();
            ug.setUser(user);
            ug.setGroup(group);
            userGroupRepository.save(ug);
        }
        return group;
    }

    public Optional<Group> getGroup(UUID id ){
        return groupRepository.findById(id);
    }
}
