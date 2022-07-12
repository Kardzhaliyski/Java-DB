package com.example.springdataautomappingobjectsexercise.services.impl;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;
import com.example.springdataautomappingobjectsexercise.models.menus.enums.MenuDelimiter;
import com.example.springdataautomappingobjectsexercise.repositories.GameRepository;
import com.example.springdataautomappingobjectsexercise.services.GameService;
import com.example.springdataautomappingobjectsexercise.services.PublisherService;
import com.example.springdataautomappingobjectsexercise.services.ReaderService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ReaderService reader;
    private final PublisherService publisherService;
    private final Validator validator;
    private Map<Long, Game> games;

    public GameServiceImpl(GameRepository gameRepository, ReaderService reader, PublisherService publisherService, Validator validator) {
        this.gameRepository = gameRepository;
        this.reader = reader;
        this.publisherService = publisherService;
        this.validator = validator;
    }

    @Override
    public boolean existInDatabase(Game game) {
        return gameRepository.exists(Example.of(game));
    }

    @Override
    public boolean existInDatabase(String name, Publisher publisher) {
        return gameRepository.existsByTitleAndPublisher(name, publisher);
    }

    @Override
    public void save(Game game) {
        if (existInDatabase(game)) {
            throw new IllegalStateException("Game already exists in the database!");
        }
        gameRepository.save(game);
        if (games != null) {
            games.put(game.getId(), game);
        }
    }

    @Override
    public void addGame() {
        Game.Builder builder = Game.getBuilder();
        setTitle(builder);
        setPublisher(builder);
        setTrailerUrlId(builder);
        setThumbnailUrl(builder);
        setSize(builder);
        setPrice(builder);
        setDescription(builder);
        setReleaseDate(builder);

        Game game = builder.build();
        Set<ConstraintViolation<Game>> violations = validator.validate(game);
        if (violations.size() != 0) {
            violations.forEach(v -> System.out.println(v.getMessage()));
            return;
        }

        save(game);
        if (games != null) {
            games.put(game.getId(), game);
        }

    }

    private void setPublisher(Game.Builder builder) {

        String publisherName = publisherService.readName();

        Publisher publisher = publisherService.getByName(publisherName);
        if (publisher == null) {
            publisher = new Publisher(publisherName);
            publisherService.save(publisher);
        }
        builder.setPublisher(publisher);
    }

    private void setTitle(Game.Builder builder) {
        builder.setTitle(readTitle());
    }

    public String readTitle() {
        String title = "";
        while (title.isBlank()) {
            System.out.print("Enter game title: ");
            String input = reader.nextLine().trim();
            Set<ConstraintViolation<Game>> violations = validator.validateValue(Game.class, "title", input);
            if (violations.size() == 0) {
                title = input;
                break;
            } else {
                violations.forEach(v -> System.out.println(v.getMessage()));
            }
        }
        return title;
    }

    private void setTrailerUrlId(Game.Builder builder) {
        String input = readTrailerUrlId();
        if (input.isBlank()) {
            return;
        }
        builder.setTrailerUrlId(input);
    }

    @Override
    public String readTrailerUrlId() {
        System.out.print("Enter trailer URL id: ");
        return reader.nextLine().trim();
    }

    @Override
    public String readThumbnailUrl() {
        System.out.print("Enter thumbnail URL: ");
        return reader.nextLine().trim();
    }

    private void setThumbnailUrl(Game.Builder builder) {
        String input = readThumbnailUrl();
        if (input.isBlank()) {
            return;
        }
        builder.setThumbnailUrl(input);
    }

    private void setSize(Game.Builder builder) {
        builder.setSize(readGameSize());
    }

    @Override
    public Double readGameSize() {
        while (true) {
            Double size;
            System.out.print("Enter size: ");
            String input = reader.nextLine();
            if (input.isBlank()) {
                return null;
            }
            try {
                size = Double.parseDouble(input);
            } catch (IllegalFormatException e) {
                System.out.println("Illegal game size format: " + input);
                continue;
            }

            Set<ConstraintViolation<Game>> violations = validator.validateValue(Game.class, "size", size);
            if (violations.size() == 0) {
                return size;
            } else {
                violations.forEach(v -> System.out.println(v.getMessage()));
            }
        }
    }

    @Override
    public BigDecimal readPrice() {
        while (true) {
            System.out.print("Enter price: ");
            BigDecimal price = null;

            try {
                price = new BigDecimal(reader.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format for game price!");
            }

            Set<ConstraintViolation<Game>> violations = validator.validateValue(Game.class, "price", price);
            if (violations.size() == 0) {
                return price;
            }

            violations.forEach(v -> System.out.println(v.getMessage()));
        }
    }

    @Override
    public String readDescription() {
        System.out.print("Enter description: ");
        return reader.nextLine().trim();
    }

    private void setPrice(Game.Builder builder) {
        builder.setPrice(readPrice());
    }

    private void setDescription(Game.Builder builder) {
        String input = readDescription();
        if (input.isBlank()) {
            return;
        }
        builder.setDescription(input);
    }

    @Override
    public LocalDate readReleaseDate() {
        while (true) {
            System.out.print("Enter release date (format: dd-MM-yyyy) or leave blank: ");
            String input = reader.nextLine();
            if (input.isBlank()) {
                return null;
            }

            try {
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format!");
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        games.remove(id);
        return gameRepository.deleteGameById(id) > 0;
    }

    @Override
    public void printGameDetails(Game game) {
        System.out.println((" Title: " + game.getTitle() + System.lineSeparator()
                + MenuDelimiter.SHORT_LINE + "Publisher: " + game.getPublisher().getName() + System.lineSeparator()
                + MenuDelimiter.SHORT_LINE + "Release date: " + game.getReleaseDate() + System.lineSeparator()
                + MenuDelimiter.SHORT_LINE + "Description: " + game.getDescription() + System.lineSeparator()
                + MenuDelimiter.SHORT_LINE + "Size: " + game.getSize() + System.lineSeparator()
                + MenuDelimiter.SHORT_LINE + "Price: " + game.getPrice()));
    }




    private void setReleaseDate(Game.Builder builder) {
        LocalDate date = readReleaseDate();
        if (date == null) {
            return;
        }
        builder.setReleaseDate(date);
    }

    public void printAllGame() {
        if (games == null) {
            getAllGames();
        }

        String gamesInfo = games.values()
                .stream()
                .map(Game::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(gamesInfo);
    }

    private Optional<Game> getGame(Long id) {
        if (games != null) {
            if (games.containsKey(id)) {
                return Optional.ofNullable(games.get(id));
            }
        }

        return gameRepository.findById(id);
    }

    private void getAllGames() {
        this.games = gameRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(Game::getId, k -> k));
    }

    public Game selectGame() {

        while (true) {
            System.out.println(MenuDelimiter.LONG_LINE);
            System.out.print("Chose game id: ");
            Long id;
            try {
                id = Long.parseLong(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid id format!");
                continue;
            }

            Optional<Game> game = getGame(id);
            if (game.isPresent()) {
                return game.get();
            } else {
                System.out.println("No game with id: " + id);
            }
        }
    }
}
