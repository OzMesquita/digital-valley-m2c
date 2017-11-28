package util;

import model.Solicitacao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;

import dao.DAOFactory;
import dao.DAOFactoryM2C;
import dao.SolicitacaoDAO;
import model.Aluno;
import model.Email;
import model.EnumSolicitacao;
import model.Professor;

public class FacadeSolicitacoes {
	private FacadeSolicitacoes() {
		//
	}

	public static void gerarPDFDaSolicitacao(Solicitacao solicitacao) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(Constantes.getTEMP_PDF_SOLICITACAO()));
			document.open();
			//cabecalho
				//logo
			Image image = Image.getInstance(Constantes.getLOGO_UFC());
			image.setAlignment(Image.MIDDLE);
			image.scaleAbsoluteWidth(90);
			image.scaleAbsoluteHeight(60);			
			document.add(image);
				//campus
			Paragraph cabecalho = new Paragraph("CAMPUS DA UFC DE RUSSAS\nCOORDENAÇÃO DO CURSO DE ENGENHARIA DE SOFTWARE\n\n\n\n\n");		
			cabecalho.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(cabecalho);			
				//conteudo
			Aluno aluno = solicitacao.getAluno();
			Paragraph conteudo = null;
			if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
				conteudo = new Paragraph("Eu, "+aluno.getNome()+", aluno(a) matriculado(a) no " + 
						"Curso de "+aluno.getCurso()+", no de matrícula "+aluno.getMatricula()+", vem mui " + 
						"respeitosamente, perante Vossa Senhoria requerer recorreção da prova da disciplina " + 
						solicitacao.getDisciplina().getNome()+ ", que realizou-se no dia "+Facade.converterLocalDateParaString(solicitacao.getDataEHoraProva()) + ", no " + 
						"horário de ____:____, e tive conhecimento do resultado no dia ___/____/_____. Apresento este " + 
						"requerimento devido ao(s) seguinte(s) motivo(s):\n" + 
						"______________________________________________________________________________" + 
						"______________________________________________________________________________" + 
						"______________________________________________________________________________" + 
						"______________________________________________________________________________" + 
						"______________________________________________________________________________" + 
						"______________________________________________________________________________" + 
						"______________________________________________________________________________" + 
						"______________________________________________________________________________" + 
						"______________________________________________________________________________" + 
						"______________________________________________________________________________" + 
						"_____________________________________________________________________________.");											
			} else if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.SEGUNDA_CHAMADA)) {
				conteudo = new Paragraph("Prof.(a): ________________________________________________________________________\r\n" + 
							"Coordenador do Curso/Prof.(a): _____________________________________\r\n" + 
							"Nome do aluno: _____________________________________  Matrícula N°: ___________\r\n" +
							"Curso: ________________________________  Data da prova: ___/____/_____\r\n" + 
							"Disciplina: _____________________________________Data deste requerimento: ___/____/_____\r\n" +
							"E-mail do aluno: _____________________________________\r\n" + 
							"Motivo da falta: ___________________________________________________\r\n" +
							"Venho por meio do presente, solicitar a realização da prova de segunda chamada da disciplina acima " + 
							"indicada, levando em conta a previsão do § 3o do Art. 110 do Regimento Geral da UFC, abaixo" + 
							"transcrito:\n" + 
							"Art. 110...\r\n" + 
							"§ 3o. - Será assegurada ao aluno a segunda chamada das provas, desde que solicitada, por escrito, até" + 
							"03 (três) dias úteis decorridos após a realização da prova em primeira chamada.\n\n\n\n" +
							"Atenciosamente,");
			}
			document.add(conteudo);
			Paragraph assAluno = new Paragraph("\n\n\n\n\n\n_______________________________________________\n(Assinatura do Aluno)");
			assAluno.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(assAluno);
			document.close();
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} 
	}

	public static void enviarEmailSolicitacao(Solicitacao solicitacao) {
		if (solicitacao != null) {
			String msg = "";
			if (solicitacao.getTipoSolicitacao() != null) {
				if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.SEGUNDA_CHAMADA)) {
					msg = "O Aluno " + solicitacao.getAluno().getNome() + " solicitou a Segunda Chamada da prova de "
							+ solicitacao.getDisciplina().getNome() + " realizada na data de: "
							+ solicitacao.getDataEHoraProva() + " com a justificativa de : \""
							+ solicitacao.getJustificativa() + "\"";
				} else if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
					msg = "O Aluno " + solicitacao.getAluno().getNome() + " solicitou a Recorreção da prova de "
							+ solicitacao.getDisciplina().getNome() + " realizada na data de: "
							+ solicitacao.getDataEHoraProva() + " com a justificativa de : \""
							+ solicitacao.getJustificativa() + "\"";
				}
			} else {
				throw new IllegalArgumentException("Tipo de solicitação não informado corretamente, valor informado: "
						+ solicitacao.getTipoSolicitacao());

			}
			if (!msg.equals("")) {
				Email e = new Email();
				e.sendEmail("Solicitação de Segunda chamada", msg, solicitacao.getProfessor().getEmail(),
						"Usuário Controle de Acesso");
			} else {
				throw new IllegalArgumentException("Erro ao enviar o email");
			}
		} else {
			throw new NullPointerException("Solicitação nula.");
		}

	}

	public static boolean verificarDias(LocalDate dataProva) {
		LocalDate hoje = LocalDate.now();
		if (hoje.getDayOfWeek().name().equals("TUESDAY")) {
			hoje = hoje.minusDays(2);
		} else if (hoje.getDayOfWeek().name().equals("MONDAY")) {
			hoje = hoje.minusDays(2);
		} else if (hoje.getDayOfWeek().name().equals("SUNDAY")) {
			hoje = hoje.minusDays(1);
		}

		if (dataProva.plusDays(4).isAfter(hoje)) {
			return true;
		} else {
			return false;
		}

	}

	public static void salvarSolicitacao(Solicitacao solicitacao) {
		SolicitacaoDAO sDAO = DAOFactoryM2C.criarSolicitacaoDAO();
		sDAO.cadastrar(solicitacao);
	}

	public static Solicitacao buscarPorId(int id) {
		Solicitacao solicitacao = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorId(id);
		solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(solicitacao.getAluno().getId()));
		solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(solicitacao.getProfessor().getId()));
		solicitacao.getDisciplina().setProfessor(solicitacao.getProfessor());
		solicitacao.getDisciplina().setNome("criarDAO");
		return solicitacao;
	}

	public static ArrayList<Solicitacao> buscarPorAluno(Aluno aluno, int inicio, int fim) {
		ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) DAOFactoryM2C.criarSolicitacaoDAO()
				.buscarPorAluno(aluno, inicio, fim);
		for (Solicitacao solicitacao : solicitacoes) {
			solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(solicitacao.getProfessor().getId()));
			solicitacao.getDisciplina().setProfessor(solicitacao.getProfessor());
			solicitacao.getDisciplina().setNome("criarDAO");
		}
		return solicitacoes;
	}

	public static ArrayList<Solicitacao> buscarPorProfessor(Professor professor, int inicio, int fim) {
		ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) DAOFactoryM2C.criarSolicitacaoDAO()
				.buscarPorProfessor(professor, inicio, fim);
		for (Solicitacao solicitacao : solicitacoes) {
			solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(solicitacao.getAluno().getId()));
			solicitacao.getDisciplina().setProfessor(professor);
			solicitacao.getDisciplina().setNome("criarDAO");
		}
		return solicitacoes;
	}

	public static ArrayList<Solicitacao> listar(int inicio, int fim) {
		ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) DAOFactoryM2C.criarSolicitacaoDAO()
				.listar(inicio, fim);
		for (Solicitacao solicitacao : solicitacoes) {
			solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(solicitacao.getAluno().getId()));
			solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(solicitacao.getProfessor().getId()));
			solicitacao.getDisciplina().setProfessor(solicitacao.getProfessor());
			solicitacao.getDisciplina().setNome("criarDAO");
		}
		return solicitacoes;
	}

}
