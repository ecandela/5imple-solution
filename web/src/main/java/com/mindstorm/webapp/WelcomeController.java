package com.mindstorm.webapp;

import com.mindstorm.service.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Created by ecandela on 23/08/2015.
 */
@Controller
public class WelcomeController {


    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    private final HelloWorldService helloWorldService;

    @Autowired
    public WelcomeController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        logger.debug("index() is executed!");

        helloWorldService.cargarData();

        model.put("title", helloWorldService.getTitle(""));
        model.put("msg", helloWorldService.getDesc());
        model.put("prueba", "Erik 2");

        return "index";
    }

    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        logger.debug("hello() is executed - $name {}", name);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        model.addObject("title", helloWorldService.getTitle(name));
        model.addObject("msg", helloWorldService.getDesc());

        return model;

    }



    @RequestMapping(value = "/persons/{name}", method = RequestMethod.GET)
    public @ResponseBody
    PersonWrapper getPerson(@PathVariable String name) {
        PersonWrapper wrapper = new PersonWrapper();
        wrapper.setAge(18);
        wrapper.setName(name);
        addSelfLink(wrapper);


        return wrapper;
    }


    private void addSelfLink(PersonWrapper resource){
        final PersonWrapper person = methodOn(WelcomeController.class).getPerson(resource.getName());
        final Link link = linkTo(person).withSelfRel();
        resource.add(link);
    }

}
