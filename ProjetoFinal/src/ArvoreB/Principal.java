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
		arvore.addChave(23);
		arvore.addChave(1);
		arvore.addChave(4);
		arvore.addChave(15);
		arvore.addChave(10);
		arvore.addChave(24);
		arvore.addChave(16);
		
		//arvore.addChave(30);//*/<-Split
		
		arvore.removeChave(12);
		System.out.println(arvore);
		
		arvore.removeChave(10);
		System.out.println(arvore);
		
		arvore.removeChave(9);
		System.out.println(arvore);
		
		
		System.out.println("fim");
	}
}
