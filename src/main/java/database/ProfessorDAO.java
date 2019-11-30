package database;

import java.util.List;

import javax.persistence.EntityManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Aluno;
import model.Professor;

public class ProfessorDAO  implements InterfaceDAO<Professor>{
	private static ObservableList<Professor> professores;

	public Professor getProfessor(String login) {
		professores = (ObservableList<Professor>) getAll();
		if (professores != null)
			for (Professor professor : professores)
				if (professor.getLogin().equals(login))
					return professor;

		EntityManager entityMng = Conection.getEntityManager();
		Professor professor = entityMng.find(Professor.class, login);
		entityMng.close();
		return professor;
	}
	
	@Override
	public Professor get(String id) {
		if (professores != null)
			for (Professor professor : professores)
				if (professor.getCpf().contentEquals(id))
					return professor;

		EntityManager entityMng = Conection.getEntityManager();
		Professor professor = entityMng.find(Professor.class, id);
		entityMng.close();
		return professor;
	}

	@Override
	public List<Professor> getAll() {
		
		professores = null;
		if (professores == null) {
			EntityManager entityMng = Conection.getEntityManager();
			professores	= FXCollections.observableArrayList(
					entityMng.createQuery("select professor from Professor as professor", Professor.class).getResultList());
			entityMng.close();
		}
		return professores;
	}

	@Override
	public void add(Professor professor) {
		EntityManager entityMng = Conection.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(professor);
		entityMng.getTransaction().commit(); // sempre que iniciamos uma transação, precisamos dar o commit
		entityMng.close();

		// adiciono na lista de usuários que está na memória, se todos os usuários já
		// foram carregador do banco
		if (professores != null)
			professores.add(professor);
	}

	@Override
	public void delete(Professor obj) {
		EntityManager entityMng = Conection.getEntityManager();
		entityMng.getTransaction().begin();
		Professor userDB = entityMng.find(Professor.class, obj.getCpf());
		entityMng.remove(userDB);
		entityMng.getTransaction().commit();
		entityMng.close();

		Professor found = null;
		if (professores != null)
			for (Professor professor : professores)
				if (professor.getCpf().contentEquals(obj.getCpf()))
					found = professor;
		if(found != null)
			professores.remove(found);
	}

	@Override
	public void update(Professor obj) {
		EntityManager entityMng = Conection.getEntityManager();
		entityMng.getTransaction().begin();
		Professor userDB = entityMng.find(Professor.class, obj.getCpf());
		userDB.setCpf(obj.getCpf());
		userDB.setNome(obj.getNome());
		userDB.setCurso(obj.getCurso());
		userDB.setLogin(obj.getLogin());
		userDB.setSenha(obj.getSenha());
		entityMng.getTransaction().commit();
		entityMng.close();

		if (professores != null) {
			for (Professor professor : professores) {
				if (professor.getCpf().contentEquals(obj.getCpf())) {
					professor.setCpf(obj.getCpf());
					professor.getNome().contentEquals(obj.getNome());
					userDB.setNome(obj.getNome());
					userDB.setCurso(obj.getCurso());
					userDB.setLogin(obj.getLogin());
					userDB.setSenha(obj.getSenha());
					
				}
			}
		}

	}

}
