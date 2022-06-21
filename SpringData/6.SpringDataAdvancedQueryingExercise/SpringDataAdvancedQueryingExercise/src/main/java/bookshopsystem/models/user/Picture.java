package bookshopsystem.models.user;

import javax.persistence.*;

@Entity(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    private String caption;
    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    protected Picture() {
    }

    public Picture(String title, String path, User owner) {
        setTitle(title);
        setPath(path);
        setOwner(owner);
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Picture title should not be Null or Blank!");
        }
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    private void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPath() {
        return path;
    }

    private void setPath(String path) {

        if(path == null || path.isBlank()) {
            throw new IllegalArgumentException("Picture path should not be Null or Blank!");
        }
        this.path = path;
    }


    public User getOwner() {
        return owner;
    }

    private void setOwner(User owner) {
        if(owner == null) {
            throw new IllegalArgumentException("Owner should not be Null!");
        }
        this.owner = owner;
    }

}
