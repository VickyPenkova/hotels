package com.java.nbu.hotels.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {
   @RequestMapping(value = {"/adminPage"}, method = RequestMethod.GET)
   public ModelAndView adminPage() {
      ModelAndView model = new ModelAndView();
      model.setViewName("admin");
      return model;
   }
}
