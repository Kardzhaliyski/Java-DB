package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;
import com.example.springdataautomappingobjectsexercise.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final ReaderService reader;
    private final Validator validator;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, ReaderService reader, Validator validator) {
        this.publisherRepository = publisherRepository;
        this.reader = reader;
        this.validator = validator;
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


    @Override
    public Publisher newPublisher(String name) {
        Publisher publisher = new Publisher(name);
        publisherRepository.save(publisher);
        return publisher;
    }

    public String readName() {
        while (true) {
            System.out.print("Enter publisher name: ");
            String name = reader.nextLine().trim();
            Set<ConstraintViolation<Publisher>> violations = validator.validateValue(Publisher.class, "name", name);
            if (violations.size() > 0) {
                violations.forEach(v -> System.out.println(v.getMessage()));
                continue;
            }
            return name;
        }
    }
}
