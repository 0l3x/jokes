package olex.dto;

import olex.models.entity.Joke;

public class JokeDTO {
    private Integer id;
    private String text1;
    private String text2;
    private String category;
    private String type;
    private String language;

    public JokeDTO(Joke joke) {
        this.id = joke.getId();
        this.text1 = joke.getText1();
        this.text2 = joke.getText2();
        this.category = joke.getCategory() != null ? joke.getCategory().getCategory() : null;
        this.type = joke.getType() != null ? joke.getType().getType() : null;
        this.language = joke.getLanguage() != null ? joke.getLanguage().getLanguage() : null;
    }

    // Getters y setters
    public Integer getId() { return id; }
    public String getText1() { return text1; }
    public String getText2() { return text2; }
    public String getCategory() { return category; }
    public String getType() { return type; }
    public String getLanguage() { return language; }

    public void setId(Integer id) { this.id = id; }
    public void setText1(String text1) { this.text1 = text1; }
    public void setText2(String text2) { this.text2 = text2; }
    public void setCategory(String category) { this.category = category; }
    public void setType(String type) { this.type = type; }
    public void setLanguage(String language) { this.language = language; }
}
