package regras_negocio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;

import modelo.Grupo;
import modelo.Individual;
import modelo.Mensagem;
import modelo.Participante;
import repositorio.Repositorio;




public class Fachada {
	
	
	private Fachada() {
		
		//repositorio.carregarObjetos();
		
	}
	
	private static Repositorio repositorio = new Repositorio();
	
	
	
	
	
	

	
	
	
	
	
//------------------------------------------------------------------------------------	
	//LEMBRAR DE APAGAR ESTE METODO ANTES DE CONCLUIR O PROJETO
	//METODO CRIADO APENAS PARA ACESSAR A INSTANCIA UNICA DE FACHADA QUE ESTÁ COMO "PRIVATE"
	//SE A INSTANCIA NÃO EXISTIR, ELE CRIARÁ UMA ASSIM QUE O MÉTODO FOR CHAMADO
	public static Fachada getInstance() {   
        return new Fachada();
    }
//------------------------------------------------------------------------------------		
	


	public static ArrayList<Individual> listarIndividuos() {
		
	    return repositorio.getIndividuos();
		
	}
	
	
	
	public static ArrayList<Grupo> listarGrupos() {
		
		return repositorio.getGrupos();	
		
	}
	
	
	
	public static ArrayList<Mensagem> listarMensagens() {
		
		return repositorio.getMensagens();
		
	}
	

	
	public static ArrayList<Mensagem> listarMensagensEnviadas(String nome) throws Exception{
		
		Individual ind = repositorio.localizarIndividual(nome);	
		if(ind == null) 
			throw new Exception("listar  mensagens enviadas - nome nao existe:" + nome);

		return ind.getEnviadas();
		
	}

	
	
	
	public static ArrayList<Mensagem> listarMensagensRecebidas(String nome) throws Exception{
		
		Individual ind = repositorio.localizarIndividual(nome);	
		if(ind == null) 
			throw new Exception("listar  mensagens recebidas - nome nao existe:" + nome);

		return ind.getRecebidas();
		
	}

	
	
	
	public static void criarIndividuo(String nome, String senha) throws Exception{
		
		if(nome.isEmpty()) 
			throw new Exception("criar individual - nome vazio:");
		if(senha.isEmpty()) 
			throw new Exception("criar individual - senha vazia:");
		
		Participante p = repositorio.localizarParticipante(nome);	
		if(p != null) 
			throw new Exception("criar individual - nome ja existe: " + nome);


		Individual individuo = new Individual(nome, senha, false);
		repositorio.adicionar(individuo);	
		
		//repositorio.salvarObjetos();
		
	}
	
	
	
	
	public static Individual validarIndividuo(String nomeindividuo, String senha) throws Exception{
		
		Individual ind = repositorio.localizarIndividual(nomeindividuo);	
		if(ind == null) {
			throw new Exception("validar individuo - individuo não existe:" + nomeindividuo);
		}
		
		if (!ind.getSenha().equals(senha)) {
	        throw new Exception("validar individuo - senha incorreta");
	    }
		
		return ind;
			
	}
	
	
	

	public static void criarAdministrador(String nome, String senha) throws  Exception{

		if(nome.isEmpty()) 
			throw new Exception("criar individual - nome vazio:");
		if(senha.isEmpty()) 
			throw new Exception("criar individual - senha vazia:");
		
		Participante q = repositorio.localizarParticipante(nome);	
		if(q != null) 
			throw new Exception("criar individual - nome ja existe: " + nome);


		Individual individuo = new Individual(nome, senha, true);
		repositorio.adicionar(individuo);	
		
		//repositorio.salvarObjetos();
		
		
		//ABRIR ESPAÇO PARA COMPARACAO DE SENHA COM OBJETO INDIVIDUAL QUE JÁ EXISTE EM REPOSITORIO
		
		//if(senha != q.getSenha()) 
		//	throw new Exception("senha nao corresponde ao nome login informado:" + nome);
		
				
	}


	
	public static void criarGrupo(String nome) throws  Exception{
		//localizar nome no repositorio
		//criar o grupo	
		
		if(nome.isEmpty()) 
			throw new Exception("criar grupo - nome vazio:");
		
		Participante r = repositorio.localizarParticipante(nome);	
		
		if(r != null) 
			throw new Exception("criar individual - nome ja existe: " + nome);
		
		Grupo grupo = new Grupo(nome);
		repositorio.adicionar(grupo);	
		
		//repositorio.salvarObjetos();
		
	}

	
	
