package com.itqpleyva.spring_events.EventsController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itqpleyva.spring_events.EventManagement.CustomEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/events")
public class eventController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping(value="/event1")
    public String event1() {

        CustomEvent event = new CustomEvent(this);

        publisher.publishEvent(event);

        return "hello from method 1";
    }
    
}