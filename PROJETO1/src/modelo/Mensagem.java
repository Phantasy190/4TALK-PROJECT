package modelo;
import java.time.LocalDateTime;



public class Mensagem {
	
	//incrementador da variavel ID, para cada instancia/objeto de mensagem
	//private static int proximoId = 1;
	
	
	public int id;
    public String texto;
    private Participante emitente;
    private Participante destinatario;
    public LocalDateTime dataHora;
    
  
    //INSTANCIA-OBJETO "MENSAGEM" NÃO PODE TER TEXTO VAZIO, EXCECAO DE CRIACAO DE OBJETO 
  	//SENDO TRATADA EM fachada.criarMensagem() QUANTO NO CONSTRUTOR DE "MENSAGEM"
    public Mensagem(int id, Participante emitente, Participante destinatario, String texto) throws IllegalArgumentException {
    	
    	if (texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException("O texto da mensagem não pode ser vazio");
        }
    	
        this.id = id;
        this.texto = texto;
        this.emitente = emitente;
        this.destinatario = destinatario;
        this.dataHora = LocalDateTime.now();
               
    }
    
    
    
//==MÉTODOS============================================================================
    
    
    public String toString() {
    	
    	String informacoes = "ID: " + id + "\n";
        informacoes += "Texto: " + texto + "\n";
        informacoes += "Emitente: " + emitente.getNome() + "\n";
        informacoes += "Destinatário: " + destinatario.getNome() + "\n";
        informacoes += "Data e Hora: " + dataHora.toString() + "\n";

        return informacoes;
    }
    
    
    
    
    //Método para interagir com fachada.criarMensagem()
  	//Método para setar o ID de uma copia igual ao do objeto Mensagem original
    public int getId() {
    	
        return id;
        
    }    
    
    
    //Método para interagir com fachada.criarMensagem()
    //Método para interagir com Mensagem.getID() ou com id da mensagem passado manualmente 
    //como parametro
    //Método do tipo "setID" especial para interagir somente com cópias da mensagem, 
    //com o diferencial que ele irá decrementar a variável "próximoID" sempre que for utilizado.
    
  	//Método para setar o ID de uma copia igual ao do objeto Mensagem original
    public void setIDCopia(int mensagemId) {
    	
        id = mensagemId;
        
        //proximoId--; 
        
    }    
    
    
    //Método para interagir com fachada.criarMensagem()
  	//Método para setar o ID de uma copia igual ao do objeto Mensagem original
    public String getTexto() {
    	
        return texto;
        
    }  
    
    
    //Método para interagir com fachada.obterConversa()
  	//Método para retornar o ID juntamente com o Texto da mensagem
    public String getTextoAndID() {
    	
    	String informacoes = emitente + "\\n";
    	informacoes += "ID: " + id + "\n";
        informacoes += "Texto: " + texto + "\n";
        
        return informacoes;
        
    }  
    
    
    //Método para interagir com fachada.criarMensagem()
  	//Método para associar um objeto Individual ao atributo emitente
    public  void setEmitente(Individual individuo) {
    	
        emitente = individuo;
        
    }    
    
    
    
    public Participante getEmitente() {
		
		return emitente;
	}
    
    
    
    //Método para interagir com fachada.obterConversa()
  	//Método para retornar o nome do atributo emitente
    //LEMBRAR DE POSSIVELMENTE MODIFICAR O METODO PARA getNomeDestinatario
    public String getNomeEmitente() {
    	
        return emitente.getNome();
        
    } 
    
    
    //Método para interagir com fachada.criarMensagem()
  	//Método para associar um objeto Participante ao atributo destinatario
    public  void setDestinatario(Participante participante) {
    	
        destinatario = participante;
        
    }    
    
    
    public Participante getDestinatario() {
		
		return destinatario;
	}
    
    
    //Método para interagir com fachada.obterConversa()
  	//Método para retornar o nome do atributo destinatario
    //LEMBRAR DE POSSIVELMENTE MODIFICAR O METODO PARA getNomeDestinatario
    public String getNomeDestinatario() {
    	
        return destinatario.getNome();
        
    }  
    
    
    
    
    public LocalDateTime getData() {
    	
    	return dataHora;
    }
    
    
    
    //REFORMULAR
    public String getMessageContent() {
        String informacoes = "ID: " + id + "\n";
        informacoes += "Texto: " + texto + "\n";
        informacoes += "Emitente: " + emitente.getNome() + "\n";
        informacoes += "Destinatário: " + destinatario.getNome() + "\n";
        informacoes += "Data e Hora: " + dataHora.toString() + "\n";

        return informacoes;
    }







	
    
    
    
    
    
    
    
    
    

}
