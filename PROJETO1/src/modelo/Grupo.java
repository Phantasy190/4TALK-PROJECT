package modelo;
import java.util.ArrayList;






public class Grupo extends Participante {
		
	private ArrayList<Individual> individuos;
	
	
	
	
	
	//INSTANCIA-OBJETO "GRUPO" NÃO PODE TER NOME VAZIO, 
	//EXCECAO DE CRIACAO DE OBJETO SENDO TRATADA EM fachada.criarGrupo()
	public Grupo(String nome) {
		
		super(nome);
		this.individuos = new ArrayList<>();
		
		
		
	}
	
	
//==MÉTODOS============================================================================
	
	
	
	//Método para interagir com fachada.inserirGrupo()
	//Método para inserir um objeto Individual no ArrayList individuos
	public void adicionar(Individual individuo) {
		
		individuos.add(individuo);
		
		//return getNome();   
        
    }
	
	
	//Método para interagir com fachada.removerGrupo()
	//Método para remover um objeto Individual no ArrayList individuos
	public void removerIndividuo(Individual individuo) {
		
		individuos.remove(individuo);
		
		 
        
    }
	
	

	public ArrayList<Individual> getIndividuos() {
		
		return individuos;	
	}
	
	
	
	//Método para interagir com fachada.inserirGrupo()
	//Método para checar se um individuo existe existe no ArrayList individuos
	//passando o objeto Individual correspondente ao nome do individuo como parametro
	public boolean localizarIndividuo(Individual ind) {
		
		for (Individual individuo : individuos) {
	        if (individuo.equals(ind)) {
	            return true; // Retorna o indivíduo que pertence ao grupo especificado
	        }
	    }
		
	    return false;
    }
	
	
	
//==MÉTODOS OVERRIDE============
	
	@Override
	public String toString() {
        return super.toString();
    }
	
	
	
	// Override do método getNome() da superclasse
	
	@Override
    public String getNome() {
        return super.getNome(); 
    }
	
	
	@Override
    public void adicionarRecebida(Mensagem m) {
	   	
		super.adicionarRecebida(m);   
		
		//grupo.setEmitente(this);
		//for para percorrer o array individuos
		//setar o grupo como emitente
		//enviar a mensagem para cada ocorrencia do array individuos
		//ASSIM QUE ENVIAREM MENSAGEM PARA UM GRUPO, ENVIAR A MESMA MENSAGEM PARA TODOS 
		//OS MEMBROS DO GRUPO, COM O EMITENTE SENDO O GRUPO
            
        
    }
	
	
	@Override
	public ArrayList<Mensagem> getEnviadas() {
		
		return super.getEnviadas();	// Chama o método getEnviadas() da superclasse Participante
	    
	}
	
	
	@Override
	public ArrayList<Mensagem> getRecebidas() {
		
	    return super.getRecebidas();	// Chama o método getRecebidas() da superclasse Participante
	    
	}


	//public Individual[] getIndividuos() {
		// TODO Auto-generated method stub
	//	return null;
	//}


	
	
}
