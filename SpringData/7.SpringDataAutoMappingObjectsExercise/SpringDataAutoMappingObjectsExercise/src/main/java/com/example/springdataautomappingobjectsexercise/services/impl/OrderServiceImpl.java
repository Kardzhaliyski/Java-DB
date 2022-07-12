package com.example.springdataautomappingobjectsexercise.services.impl;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.entities.Order;
import com.example.springdataautomappingobjectsexercise.models.entities.User;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuDelimiter;
import com.example.springdataautomappingobjectsexercise.repositories.OrderRepository;
import com.example.springdataautomappingobjectsexercise.services.OrderService;
import com.example.springdataautomappingobjectsexercise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private Order order;
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public void startOrder(User user) {
        order = new Order(user);
    }

    @Override
    public void addGame(Game game) {
        boolean alreadyOwn = order.getUser().ownGame(game.getId());
        if(alreadyOwn) {
            System.out.println("You already own game: " + game.getTitle());
            return;
        }

        try {
            order.addGame(game);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeGame(Long id) {
        order.removeGame(id);
    }

    @Override
    public void printCart() {
        System.out.println(MenuDelimiter.LONG_LINE);
        order.getGames().values().forEach(System.out::println);
        System.out.println(MenuDelimiter.SHORT_LINE + "Total price: " + order.getTotalPrice());
    }

    @Override
    public void finishOrder() {
        order.setFinished();
        orderRepository.save(order);
        for (Game game : order.getGames().values()) {
            order.getUser().addGameToOwned(game);
            System.out.printf(MenuDelimiter.SHORT_LINE + "Game: %s added to collection!%n",
                    game.getTitle());
        }
        userService.save(order.getUser());
        order = null;
    }


}
