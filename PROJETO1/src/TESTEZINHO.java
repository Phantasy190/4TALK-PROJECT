import regras_negocio.Fachada;

public class TESTEZINHO {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		//Participante p1 = new Participante("Marcos");
		//Participante p2 = new Participante("Lucas");
		//Mensagem m1 = new Mensagem("Olá", p1, p2);
		//System.out.println(m1.getMessageContent());
		
		//Individual i1 = new Individual("Luiza", "123", false);
		//Individual i2 = new Individual("Junior", "321", false);
		//Grupo g1 = new Grupo("Grupo1");
		
		//System.out.println(i1.getNome() + "\n" + i2.getNome() + "\n" + g1.getNome() + "\n" + g1.listagemIndividuos());
		
		Fachada fachada = Fachada.getInstance();
		//System.out.println(fachada.listarIndividuos()); 
		//METODO OK, PORÉM FALTA COMPROVAR SE RETORNA INSTANCIAS DE INDIVIDUAL
		
		//fachada.criarIndividuo("Kakarotto", "123456");
		//fachada.criarIndividuo("Trunks", "12345");
		//fachada.criarAdministrador("Gohan", "1234");
		//fachada.criarGrupo("Guerreiros Z");
		//fachada.inserirGrupo("Trunks", "Guerreiros Z");
		
		//TESTE listagemGrupos() e listagemIndividuos() das classes Individual e Grupo
		//Individual i3 = new Individual("Maria", "123", false);
		//Individual i4 = new Individual("Amélia", "123", false);
		//Individual i5 = new Individual("Jefferson", "123", false);
		//Individual i6= new Individual("Anderson", "123", false);
		//Grupo g2 = new Grupo("League of Legends");
		//g2.inserirIndividuo(i3);
		//g2.inserirIndividuo(i4);
		//g2.inserirIndividuo(i5);
		//g2.inserirIndividuo(i6);
		
		//i3.inserirGrupo(g2);
		//i4.inserirGrupo(g2);
		//i5.inserirGrupo(g2);
		//i6.inserirGrupo(g2);
		
		//System.out.println(g2.listagemIndividuos()); 
		//System.out.println(i3.listagemGrupos()); 
			
		//System.out.println(fachada.listarIndividuos());
		//System.out.println(fachada.listarGrupos());
		
		//TESTE fachada.criarMensagem() e ver se ela a mensagem é enviada para todos os membros
		//do grupo, caso o destinatario seja um grupo.
		
		fachada.criarIndividuo("Maria", "123");
		fachada.criarIndividuo("Amélia", "123");
		fachada.criarIndividuo("Jefferson", "123");
		fachada.criarIndividuo("Anderson", "123");
		fachada.criarAdministrador("Jorge", "1234");
		
		
		System.out.println(fachada.listarIndividuos());
		
		fachada.criarGrupo("League of Legends");
		
		System.out.println(fachada.listarGrupos());
		
		fachada.inserirGrupo("Maria", "League of Legends");
		fachada.inserirGrupo("Amélia", "League of Legends");
		fachada.inserirGrupo("Jefferson", "League of Legends");
		fachada.inserirGrupo("Anderson", "League of Legends");
		
		fachada.criarMensagem("Jefferson", "League of Legends", "Oi");
		fachada.criarMensagem("Jefferson", "Maria", "Olá");
		System.out.println("\n\n\nTeste LISTAR MENSAGENS\n" + fachada.listarMensagens() + "\n");
		
		fachada.criarMensagem("Maria", "Jefferson", "Oi");
		fachada.criarMensagem("Jefferson", "Maria", "Boa Tarde");
		fachada.criarMensagem("Maria", "Jefferson", "Prazer em conhecer ^^");
		fachada.criarMensagem("Jefferson", "Maria", "Me chamo Jefferson");
		fachada.criarMensagem("Jefferson", "Jefferson", "Teste Emitente e Destinatario iguais");
		
		System.out.println("\n\n\nTeste LISTAR MENSAGENS\n" + fachada.listarMensagens() + "\n");
		
		//System.out.println("\n\n\nTeste OBTER CONVERSA\n" + fachada.obterConversa("Jefferson", "Maria"));
		
		//TESTE METODO fachada.apagarMensagem()
		//fachada.apagarMensagem("Jefferson", 1);
		//System.out.println("\n\n\nTeste LISTAR MENSAGENS APÓS APAGÁ-LAS\n" + fachada.listarMensagens() + "\n");
		
		
		
		//TESTE MÉTODO fachada.espionarMensagens
		//System.out.println("\n\n\nTeste ESPIONAR MENSAGENS\n" + fachada.espionarMensagens("Jorge", "^"));
		
		
		
		//TESTE MÉTODO fachada.ausentes()
		//System.out.println("\n\n\nTeste ESPIONAR AUSENTES\n" + fachada.ausentes("Jorge"));
		
		
		//TESTE MÉTODO fachada.validarIndividuo()
		//System.out.println("\n\n\nTeste VALIDAR INDIVIDUO\n" + fachada.validarIndividuo("Jefferson", "123" ));
		
		
		
		
		
		
		
															
	}

}
