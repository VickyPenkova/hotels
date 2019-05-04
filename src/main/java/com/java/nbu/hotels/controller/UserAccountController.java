package com.java.nbu.hotels.controller;

import com.java.nbu.hotels.entities.ConfirmationToken;
import com.java.nbu.hotels.entities.UserEntity;
import com.java.nbu.hotels.repository.ConfirmationTokenRepository;
import com.java.nbu.hotels.repository.UserRepository;
import com.java.nbu.hotels.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserAccountController {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private ConfirmationTokenRepository confirmationTokenRepository;

   @Autowired
   private EmailSenderService emailSenderService;

   @RequestMapping(value="/register", method = RequestMethod.GET)
   public ModelAndView displayRegistration(ModelAndView modelAndView, UserEntity user)
   {
      modelAndView.addObject("userEntity", user);
      modelAndView.setViewName("register");
      return modelAndView;
   }

   @RequestMapping(value="/register", method = RequestMethod.POST)
   public ModelAndView registerUser(ModelAndView modelAndView, UserEntity user)
   {

      UserEntity existingUser = userRepository.findByEmailIdIgnoreCase(user.getEmail());
      if(existingUser != null)
      {
         modelAndView.addObject("message","This email already exists!");
         modelAndView.setViewName("error");
      }
      else
      {
         userRepository.save(user);

         ConfirmationToken confirmationToken = new ConfirmationToken(user);

         confirmationTokenRepository.save(confirmationToken);

         SimpleMailMessage mailMessage = new SimpleMailMessage();
         mailMessage.setTo(user.getEmail());
         mailMessage.setSubject("Complete Registration!");
         mailMessage.setFrom("chand312902@gmail.com");
         mailMessage.setText("To confirm your account, please click here : "
               +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());

         emailSenderService.sendEmail(mailMessage);

         modelAndView.addObject("email", user.getEmail());

         modelAndView.setViewName("successfulRegisteration");
      }

      return modelAndView;
   }

   @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
   public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
   {
      ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

      if(token != null)
      {
         UserEntity user = userRepository.findByEmailIdIgnoreCase(token.getUser().getEmail());
         user.setEnabled(true);
         userRepository.save(user);
         modelAndView.setViewName("accountVerified");
      }
      else
      {
         modelAndView.addObject("message","The link is invalid or broken!");
         modelAndView.setViewName("error");
      }

      return modelAndView;
   }
   // getters and setters
}
