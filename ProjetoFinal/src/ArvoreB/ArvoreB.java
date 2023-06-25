package ArvoreB;

public class ArvoreB {
	private int t;
	private NoB root;

	//Construtor
	public ArvoreB(int t) {
		this.t = t;
		root = new NoB(true);
	}
	
	
	//Adicionar chave
	public void addChave(int chave) {
		addChave(chave, root);
		
		//se raiz estiver cheia, chama a função para dividir nó
		if(root.getSizeOfChaves() == 2*t) {
			NoB newRoot = new NoB(false, root);
			splitNo(newRoot, 0);
			root = newRoot;
		}
		
	}
	
	private void addChave(int chave, NoB noAtual) {
		if(noAtual.isLeaf()) {
			noAtual.addChave(chave);
		} else {
			//procura qual filho da lista deve ser o próximo
			int i = 0;
			while(i < noAtual.getSizeOfChaves() && chave > noAtual.getChave(i)) {
				i++;
			}
			
			addChave(chave, noAtual.getFilho(i));
			if(noAtual.getFilho(i).getSizeOfChaves() == 2*t) {
				splitNo(noAtual, i);
			}
		}
	}
	
	
	//Dividir nó
	private void splitNo(NoB noPai, int indexFilho) {
		//Define filhos A e B
		NoB noFilhoA = noPai.getFilho(indexFilho);
		NoB noFilhoB = new NoB(noFilhoA.isLeaf(), noFilhoA.getFilho(t));
		
		//A dá metade de suas chaves e filhos pra B
		for(int i = t; i < 2*t; i++) {
			noFilhoB.addChave(noFilhoA.getChave(t));
			noFilhoB.setFilho(i-t+1, noFilhoA.getFilho(t+1));
			noFilhoA.removeChave(t);
		}
		
		//passa a ultima chave de A para seu pai
		noPai.addChave(noFilhoA.getChave(t-1));
		noFilhoA.removeChave(t-1);
		
		//adiciona o B na lista de filhos do pai
		noPai.setFilho(indexFilho+1, noFilhoB);
	}
	
	
	//remover
	public void removeChave(int chave) {
		//recursão até chegar no alvo
		
		//Caso 0? -
		//Se (há um nó com um irmão adjacente com t-1 chaves pelo caminho)
			//Esses nós e a chave que os liga são fundidos em um só nó
		
		//Caso 1 - remoção simples
		//Se (for uma folha e tiver mais que t-1 chaves)
			//Deleta a chave
		
		//Caso 2 -
		//Senão, se (não for uma folha)
			//Se (e um dos filhos tiver mais que t-1 chaves)
				//Deleta e pega de um dos filhos (último da esquerda ou primeiro da direita)
			//Senão,
				//Desce a chave a ser deletada e a funde com esse filhos
				//Situação vira um Cado 2 anterior ou um Caso 1
		
		//Caso 3 -
		//Senão, se (o nó possui t-1 chaves)
			//Se (há um irmão adjacente com mais de t-1 chaves)
				//Puxa a chave mais proxima dele pro pai e pega a do pai pra se
			//Senão
				//Escolhe um irmão e se funde come ele, pegando a chave do pai entre eles
				//Se (o pai ficar com menos de t-1 e não for raiz)
					//Tentar pegar uma chave de alguém OU se fundir com alguém (preferencialmente)
		
		return;
	}
	
	//Buscar chave
	public NoB buscarChave(int chave) {
		return buscarChave(chave, root);
	}
	
	private NoB buscarChave(int chave, NoB noAtual) {
		int i = 0;
		while(i < noAtual.getSizeOfChaves() && chave > noAtual.getChave(i)) {
			i++;
		}
		
		if(i < noAtual.getSizeOfChaves() && chave == noAtual.getChave(i)) {
			return noAtual;
		} else if(noAtual.isLeaf()) {
			return null;
		} else {
			return buscarChave(chave, noAtual.getFilho(i));
		}
	}
	
	//toString
}