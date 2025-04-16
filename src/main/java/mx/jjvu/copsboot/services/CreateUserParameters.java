package mx.jjvu.copsboot.services;

import mx.jjvu.copsboot.utility.id.AuthServerId;

public record CreateUserParameters(AuthServerId authServerId, String email, String mobileToken) {}
