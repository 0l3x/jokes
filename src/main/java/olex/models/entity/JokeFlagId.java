package olex.models.entity;

import java.io.Serializable;

public class JokeFlagId implements Serializable {
    private Integer joke;
    private Integer flag;
    
    
		public JokeFlagId(Integer jokeId, Integer flagId) {
			// TODO Auto-generated constructor stub
		}
	
		public JokeFlagId() {
			// TODO Auto-generated constructor stub
		}
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override	
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
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
    
}