	public static void inserirGrupo(String nomeindividuo, String nomegrupo) throws  Exception{
		//localizar nomeindividuo no repositorio
		//localizar nomegrupo no repositorio
		//verificar se individuo nao esta no grupo	
		//adicionar individuo com o grupo e vice-versa
		
		Individual ind = repositorio.localizarIndividual(nomeindividuo);
		if(ind == null) 
			throw new Exception("inserir grupo - nome do individuo nao existe:" + nomeindividuo);
		
		Grupo g1 = repositorio.localizarGrupo(nomegrupo);
		if(g1 == null) 
			throw new Exception("inserir grupo - nome do grupo nao existe:" + nomeindividuo);
		
		if(g1.localizarIndividuo(ind) == true) 
			throw new Exception("inserir grupo - individuo já existe neste grupo:" + nomegrupo);
		
		else
			g1.adicionar(ind);
			ind.inserirGrupo(g1);	
			
			//repositorio.salvarObjetos();
		
	}

	
	
	public static void removerGrupo(String nomeindividuo, String nomegrupo) throws  Exception{
		//localizar nomeindividuo no repositorio
		//localizar nomegrupo no repositorio
		//verificar se individuo ja esta no grupo	
		//remover individuo com o grupo e vice-versa
		
		Individual ind = repositorio.localizarIndividual(nomeindividuo);
		if(ind == null) 
			throw new Exception("inserir grupo - nome do individuo nao existe:" + nomeindividuo);
		
		Grupo g1 = repositorio.localizarGrupo(nomegrupo);
		if(g1 == null) 
			throw new Exception("inserir grupo - nome do grupo nao existe:" + nomeindividuo);
		
		if(g1.localizarIndividuo(ind) == true) 
			throw new Exception("inserir grupo - individuo já existe neste grupo:" + nomegrupo);
		
		else
			g1.removerIndividuo(ind);
			ind.removerGrupo(g1);
			
			//repositorio.salvarObjetos();
		
	}

	

	public static void criarMensagem(String nomeemitente, String nomedestinatario, String texto) throws Exception{
		
		if(texto.isEmpty()) 
			throw new Exception("criar mensagem - texto vazio:");

		
		Individual emitente = repositorio.localizarIndividual(nomeemitente);	
		if(emitente == null) 
			throw new Exception("criar mensagem - emitente nao existe:" + nomeemitente);

		
		Participante destinatario = repositorio.localizarParticipante(nomedestinatario);	
		if(destinatario == null) 
			throw new Exception("criar mensagem - destinatario nao existe:" + nomeemitente);

		
		if(destinatario instanceof Grupo g && emitente.localizarGrupo(g.getNome()) == null)
			throw new Exception("criar mensagem - grupo nao permitido:" + nomedestinatario);
	
		//Gerando o ID da Mensagem
		
		int mensagemId = 0;
		
		ArrayList<Mensagem> mensagens = listarMensagens();
		
		if (!mensagens.isEmpty()) {
		    Mensagem ultimaMensagem = mensagens.get(mensagens.size() - 1);
		    mensagemId = ultimaMensagem.getId() + 1;
		} else {
		    mensagemId = 1;
		}
		
		
		
		// Criando a mensagem original
	    Mensagem mensagem = new Mensagem(mensagemId, emitente, destinatario, texto);
	    emitente.adicionarEnviada(mensagem);
	    destinatario.adicionarRecebida(mensagem);
	    repositorio.adicionar(mensagem);
	    
	  //repositorio.salvarObjetos();

	    // Se o destinatário for um grupo, envia cópias da mensagem para os membros do grupo
	    if (destinatario instanceof Grupo) {
	        Grupo grupoDestinatario = (Grupo) destinatario;

	        for (Individual individuo : grupoDestinatario.getIndividuos()) {
	        	
	        	// Criar cópia da mensagem (com mesmo ID e conteúdo)
	        	if (individuo != emitente) {
	        		
	        		String textoCopia = emitente.getNome() + "/ " + mensagem.getTexto();
		            Mensagem mensagemCopia = new Mensagem(mensagem.getId(), grupoDestinatario, individuo, textoCopia);
		            //mensagemCopia.setIDCopia(mensagem.getId());
		            grupoDestinatario.adicionarEnviada(mensagemCopia);
		            individuo.adicionarRecebida(mensagemCopia);
		            repositorio.adicionar(mensagemCopia); 
		            
		            //repositorio.salvarObjetos();
	        	}        	
	        }
	    }
	
	    //repositorio.salvarObjetos();
	    
		//cont.
		//gerar id no repositorio para a mensagem
		//criar mensagem
		//adicionar mensagem ao emitente e destinatario
		//adicionar mensagem ao repositorio
		//
		//caso destinatario seja tipo Grupo então criar copias da mensagem, tendo o grupo como emitente e cada membro do grupo como destinatario, 
		//  usando mesmo id e texto, e adicionar essas copias no repositorio
		
	}
	
	
	public static ArrayList<Mensagem> obterConversa(String nomeindividuo, String nomedestinatario) throws Exception{
		//localizar emitente no repositorio
		//localizar destinatario no repositorio
		//obter do emitente a lista  enviadas
		//obter do emitente a lista  recebidas
		
		//criar uma especie de chat, percorrendo a lista de enviadas e recebidas
		//cada ocorrencia da lista que bater com emitente e destinatario, ser adicionada
		//em uma lista de conversa , dos dois lados, para montar uma especie de chatBot
		
		//criar a lista conversa
		//Adicionar na conversa as mensagens da lista enviadas cujo destinatario é igual ao parametro destinatario
		//Adicionar na conversa as mensagens da lista recebidas cujo emitente é igual ao parametro destinatario
		//ordenar a lista conversa pelo id das mensagens
		//retornar a lista conversa
				
		Individual ind = repositorio.localizarIndividual(nomeindividuo);	
		if(ind == null) 
			throw new Exception("criar mensagem - emitente nao existe:" + nomeindividuo);

		
		Participante dest = repositorio.localizarParticipante(nomedestinatario);	
		if(dest == null) 
			throw new Exception("criar mensagem - destinatario nao existe:" + nomedestinatario);
		
		
		ArrayList<Mensagem> listaConversa = new ArrayList<>();
		
		for (Mensagem me : listarMensagensEnviadas(nomeindividuo)) {
			if(me.getNomeDestinatario().equals(nomedestinatario))
				listaConversa.add(me);
		}
		
		for (Mensagem mr : listarMensagensRecebidas(nomeindividuo)) {
			if(mr.getNomeEmitente().equals(nomedestinatario))
				listaConversa.add(mr);
		}
		
		// Ordenar a lista conversa pelo id das mensagens
	    listaConversa.sort(Comparator.comparingInt(Mensagem::getId));
		
		return listaConversa;
			
		
	}

	
	
