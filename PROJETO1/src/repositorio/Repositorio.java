package repositorio;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

//GRUPO: Geraldo Gama de Oliveira Filho, Arthur Lyra Miranda, Lucas Henrique Santos Chaga




import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import modelo.Grupo;
import modelo.Individual;
import modelo.Mensagem;
import modelo.Participante;




//OBS: MUDAR PARA STATIC ALGUNS METODOS PARA ELES INTERAGIREM COM FACHADA SOMENTE ATRAVÉS
//DA CRIACAO DE UM OBJETO INSTANCIA DE FACHADA

public class Repositorio {
	
	//OBS: MUDAR AS LISTAS PARA PRIVATE
	private TreeMap<String, Participante> participantesMap = new TreeMap<>();
    private ArrayList<Mensagem> mensagens = new ArrayList<>();
    
    
    
    public Repositorio() {
    	
	    Individual adminPadrao = new Individual("admin", "admin", true);
	    
	    //Observar possivel bug salvar objetos
	    participantesMap.put(adminPadrao.getNome(), adminPadrao);
	    
	    //this.carregarObjetos();
    	
    }
    
   
    
    	
    	
	
		
//-----------------------------------------------------------------------------------------------	
	public void carregarObjetos()  	{
		// carregar para o repositorio os objetos dos arquivos csv
		try {
			//caso os arquivos nao existam, serao criados vazios
			File f1 = new File( new File(".\\mensagens.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\individuos.csv").getCanonicalPath() ) ; 
			File f3 = new File( new File(".\\grupos.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() || !f3.exists() ) {
				//System.out.println("criando arquivo .csv vazio");
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				FileWriter arquivo3 = new FileWriter(f3); arquivo3.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}

		String linha;	
		String[] partes;	

		try	{
			String nome,senha,administrador;
			File f = new File( new File(".\\individuos.csv").getCanonicalPath())  ;
			Scanner arquivo1 = new Scanner(f);	 //  pasta do projeto
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				nome = partes[0];
				senha = partes[1];
				administrador = partes[2];
				Individual ind = new Individual(nome,senha,Boolean.parseBoolean(administrador));
				this.adicionar(ind);
			}
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de individuos:"+ex.getMessage());
		}

		try	{
			String nome;
			Grupo grupo;
			Individual individuo;
			File f = new File( new File(".\\grupos.csv").getCanonicalPath())  ;
			Scanner arquivo2 = new Scanner(f);	 //  pasta do projeto
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				nome = partes[0];
				grupo = new Grupo(nome);
				if(partes.length>1)
					for(int i=1; i< partes.length; i++) {
						individuo = this.localizarIndividual(partes[i]);
						grupo.adicionar(individuo);
					}
				this.adicionar(grupo);
			}
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de grupos:"+ex.getMessage());
		}


		try	{
			String id, nomeemitente, nomedestinatario,texto;
			Mensagem m;
			Participante emitente,destinatario;
			File f = new File( new File(".\\mensagens.csv").getCanonicalPath() )  ;
			Scanner arquivo3 = new Scanner(f);	 //  pasta do projeto
			while(arquivo3.hasNextLine()) 	{
				linha = arquivo3.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				id = partes[0];
				nomeemitente = partes[1];
				nomedestinatario = partes[2];
				texto = partes[3];
				emitente = this.localizarParticipante(nomeemitente);
				destinatario = this.localizarParticipante(nomedestinatario);
				m = new Mensagem(Integer.parseInt(id),emitente,destinatario,texto);
				this.adicionar(m); 
			} 
			arquivo3.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de mensagens:"+ex.getMessage());
		}

	}


	
	public void	salvarObjetos()  {
		//gravar nos arquivos csv os objetos que estão no repositório
		try	{
			File f = new File( new File(".\\mensagens.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Mensagem m : mensagens) 	{
				arquivo1.write(	m.getId()+";"+
						m.getEmitente().getNome()+";"+
						m.getDestinatario().getNome()+";"+
						m.getTexto()+"\n");	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na criação do arquivo  mensagens "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\individuos.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			for(Individual ind : this.getIndividuos()) {
				arquivo2.write(ind.getNome() +";"+ ind.getSenha() +";"+ ind.getAdministrador() +"\n");	
			} 
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo  individuos "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\grupos.csv").getCanonicalPath())  ;
			FileWriter arquivo3 = new FileWriter(f) ; 
			for(Grupo g : this.getGrupos()) {
				String texto="";
				for(Individual ind : g.getIndividuos())
					texto += ";" + ind.getNome();
				arquivo3.write(g.getNome() + texto + "\n");	
			} 
			arquivo3.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criação do arquivo  grupos "+e.getMessage());
		}
	}
	
	
