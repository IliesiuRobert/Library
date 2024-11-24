package Mapper;

import Model.Builder.UserBuilder;
import Model.User;
import View.Model.Builder.UserDTOBuilder;
import View.Model.UserDTO;

import java.util.*;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO convertUserToUserDTO(User user) {
        return new UserDTOBuilder().setUsername(user.getUsername()).build();
    }

    public static User convertUserDTOToUser(UserDTO userDTO) {
        return new UserBuilder().setUsername(userDTO.getUsername()).build();
    }

    public static List<UserDTO> convertUserListToUserDTOList(List<User> users) {
        return users.stream()
                .map(UserMapper::convertUserToUserDTO)
                .collect(Collectors.toList());
    }

    public static List<User> convertUserDTOToUserList(List<UserDTO> userDTOS) {
        return userDTOS.stream()
                .map(UserMapper::convertUserDTOToUser)
                .collect(Collectors.toList());
    }

}
