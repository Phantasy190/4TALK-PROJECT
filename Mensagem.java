import java.time.LocalDateTime;





public class Mensagem {
	
	//incrementador da variavel ID, para cada instancia/objeto de mensagem
	private static int proximoId = 1;
	
	
	public int id;
    public String texto;
    private Participante emitente;
    private Participante destinatario;
    public LocalDateTime dataHora;
    

    
    public Mensagem(String texto, Participante emitente, Participante destinatario) throws IllegalArgumentException {
    	
    	if (texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException("O texto da mensagem não pode ser vazio");
        }
    	
        this.id = proximoId++;
        this.texto = texto;
        this.emitente = emitente;
        this.destinatario = destinatario;
        this.dataHora = LocalDateTime.now();
               
    }
    
    public String getMessageContent() {
        String informacoes = "ID: " + id + "\n";
        informacoes += "Texto: " + texto + "\n";
        informacoes += "Emitente: " + emitente.getNome() + "\n";
        informacoes += "Destinatário: " + destinatario.getNome() + "\n";
        informacoes += "Data e Hora: " + dataHora.toString() + "\n";

        return informacoes;
    }
    
    
    
    
    
    
    
    
    

}
