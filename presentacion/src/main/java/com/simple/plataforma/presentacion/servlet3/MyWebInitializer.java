package com.simple.plataforma.presentacion.servlet3;

/**
 * Created by ecandela on 23/08/2015.
 */

//import com.simple.plataforma.presentacion.config.SpringDataConfig;

import com.simple.plataforma.infraestructura.config.SpringDataConfig;
import com.simple.plataforma.presentacion.config.SpringRootConfig;
import com.simple.plataforma.presentacion.config.SpringWebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class MyWebInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SpringRootConfig.class,SpringDataConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { SpringWebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}