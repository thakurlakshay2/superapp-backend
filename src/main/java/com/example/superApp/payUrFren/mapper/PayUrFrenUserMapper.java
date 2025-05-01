package com.example.superApp.payUrFren.mapper;


import com.example.superApp.payUrFren.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct mapper for converting between BaseUser and PayUrFrenUser entities
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PayUrFrenUserMapper {

    /**
     * Converts a BaseUser to a new PayUrFrenUser
     *
     * @param user The source BaseUser
     * @return A new PayUrFrenUser instance with mapped fields
     */
    @Mapping(target = "accountBalance", constant = "0.0")
    @Mapping(target = "paymentMethodsAdded", constant = "false")
    @Mapping(target = "friendsCount", constant = "0")
    User toPayUrFrenUser(User user);

    /**
     * Updates an existing PayUrFrenUser with data from a BaseUser
     *
     * @param user The source BaseUser with updated data
     * @param payUrFrenUser The target PayUrFrenUser to update
     */
    @Mapping(target = "accountBalance", ignore = true)
    @Mapping(target = "paymentMethodsAdded", ignore = true)
    @Mapping(target = "friendsCount", ignore = true)
    void updatePayUrFrenUserFromBaseUser(User user, @MappingTarget User payUrFrenUser);

    /**
     * Maps only basic user information from PayUrFrenUser to BaseUser
     * Note: Password and authentication fields are not mapped
     *
     * @param payUrFrenUser The source PayUrFrenUser
     * @return A basic BaseUser (likely needs additional processing for authentication)
     */
    @Mapping(target = "password", ignore = true)
    User toBaseUser(User payUrFrenUser);
}