package com.example.springdataautomappingobjectsexercise.services;

import com.example.springdataautomappingobjectsexercise.models.entities.Game;
import com.example.springdataautomappingobjectsexercise.models.entities.Publisher;
import com.example.springdataautomappingobjectsexercise.repositories.GameRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.IllegalFormatException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ReaderService reader;
    private final PublisherService publisherService;
    private Map<Long,Game> games;

    public GameServiceImpl(GameRepository gameRepository, ReaderService reader, PublisherService publisherService) {
        this.gameRepository = gameRepository;
        this.reader = reader;
        this.publisherService = publisherService;
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
        try {
            save(game);
            if (games != null) {
                games.put(game.getId(),game);
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
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

    public String readTitle(){
        String title = "";
        while (title.isBlank()) {
                System.out.print("Enter game title: ");
                title = reader.nextLine().trim();
                if(title.isBlank()) {
                    System.out.println("Title should not be blank");
                }
        }
        return title;
    }

    private void setTrailerUrlId(Game.Builder builder) {
        System.out.print("Enter trailer URL id: ");
        String input = reader.nextLine();
        if (input.isBlank()) {
            return;
        }
        builder.setTrailerUrlId(input);
    }

    private void setThumbnailUrl(Game.Builder builder) {
        System.out.print("Enter thumbnail URL: ");
        String input = reader.nextLine();
        if (input.isBlank()) {
            return;
        }
        builder.setThumbnailUrl(input);
    }

    private void setSize(Game.Builder builder) {
        System.out.print("Enter size: ");
        while (builder.getSize() == null) {
            String input = reader.nextLine();
            if (input.isBlank()) {
                return;
            }
            try {

                builder.setSize(Double.parseDouble(input));
            } catch (IllegalFormatException e) {
                System.out.println("Illegal game size format: " + input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setPrice(Game.Builder builder) {
        while (builder.getPrice() == null) {
            System.out.print("Enter price: ");
            try {
                BigDecimal price = new BigDecimal(reader.nextLine().trim());
                builder.setPrice(price);
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format for game price!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setDescription(Game.Builder builder) {
        System.out.print("Enter description: ");
        String input = reader.nextLine();
        if (input.isBlank()) {
            return;
        }
        builder.setDescription(input);
    }

    private void setReleaseDate(Game.Builder builder) {
        System.out.print("Enter release date (format: dd-MM-yyyy): ");
        String input = reader.nextLine();
        if (input.isBlank()) {
            return;
        }
        LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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

    @Override
    public Game getGame(Long id) {
        if(games != null) {
            if (games.containsKey(id)) {
                return games.get(id);
            }
        }

        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }

    private void getAllGames() {
        Map<Long, Game> games = gameRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(Game::getId, k -> k));
        this.games = games;
    }
}
