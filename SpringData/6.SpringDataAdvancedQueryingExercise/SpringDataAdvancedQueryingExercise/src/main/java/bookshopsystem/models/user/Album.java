package bookshopsystem.models.user;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "background_color")
    private String backgroundColor;
    @Column(name = "is_public")
    private boolean isPublic;
    @ManyToMany
    private Set<Picture> pictures;
    @ManyToOne
    private User owner;

    public Album() {
    }

    public Album(String name, User owner) {
        setName(name);
        setBackgroundColor("white");
        setPublic(false);
        setOwner(owner);
        setPictures();
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Album name should not be Null or Blank!");
        }
        this.name = name;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    private void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isPublic() {
        return isPublic;
    }

    private void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public User getOwner() {
        return owner;
    }

    private void setOwner(User owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Album Owner should not be Null!");
        }
        this.owner = owner;
    }

    public Set<Picture> getPictures() {
        return Collections.unmodifiableSet(pictures);
    }

    private void setPictures() {
        this.pictures = new HashSet<>();
    }

    public void addPicture(Picture picture) {
        if (owner == null) {
            throw new IllegalArgumentException("Can't add Null picture to album!");
        }
        this.pictures.add(picture);
    };
}
