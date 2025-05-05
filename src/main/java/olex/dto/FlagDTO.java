package olex.dto;

public class FlagDTO {
    private Integer id;
    private String flag;

    public FlagDTO() {}

    public FlagDTO(Integer id, String name) {
        this.id = id;
        this.flag = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return flag; }
    public void setName(String name) { this.flag = name; }
}
