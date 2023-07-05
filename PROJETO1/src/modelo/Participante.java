package modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;





public class Participante {
	
    public String nome;
    public ArrayList<Mensagem> enviadas;
    public ArrayList<Mensagem> recebidas;
    
   
    
    //INSTANCIA-OBJETO "PARTICIPANTE" NÃO PODE TER NOME VAZIO, EXCECAO DE CRIACAO DE OBJETO 
  	//SENDO TRATADO DIRETAMENTE NO CONSTRUTOR DE "PARTICIPANTE" 
    public Participante(String nome) {
    	
    	
    	
    	super();	
        this.nome = nome;
        this.recebidas = new ArrayList<>();
        this.enviadas = new ArrayList<>();
        
        
        
    }
    
    
    
//==MÉTODOS============================================================================
    
    
       
    public String toString() {
        return this.nome;
    }
    
    
    
    public String getNome() {
    	   	
        return this.nome;    
        
    }
    
    
    
    public void adicionarEnviada(Mensagem m) {
	   	
        enviadas.add(m);   
        
    }
    
    
    
    public void removerEnviada(Mensagem m) {
		
    	enviadas.remove(m);
		
	}
    
    
    
    public void adicionarRecebida(Mensagem m) {
	   	
        recebidas.add(m);   
            
        
    }
    
    
    
    public void removerRecebida(Mensagem m) {

    	recebidas.remove(m);
		
	}
    
    
   
    public Mensagem localizarEnviada(int id) {

    	for (Mensagem mensagem : enviadas) {
            if (mensagem.getId() == id) {
                return mensagem;
            }
        }
        return null;
		
	}
    
    
    
    public ArrayList<Mensagem> getEnviadas() {
    	 	
        return enviadas;
        
    }
    
    
    
    public ArrayList<Mensagem> getRecebidas() {
	 	
        return recebidas;
        
    }



	
   
}
