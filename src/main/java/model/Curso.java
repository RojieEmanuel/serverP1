package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Curso {
	@Id
	private String code;
	private String name;
	private String cordenador;
	
	public Curso() {
		
	}

	public Curso(String code, String name, String cordenador) {
		super();
		this.code = code;
		this.name = name;
		this.cordenador = cordenador;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCordenador() {
		return cordenador;
	}

	public void setCordenador(String cordenador) {
		this.cordenador = cordenador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((cordenador == null) ? 0 : cordenador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (cordenador == null) {
			if (other.cordenador != null)
				return false;
		} else if (!cordenador.equals(other.cordenador))
			return false;
		return true;
	}
	

	
}
