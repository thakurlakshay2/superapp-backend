package com.example.superApp.payUrFren.mapper;

import com.example.superApp.payUrFren.dto.BaseUserDTO;
import com.example.superApp.payUrFren.dto.CreateUserDTO;
import com.example.superApp.payUrFren.dto.UserDTOResponse;
import com.example.superApp.payUrFren.entity.User;
import org.springframework.stereotype.Component;

/**
 * Manual implementation of User mapping logic, equivalent to the
 * original UserMapper MapStruct interface.
 */
@Component // Make it a Spring component, like componentModel="spring"
public class UserMapper {

    /**
     * Maps a User entity to a BaseUserDTO.
     * Corresponds to: BaseUserDTO toBaseUserDTO(User user);
     * Ignores the password field.
     *
     * @param user The User entity to map.
     * @return A BaseUserDTO object, or null if the input is null.
     */
    public BaseUserDTO toBaseUserDTO(User user) {
        if (user == null) {
            return null;
        }

        BaseUserDTO baseUserDTO = new BaseUserDTO();

        // Map common fields
        baseUserDTO.setEmail(user.getEmail());
        baseUserDTO.setUsername(user.getUsername());

        // baseUserDTO.setPassword(user.getPassword()); // Ignored as per @Mapping(target = "password", ignore = true)

        return baseUserDTO;
    }


    public UserDTOResponse toUserDTOResponse(User user) {
        if (user == null) {
            return null;
        }

        UserDTOResponse baseUserDTO = new UserDTOResponse();

        // Map common fields
        baseUserDTO.setUsername(user.getUsername());
        baseUserDTO.setPhone(user.getPhone());
        baseUserDTO.setEmail(user.getEmail());
        baseUserDTO.setCreatedAt(user.getCreatedAt());
        baseUserDTO.setUpdatedAt(user.getUpdatedAt());


        // baseUserDTO.setPassword(user.getPassword()); // Ignored as per @Mapping(target = "password", ignore = true)

        return baseUserDTO;
    }


    /**
     * Maps a CreateUserDTO to a new User entity.
     * Corresponds to: User toBaseUser(CreateUserDTO userDTO);
     * Ignores id, createdAt, and updatedAt fields.
     *
     * @param userDTO The CreateUserDTO to map from.
     * @return A new User entity, or null if the input is null.
     */
    public User toBaseUser(CreateUserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();

        // Map fields from DTO
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Password is mapped from CreateUserDTO

        // user.setId(...);          // Ignored as per @Mapping(target = "id", ignore = true)
        // user.setCreatedAt(...);   // Ignored as per @Mapping(target = "createdAt", ignore = true)
        // user.setUpdatedAt(...);   // Ignored as per @Mapping(target = "updatedAt", ignore = true)

        return user;
    }

    /**
     * Updates an existing User entity from a BaseUserDTO.
     * Corresponds to: void updateBaseUserFromDTO(BaseUserDTO userDTO, @MappingTarget User user);
     * Ignores null values from the DTO (NullValuePropertyMappingStrategy.IGNORE).
     * Ignores password, id, and createdAt fields.
     *
     * @param userDTO The BaseUserDTO containing updated data.
     * @param user    The existing User entity to update (MappingTarget).
     */
    public void updateBaseUserFromDTO(BaseUserDTO userDTO, User user) {
        if (userDTO == null || user == null) {
            return; // Do nothing if either input is null
        }

        // Apply updates only if the source property is not null
        // Simulates NullValuePropertyMappingStrategy.IGNORE

        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }


        // user.setPassword(...);   // Ignored as per @Mapping(target = "password", ignore = true)
        // user.setId(...);         // Ignored as per @Mapping(target = "id", ignore = true)
        // user.setCreatedAt(...);  // Ignored as per @Mapping(target = "createdAt", ignore = true)
        // Note: The original MapStruct config also ignored updatedAt in this specific method.
        // Typically, updatedAt would be set here or by the persistence layer.
    }
}