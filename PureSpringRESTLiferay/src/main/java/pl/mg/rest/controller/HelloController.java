package pl.mg.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(method = RequestMethod.GET, value = "/hello/{name}")
	public String getHello(@PathVariable String name) {
		return "hello spring 4=" + name;
	}

}
