package com.defaulty.autopark.service.security;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);


}
