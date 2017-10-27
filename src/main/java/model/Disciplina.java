package model;

public class Disciplina{	
	
	private int id;
	private Professor professor;
	private Curso curso;
	

	public Disciplina(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		if(professor==null)
			throw new IllegalArgumentException("Professor inválido");
		this.professor = professor;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		if(curso==null)
			throw new IllegalArgumentException("Curso inválido");
		this.curso = curso;
	}
}
