package dao;

import javax.persistence.EntityManager;

import br.com.n2s.model.Aluno;
import br.com.n2s.model.EnumSolicitacao;
import br.com.n2s.model.Professor;
import br.com.n2s.model.Solicitacao;

public class JPAHSolicitacaoDAO extends JPADAO implements SolicitacaoDAO {

	@Override
	public void cadastrar(Solicitacao solicitacao) {
		if (solicitacao != null) {
			super.open();
			EntityManager entityManager = super.getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(solicitacao);
			entityManager.getTransaction().commit();
			entityManager.flush();
			super.close();
		} else {
			throw new NullPointerException("Erro: A solicitação não pode ser nula.");
		}

	}

	@Override
	public void buscarPorId(int id) {
		EntityManager entityManager = super.getEntityManager();
		entityManager.find(Solicitacao.class, id);

	}

	@Override
	public void buscarPorAluno(Aluno aluno) {
		EntityManager entityManager = super.getEntityManager();
		entityManager.find(Solicitacao.class, aluno);		
	}

	@Override
	public void buscarPorProfessor(Professor professor) {
		EntityManager entityManager = super.getEntityManager();
		entityManager.find(Solicitacao.class, professor);

	}

	@Override
	public void buscarPorTipo(EnumSolicitacao tipo) {
		// TODO Auto-generated method stub

	}

}
