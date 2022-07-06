package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;
import com.example.springdataautomappingobjectsexercise.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final ReaderService reader;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, ReaderService reader) {
        this.publisherRepository = publisherRepository;
        this.reader = reader;
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
    public Boolean validName(String name) {
        if(name == null || name.isBlank()) {
            return false;
        }
        return true;
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
            String name = reader.nextLine();
            if (!validName(name)) {
                System.out.println("Invalid publisher name: " + name);
                continue;
            }
            return name;
        }
    }
}
