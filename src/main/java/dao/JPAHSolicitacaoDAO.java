package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Aluno;
import model.EnumSolicitacao;
import model.Professor;
import model.Solicitacao;

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
	public Solicitacao buscarPorId(int id) {
		super.open();
		Solicitacao solicitacao = super.getEntityManager().find(Solicitacao.class, id);
		super.close();
		return solicitacao;
	}

	@Override
	public List<Solicitacao> buscarPorAluno(Aluno aluno) {
		super.open();
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Solicitacao> criteriaQuery = builder.createQuery(Solicitacao.class);
		Root<Solicitacao> solicitacao = criteriaQuery.from(Solicitacao.class);
		List<Solicitacao> solicitacoes = entityManager
				.createQuery(criteriaQuery.select(solicitacao).where(builder.equal(solicitacao.get("aluno"), aluno)))
				.getResultList();
		super.close();
		return solicitacoes;
	}

	@Override
	public List<Solicitacao> buscarPorProfessor(Professor professor) {
		super.open();
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Solicitacao> criteriaQuery = builder.createQuery(Solicitacao.class);
		Root<Solicitacao> solicitacao = criteriaQuery.from(Solicitacao.class);
		List<Solicitacao> solicitacoes = entityManager
				.createQuery(criteriaQuery.select(solicitacao).where(builder.equal(solicitacao.get("professor"), professor)))
				.getResultList();
		super.close();
		return solicitacoes;
	}

	@Override
	public List<Solicitacao> buscarPorTipo(EnumSolicitacao tipo) {
		EntityManager entityManager = super.getEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Solicitacao> criteriaQuery = builder.createQuery(Solicitacao.class);
		Root<Solicitacao> solicitacao = criteriaQuery.from(Solicitacao.class);
		List<Solicitacao> solicitacoes = entityManager
				.createQuery(criteriaQuery.select(solicitacao).where(builder.equal(solicitacao.get("tipoSolicitacao"), tipo)))
				.getResultList();
		super.close();
		return solicitacoes;
	}

}
