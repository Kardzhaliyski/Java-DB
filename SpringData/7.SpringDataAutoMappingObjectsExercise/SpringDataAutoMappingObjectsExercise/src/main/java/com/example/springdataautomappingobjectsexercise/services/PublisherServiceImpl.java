package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;
import com.example.springdataautomappingobjectsexercise.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Boolean existsByName(String name) {
        return publisherRepository.existsByName(name);
    }

    @Override
    public Boolean exists(Publisher publisher) {
        return publisherRepository.exists(Example.of(publisher));
    }

    @Override
    public Publisher getByName(String name) {
        return publisherRepository.findPublisherByName(name);
    }

    @Override
    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }
}
