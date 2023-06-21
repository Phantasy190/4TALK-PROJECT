
public class TESTEZINHO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		Participante p1 = new Participante("Marcos");
		Participante p2 = new Participante("Lucas");
		Mensagem m1 = new Mensagem("Olá", p1, p2);
		System.out.println(m1.getMessageContent());

	}

}
