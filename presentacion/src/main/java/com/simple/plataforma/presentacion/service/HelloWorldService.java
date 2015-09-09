package com.simple.plataforma.presentacion.service;


import com.simple.plataforma.infraestructura.data.AuthenticationMockup;
import com.simple.plataforma.dominio.modelo.Country;
import com.simple.plataforma.infraestructura.data.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by ecandela on 23/08/2015.
 */
@Service
public class HelloWorldService {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

    @Autowired
    private CountryRepository countryRepository;

    public String getDesc() {

        logger.debug("getDesc() is executed!");

        return "Gradle + Spring MVC Hello World Example";

    }

    public void cargarData() {

        AuthenticationMockup.UserName = "dani";

        // empty repository
        countryRepository.deleteAllInBatch();

        // insert
        countryRepository.save(new Country("Spain", 47265321));
        countryRepository.save(new Country("Mexico", 115296767));
        countryRepository.save(new Country("Germany", 81799600));
        countryRepository.save(new Country("Finland", 5470820));
        countryRepository.save(new Country("Colombia", 47846160));
        countryRepository.save(new Country("Costa Rica", 4586353));
        countryRepository.save(new Country("Norway", 5136700));


    }

    public String getTitle(String name) {

        logger.debug("getTitle() is executed! $name : {}", name);

        if(StringUtils.isEmpty(name)){
            return "Hello World";
        }else{
            return "Hello " + name;
        }

    }

}
