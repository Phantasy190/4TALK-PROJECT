package modelo;
import java.util.ArrayList;






public class Individual extends Participante {

	private String senha;
    private boolean administrador;
    private ArrayList<Grupo> grupos;
	
	
    
    //INSTANCIA-OBJETO "INDIVIDUAL" NÃO PODE TER NOME E SENHA VAZIAS, EXCECAO DE CRIACAO DE OBJETO 
  	//SENDO TRATADA EM fachada.criarIndividuo()
	public Individual(String nome, String senha, boolean administrador)  {
	
		
		super(nome);
		this.senha = senha;
		this.administrador = administrador;
		this.grupos = new ArrayList<>();
		
		//FALTA IMPLEMENTAR EXCECAO PARA O NOME DO INDIVIDUO/PARTICIPANTE/GRUPO SER UNICO
		
	}
	
	
	
	
	
	
//==MÉTODOS============================================================================
		
		
		
	public String getSenha() {
		
        return senha; // Método para retornar senha de instancia objeto de Individual
        
    }	
	
	
	
	//Método para interagir com fachada.inserirGrupo()
	//Método para inserir um objeto Individual no ArrayList individuos
	public String inserirGrupo(Grupo g) {
		
		grupos.add(g);
		
		return getNome();   
        
    }
	
	
	
	//Método para interagir com fachada.removerGrupo()
	//Método para remover um objeto Grupo no ArrayList grupos
	public void removerGrupo(Grupo g) {
		
		grupos.remove(g);
		
		 
        
    }
	
	
	
	public Grupo localizarGrupo(String nomegrupo) {
		
		for (Grupo grupo : grupos) {
			if (grupo.getNome().equals(nomegrupo)) {
	            return grupo; // Retorna o grupo encontrado
	        }
	    }
	    return null; 
	}
	
	
	
	//Método para listar os objetos Grupo no ArrayList grupos
	public ArrayList<Grupo> getGrupos() {
		
		return grupos;
		
	} 
        
    
		
	public void setAdministradorTrue() {
		// Método que seta o atributo 'administrador' da instancia objeto Individual para True
		
        administrador = true; 
        
    }	
	
	
	
	public void setAdministradorFalse() {
		// Método que seta o atributo 'administrador' da instancia objeto Individual para False
		
        administrador = false; 
    }	


	
	//Método para interagir com fachada.espionarMensagens()
	//Método que retorna o valor do atributo administrador "true ou false"
	public boolean getAdministrador() {
		
		
        return administrador; 
        
    }	
	
	
	
//==MÉTODOS OVERRIDE============
	
	//@Override
	public String toString() {
		
        return super.toString() + "," + senha + "," + administrador;
        
    }
	
	
	@Override
    public String getNome() {
		
        return super.getNome(); // Chama o método getNome() da superclasse Participante
        
    }
	
	
	@Override
	public void adicionarEnviada(Mensagem m) {
	   	
		super.adicionarEnviada(m);   
        
    }
	
	
	@Override
	public void removerEnviada(Mensagem m) {

		super.removerEnviada(m);
		
	}
    
    
	@Override
    public void adicionarRecebida(Mensagem m) {
	   	
		super.adicionarRecebida(m);   
            
        
    }
	
	
	@Override
	public Mensagem localizarEnviada(int id) {
		
		return super.localizarEnviada(id);
		
	}
	
    
	@Override
	public ArrayList<Mensagem> getEnviadas() {
		
	    return super.getEnviadas();	// Chama o método getEnviadas() da superclasse Participante
	    
	}
	
	
	@Override
	public ArrayList<Mensagem> getRecebidas() {
		
	    return super.getRecebidas();	// Chama o método getRecebidas() da superclasse Participante
	    
	}





	
}

