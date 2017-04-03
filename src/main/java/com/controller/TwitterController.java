package com.controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Created by sowmyaparameshwara on 4/2/17.
 */
@Controller
@RequestMapping("/twitter")
public class TwitterController {

    private Twitter twitter;
    private ConnectionRepository connectionRepository;

    @Inject
    public TwitterController(Twitter twitter,ConnectionRepository connectionRepository){
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public String helloTwitter(Model model){
        if(connectionRepository.findPrimaryConnection(Twitter.class)==null){
            return "redirect:/connect/twitter";
        }
        model.addAttribute(twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        model.addAttribute("friends", friends);
        return "twitter";

    }
}
