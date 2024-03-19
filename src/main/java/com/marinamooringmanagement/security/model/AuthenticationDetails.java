package com.marinamooringmanagement.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDetails implements Serializable {
    private static final long serialVersionUID = 550269061132507976L;

    private Integer loggedInUserId;
    private String loggedInUserEmail;
    private String loggedInUserRole;
}
