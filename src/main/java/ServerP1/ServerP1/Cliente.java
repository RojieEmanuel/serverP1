package ServerP1.ServerP1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import database.AlunoDAO;
import database.ProfessorDAO;
import model.Aluno;
import model.Professor;

public class Cliente implements Runnable {

	private Socket socket;

	public Cliente(Socket socket) {
		super();
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public String leitura() {
		String menssagem = "Sem menssagem";

		try {
			Scanner scanner = new Scanner(getSocket().getInputStream());
			if (scanner.hasNextLine()) {
				menssagem = scanner.nextLine().toString();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menssagem;

	}

	public void enviaResposta(String resposta) {

		
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		OutputStream output;
		System.out.println(resposta);
		try {
			output = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(output, true);
			printWriter.println(resposta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		String[] menssagem;

		menssagem = leitura().split("@");
		System.out.println(menssagem[0] + " " + Thread.currentThread().getId());
		
		String resposta;
		String[] menssagemFormatada;
		switch (menssagem[0]) {
		
		case "novatarefa":
			System.out.println(menssagem[1]);
			String[] tarefa = menssagem[1].split("-");
			//criar aqui a inserceo da tarefa no banco a tarefa[0] eh o titulo da tarefa,  tarefa[1] eha  turma e tarefa[2] eh a descricao
			enviaResposta("ok@POO/PPI/");//a resposta tem que ser um ok seguido da lista de cursos que tiver no banco, mande soh o nome do curso separado por /, colo aqui
			break;
			
		case "novamateria":
			System.out.println(menssagem[1]);
			String[] materia = menssagem[1].split("-");
			//criar aqui a inserceo da materia no banco a materia[0] eh o nome da turma e a materia[1] eh o nome do professor responsavel
			enviaResposta("ok@POO/PPI/");//a resposta tem que ser um ok seguido da lista de cursos que tiver no banco, mande soh o nome do curso separado por /, colo aqui
			break;
			
		case "novaturma":
			System.out.println(menssagem[1]);
			String[] turma = menssagem[1].split("-");
			//criar aqui a inserceo da turma no banco a turma[0] eh o nome da turma e a turma[1] eh o nome do professor responsavel
			
			enviaResposta("ok@POO/PPI/");//a resposta tem que ser um ok seguido da lista de cursos que tiver no banco, mande soh o nome do curso separado por /, colo aqui
			break;

		case "novo-aluno":
			System.out.println(menssagem[1]);
			String[] alunoFormatado = menssagem[1].split("-");

			Aluno aluno = new Aluno(alunoFormatado[0], alunoFormatado[1], alunoFormatado[2], alunoFormatado[3],
					alunoFormatado[4]);
			new AlunoDAO().add(aluno);
			enviaResposta("ok");
			break;
		case "updateProfessor":
			System.out.println(menssagem[1]);
			String[] professorAtualizado = menssagem[1].split("-");

			Professor professorAtua = new Professor(professorAtualizado[0], professorAtualizado[1], professorAtualizado[2], professorAtualizado[3],
					professorAtualizado[4]);
			new ProfessorDAO().update(professorAtua);
			enviaResposta("ok");
			break;
			
	
		case "updateAluno":
			System.out.println(menssagem[1]);
			String[] alunoAtualizado = menssagem[1].split("-");

			Aluno alunoAtua = new Aluno(alunoAtualizado[0], alunoAtualizado[1], alunoAtualizado[2], alunoAtualizado[3],
					alunoAtualizado[4]);
			new AlunoDAO().update(alunoAtua);
			enviaResposta("ok");
			break;
			
		case "apagarAluno":
			System.out.println(menssagem[1]);
			String alunoApagado = menssagem[1];
			Aluno alunoBusca = new AlunoDAO().get(alunoApagado);
			new AlunoDAO().delete(alunoBusca);
			enviaResposta("ok");
			break;
			
		case "apagarProfessor":
			System.out.println(menssagem[1]);
			String professorApagado = menssagem[1];
			Professor professorBusca = new ProfessorDAO().get(professorApagado);
			new ProfessorDAO().delete(professorBusca);
			enviaResposta("ok");
			break;
			
		case "novo-professor":
			System.out.println(menssagem[1]);
			String[] professorFormatado = menssagem[1].split("-");

			Professor professor = new Professor(professorFormatado[0], professorFormatado[1], professorFormatado[2],
					professorFormatado[3], professorFormatado[4]);
			new ProfessorDAO().add(professor);
			enviaResposta("ok");
			break;
		case "Cursos":

			resposta = "0001-Luciano-ADS/0002-Lucas-ADS/";
			enviaResposta(resposta);

			break;
		case "Login-Professor":
			System.out.println(menssagem[1]);
			menssagemFormatada = menssagem[1].split("-");

			if (menssagemFormatada[0].equals("rojie") && menssagemFormatada[1].equals("12345678")) {
				resposta = "ok@POO/PPI/";

			} else {
				resposta = "errado";
			}
			enviaResposta(resposta);
			break;

		case "Login-Aluno":
			System.out.println(menssagem[1]);
			menssagemFormatada = menssagem[1].split("-");

			if (menssagemFormatada[0].equals("rojie") && menssagemFormatada[1].equals("12345678")) {
				resposta = "ok";

			} else {
				resposta = "errado";
			}
			enviaResposta(resposta);
			break;

		case "minhasTarefas":

			resposta = "TarefaReal1-123/@TarefaEspera1-123/";
			enviaResposta(resposta);

			break;

		case "minhasTurmas":

			resposta = "POO-Lucas/PPI-Alexandre/";
			enviaResposta(resposta);

			break;

		case "minhaLista":

			resposta = "Lucas-123-45678-88-9999/@rojie-3126-888-99-999/";
			enviaResposta(resposta);

			break;

		case "minhaChamada":

			resposta = "Lucas-123-45678-88-9999/rojie-3126-888-99-999/";
			enviaResposta(resposta);

			break;

		default:
			break;
		}

	}

}
