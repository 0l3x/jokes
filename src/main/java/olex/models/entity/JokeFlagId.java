package olex.models.entity;

import java.io.Serializable;
import java.util.Objects;

public class JokeFlagId implements Serializable {

    private Integer joke;
    private Integer flag;

    public JokeFlagId() {}

    public JokeFlagId(Integer joke, Integer flag) {
        this.joke = joke;
        this.flag = flag;
    }

    public Integer getJoke() {
        return joke;
    }

    public void setJoke(Integer joke) {
        this.joke = joke;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JokeFlagId)) return false;
        JokeFlagId that = (JokeFlagId) o;
        return Objects.equals(joke, that.joke) && Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joke, flag);
    }
}