//----------------------------------------------------------------------------------------------
	
	
	public ArrayList<Individual> getIndividuos() {
		//cria um arraylist para armazenar os objetos Individual obtidos através de 
		//uma busca na TreeMap de participante, difereciando-os se são instancias de Individual
		//através do metodo instanceof
		
		
		ArrayList<Individual> individuos = new ArrayList<>();

	    for (Participante participante : participantesMap.values()) {
	        if (participante instanceof Individual) {
	            individuos.add((Individual) participante);
	        }
	    }

	    return individuos;
	}
	
	
	
	public ArrayList<Grupo> getGrupos() {
		//Método similar ao anterior
		//.................................
		//cria um arraylist para armazenar os objetos Grupo obtidos através de 
		//uma busca na TreeMap de participante, difereciando-os se são instancias de Grupo
		//através do metodo instanceof
		
		
		ArrayList<Grupo> grupos = new ArrayList<>();

	    for (Participante participante : participantesMap.values()) {
	        if (participante instanceof Grupo) {
	            grupos.add((Grupo) participante);
	        }
	    }

	    return grupos;						
	}
	

	
	public Individual localizarIndividual(String nome) {
		//Método para retornar um objeto da classe Individual em "participantesMap" de acordo
		//com o nome do individuo passado como parametro
		
		
	    for (Participante participante : participantesMap.values()) {
	        if (participante instanceof Individual && participante.getNome().equals(nome)) {
	            return (Individual) participante;
	        }
	    }

	    return null;
	}

	
	
	public Participante localizarParticipante(String nome) {
		
		 for (Participante participante : participantesMap.values()) {
		        if (participante.getNome().equals(nome)) {
		            return participante;
		        }
		    }
		 return null; // Retorna null caso o nome não seja encontrado
			
	}
	
	
	
	public Grupo localizarGrupo(String nome) {
		//Método para retornar um objeto da classe Individual em "participantesMap" de acordo
		//com o nome do individuo passado como parametro
		
		
	    for (Participante participante : participantesMap.values()) {
	        if (participante instanceof Grupo && participante.getNome().equals(nome)) {
	            return (Grupo) participante;
	        }
	    }

	    return null;
	}
	
	
	
	
	//Método para adicionar uma instancia objeto de Participante, Individual ou Grupo
	//em "participantesMap", setando o nome do Individuo 
	//como chave da ocorrencia da TreeMap, obtendo o nome através 
	//do metodo getNome() na classe Individual
	public void adicionar(Participante participante) {
		
		
		 //participantesMap.put(individuo.getNome(), individuo);
		 if (participante instanceof Individual) {
			 Individual individuo = (Individual) participante;
			 participantesMap.put(individuo.getNome(), individuo);
		 } 
		 
		 else if (participante instanceof Grupo) {
			 Grupo grupo = (Grupo) participante;
			 participantesMap.put(grupo.getNome(), grupo);
		 }
		 	
		 
	}
	
	
	
	
	
	public ArrayList<Mensagem> getMensagens() {
		
		return mensagens;
		
	}
	
	
	
	
	//Método para adicionar uma instancia objeto de Mensagem no ArrayList "mensagens"
	public void adicionar(Mensagem m) {
		
		mensagens.add(m);
		
	}

	
	
	//REMOVER MENSAGEM DO ARRAYLIST MENSAGENS
	public void remover(Mensagem m) {


		mensagens.remove(m);
		
	}


	
	
	
	
	//public void getIndividuoSenha() {
		//Método para adicionar uma instancia objeto de Individual em "participantesMap"
		//setando o nome do Individuo como chave da ocorrencia da TreeMap, obtendo o nome
		//através do metodo getNome() na classe Individual
		
		
	//	 participantesMap.put(individuo.getNome(), individuo);
		 
	//}
	
	
	
	
	
	
	
	
//==MÉTODOS DESCARTADOS===============================================================
	
	//getMensagens descartado para funcionar TreeMap<Mensagem>;
		//public ArrayList<Mensagem> getMensagens() {
			//cria um arraylist para armazenar os objetos Mensagem obtidos através de 
			//uma busca na TreeMap de Mensagem
			
			
			//ArrayList<Mensagem> mensagens = new ArrayList<>();

		    //for (Mensagem mensagem : mensagensMap.values()) {
		    //	mensagens.add(mensagem);
		    //}

		    //return mensagens;						
		//}
		
	
	
}
