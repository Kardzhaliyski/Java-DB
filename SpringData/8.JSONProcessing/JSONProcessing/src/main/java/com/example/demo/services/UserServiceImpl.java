package com.example.demo.services;

import com.example.demo.entities.users.User;
import com.example.demo.entities.users.UserWithSellsDTO;
import com.example.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements com.example.demo.services.UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Random random;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        random = new Random();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getRandomUser() {
        long userCount = userRepository.count();
        int randomUserNumber = random.nextInt((int) userCount);
        Page<User> userPage = userRepository.findAll(PageRequest.of(randomUserNumber, 1));

        if (userPage.hasContent()) {
            return userPage.getContent().get(0);
        }

        return null;
    }

    @Transactional
    public List<UserWithSellsDTO> getAllUsersWithSells() {
        List<User> usersWithSoldProducts = userRepository.findAllBySellingProductsBuyerIsNotNull();
        //        UserWithSellsDTO[] map = modelMapper.map(usersWithSoldProducts, UserWithSellsDTO[].class);
        TypeMap<User, UserWithSellsDTO> typeMap = modelMapper.createTypeMap(User.class, UserWithSellsDTO.class);
        typeMap.addMapping(user -> user.getSoldProducts(),
//                                    .stream()
//                                    .filter(p -> p.getBuyer() != null)
//                                    .collect(Collectors.toList()),
                            UserWithSellsDTO::setSoldProducts);
        System.out.println();
        return usersWithSoldProducts
                .stream()
//                .map(u -> modelMapper.)
                .map(typeMap::map)
                .collect(Collectors.toList());
    }

}
