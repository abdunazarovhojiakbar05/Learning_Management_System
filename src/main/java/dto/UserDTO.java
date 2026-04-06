package dto;

import enums.Status;
import enums.Status2;
import enums.UserRole;

public record UserDTO (String id, String name, String email, String password, Status status, Status2 status2, UserRole userRole) {
}

