package business;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PetStore {
    private int id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    // Classe interna para Category
    @Getter
    @Setter
    public static class Category {
        private int id;
        private String name;
    }

    // Classe interna para Tag
    @Getter
    @Setter
    public static class Tag {
        private int id;
        private String name;
    }
}
