package com.simple.plataforma.infraestructura.data.listeners;


import com.simple.plataforma.infraestructura.data.AuthenticationMockup;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Created by ecandela on 30/08/2015.
 */
@Component
public class CustomAuditorAware implements AuditorAware<String>
{

    @Override
    public String getCurrentAuditor()
    {
        return AuthenticationMockup.UserName;
    }

}