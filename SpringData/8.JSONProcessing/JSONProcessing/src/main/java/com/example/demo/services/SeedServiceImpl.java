package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.UserImportDTO;
import com.example.demo.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final static String USER_SEED_PATH = "src/main/resources/users.json";
    private final UserRepository userRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public SeedServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
         gson = new Gson().newBuilder().setPrettyPrinting().create();
         modelMapper = new ModelMapper();
         modelMapper.getConfiguration()
                 .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                 .setFieldMatchingEnabled(true);
    }

    @Override
    public void seedUsers() {
        UserImportDTO[] userImportDTOS;
        try (Reader seedFile = new FileReader(USER_SEED_PATH)) {
            userImportDTOS = gson.fromJson(seedFile, UserImportDTO[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<User> users = Arrays.stream(userImportDTOS).map(importDTO -> modelMapper.map(importDTO, User.class))
                .collect(Collectors.toList());
        users.forEach(userRepository::save);
    }

    @Override
    public void seedCatorogies() {

    }

    @Override
    public void seedProducts() {

    }
}
