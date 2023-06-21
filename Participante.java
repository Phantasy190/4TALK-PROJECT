import java.time.LocalDateTime;
import java.util.ArrayList;

public class Participante {
	
    public String nome;
    public ArrayList<Mensagem> recebidas;
    public ArrayList<Mensagem> enviadas;
   
    

    
    public Participante(String nome) {
    	
        this.nome = nome;
        this.recebidas = new ArrayList<>();
        this.enviadas = new ArrayList<>();
               
    }
    
    public String getNome() {
    	
        return nome;
               
    }

}
