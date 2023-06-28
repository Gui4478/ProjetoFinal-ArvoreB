package ArvoreB;

public class Principal {
	public static void main(String[] args) {
		ArvoreB arvore = new ArvoreB(2);
		
		arvore.addChave(3);
		arvore.addChave(20);
		arvore.addChave(18);
		arvore.addChave(9); //<- Split
		arvore.addChave(14);
		arvore.addChave(5);
		arvore.addChave(8);
		arvore.addChave(2);
		arvore.addChave(12);
		arvore.addChave(6);
		arvore.addChave(7); //<- Split
		arvore.addChave(23);  //<- (*)
		arvore.addChave(1);
		arvore.addChave(4);
		arvore.addChave(15);
		arvore.addChave(10);
		arvore.addChave(24);
		
		//System.out.println(arvore);
		
		arvore.removeChave(10);
		
		System.out.println(arvore);
		
		arvore.removeChave(14);
		
		System.out.println(arvore);
		
		//Lembrete: estava revisando o caso 2 (.a e .b funcionam, falta .c) 
		
		/*
		arvore.buscarChave(6);
		arvore.buscarChave(14);
		arvore.buscarChave(23);
		

		arvore.removeChave(2);
		arvore.removeChave(18);
		arvore.removeChave(7);
		arvore.removeChave(14);
		arvore.removeChave(6);
		//*/
		
		System.out.println("fim");
	}
}
