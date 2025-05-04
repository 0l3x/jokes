package olex.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jokes_flags")
@IdClass(JokeFlagId.class)
public class JokeFlag {

    @Id
    @ManyToOne
    @JoinColumn(name = "joke_id")
    private Joke joke;

    @Id
    @ManyToOne
    @JoinColumn(name = "flag_id")
    private Flag flag;

	public Joke getJoke() {
		return joke;
	}

	public void setJoke(Joke joke) {
		this.joke = joke;
	}

	public Flag getFlag() {
		return flag;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
	}

}