	public static void apagarMensagem(String nomeindividuo, int id) throws  Exception{
		
		Individual emitente = repositorio.localizarIndividual(nomeindividuo);	
		if(emitente == null) 
			throw new Exception("apagar mensagem - nome nao existe:" + nomeindividuo);
		
		
		Mensagem m = emitente.localizarEnviada(id);
		if(m == null)
			throw new Exception("apagar mensagem - mensagem nao pertence a este individuo:" + id);
		
		
		emitente.removerEnviada(m);
		
		Participante destinatario = m.getDestinatario();
		
		destinatario.removerRecebida(m);
		
		repositorio.remover(m);	
		
		//repositorio.salvarObjetos();

		if(destinatario instanceof Grupo g) {
			ArrayList<Mensagem> lista = destinatario.getEnviadas(); //testar se o grupo contem enviadas para todos os individuos
			lista.removeIf(new Predicate<Mensagem>() {
				@Override
				public boolean test(Mensagem t) {
					if(t.getId() == m.getId()) {
						t.getDestinatario().removerRecebida(t);
						repositorio.remover(t);	
						
						//repositorio.salvarObjetos();
						return true;		//apaga mensagem da lista
					}
					else
						return false;
				}

			});

		}
	}

	
	
	public static ArrayList<Mensagem> espionarMensagens(String nomeadministrador, String termo) throws Exception{
		//localizar individuo no repositorio
		//verificar se individuo é administrador
		//listar as mensagens que contem o termo no texto
		
		Individual ind = repositorio.localizarIndividual(nomeadministrador);	
		if(ind == null) 
			throw new Exception("espionar mensagem - individuo não existe:" + nomeadministrador);
		
		
		if(ind.getAdministrador() == false) {
			throw new Exception("criar mensagem - individuo não é administrador:" + nomeadministrador);
		}
		
		
		if (termo.isEmpty()) {
	        return listarMensagens();
	    }

		
	    ArrayList<Mensagem> mensagensEncontradas = new ArrayList<>();

	    for (Mensagem mensagem : listarMensagens()) {
	        if (mensagem.getTexto().contains(termo)) {
	            mensagensEncontradas.add(mensagem);
	        }
	    }

	    return mensagensEncontradas;
		
	}

	
	
	public static ArrayList<String> ausentes(String nomeadministrador) throws Exception{
		//localizar individuo no repositorio
		//verificar se individuo é administrador
		//listar os nomes dos participante que nao enviaram mensagens
		
		Individual ind = repositorio.localizarIndividual(nomeadministrador);	
		if(ind == null) 
			throw new Exception("espionar mensagem - individuo não existe:" + nomeadministrador);
		
		
		if(ind.getAdministrador() == false) {
			throw new Exception("criar mensagem - individuo não é administrador:" + nomeadministrador);
		}
		
		
		ArrayList<String> listaAusentes = new ArrayList<>();
		
		for (Individual individuo : listarIndividuos()) {
			if (individuo.getEnviadas().isEmpty()) {
				listaAusentes.add(individuo.getNome());
			}
			
		}
		
		return listaAusentes;
		
		
	}

	
}