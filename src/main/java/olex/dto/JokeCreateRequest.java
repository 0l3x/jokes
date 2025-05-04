package olex.dto;

public class JokeCreateRequest {

    private String text1;
    private String text2;
    private Integer category;
    private Integer type;
    private Integer language;

    public String getText1() { return text1; }
    public void setText1(String text1) { this.text1 = text1; }

    public String getText2() { return text2; }
    public void setText2(String text2) { this.text2 = text2; }

    public Integer getCategory() { return category; }
    public void setCategory(Integer category) { this.category = category; }

    public Integer getType() { return type; }
    public void setType(Integer type) { this.type = type; }

    public Integer getLanguage() { return language; }
    public void setLanguage(Integer language) { this.language = language; }
}
