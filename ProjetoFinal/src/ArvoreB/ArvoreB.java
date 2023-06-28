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
			
			//adiciona no próximo e vê se ele está cheio
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
		
		
		//Caso 1 - remoção simples
		//Se (for uma folha e tiver t ou mais chaves)
			//Deleta a chave
			//return;
		
		//Caso 2 - remoção por filhos
		//Senão, Se (não for uma folha)
			//Se (um dos filhos tiver t ou mais chaves)
				//(*) Chama um metodo recursivo que:
					//Se (for uma folha)
						//Manda a chave desejada para cima
					//Senão, Se (um dos filhos adjacentes a chave possui t ou mais chaves)
						//recursividade para o filho
						//Manda a chave recebida pra cima
					//Senão
						//funde os filhos adjacentes com a chave
						//recursividade consigo mesmo
						//manda a chave recebida para cima
				//Substitue a chave a ser deletada pela recebida
				//return;
			//Senão, Se (possui menos que t chaves)
				//return;
			//Senão, Se os filhos possuem menos que t chaves)
				//Desce a chave a ser deletada e a funde com esse filhos
				//recursividade consigo mesmo
				//return;
		
		//...
		//Caso 3 - remoção por pai
		//Se (for raiz ou possui t ou mais chaves)
			//Se (filho(i) e um dos irmãos adjacentes possuem menos de t chaves)
				//funde filho com um deles
		
		//recursão para o próximo nó
		
		//Se (resultado da recurção do filhoA for 'true')
			//Se (há irmãoB do filhoA com t ou mais chaves)
				//Se (irmãoB for folha)
					//passar uma chave de B pro pai, e do pai pro A
				//Senão
					//metodo de remover chave para retirar uma chave de B e passar pro pai, e do pai pro A
					//AVISO: se ele não é folha então também têm que entrar um filho junto, mas isso não é possivel
		
		
		
		removeChave(chave, root);
	}
	
	private boolean removeChave(int chave, NoB noAtual) {
		//Acha a chave desejada ou a direção dela
		int i = 0;
		while(i < noAtual.getSizeOfChaves() && chave > noAtual.getChave(i)) {
			i++;
		}
		
		//Procedimentos de remoção
		if(i < noAtual.getSizeOfChaves() && chave == noAtual.getChave(i)) {
			//Possui a chave a ser deletada:
			
			if(noAtual.isLeaf() && noAtual.getSizeOfChaves() >= t) {
				//Caso 1 - Remoção simples
				noAtual.removeChave(i);
				
			} else if(!noAtual.isLeaf()){
				//Caso 2 - Remoção por filho (?)
				if(noAtual.getFilho(i).getSizeOfChaves() >= t) {
					//Caso 2.a
					int novaChave = substituirChaveADireita(noAtual.getFilho(i));
					noAtual.setChave(i, novaChave);
					
				} else if(noAtual.getFilho(i+1).getSizeOfChaves() >= t) {
					//Caso 2.b
					int novaChave = substituirChaveAEsquerda(noAtual.getFilho(i+1));
					noAtual.setChave(i, novaChave);
					
				} else if(noAtual.equals(root) || noAtual.getSizeOfChaves() >= t){
					//Caso 2.c
					fundirFilhos(noAtual, i); //(?)
					removeChave(chave, noAtual.getFilho(i));
					
				} else {
					
					return true;
				}
			} else {
				
				return true;
			}
		} else if(!noAtual.isLeaf()) {
			//Não possui a chave a ser deletada:
			
			//Caso 3.a - Funde dois nós com t-1 chaves pelo caminho
			if((noAtual.equals(root) || noAtual.getSizeOfChaves() >= t) && (noAtual.getFilho(i).getSizeOfChaves() < t)) {
				if(0 < i && noAtual.getFilho(i-1).getSizeOfChaves() < t) {
					fundirFilhos(noAtual, i-1);
				} else if(i < noAtual.getFilho(i).getSizeOfChaves() && noAtual.getFilho(i+1).getSizeOfChaves() < t) {
					fundirFilhos(noAtual, i);
				}
			}
			if(root.getSizeOfChaves() == 0) {
				root = root.getFilho(0);
			}
			
			
			boolean retry = removeChave(chave, noAtual.getFilho(i));
			
			//Caso 3.b - Desloca de algum irmão e tenta novamente 
			if(retry) {
				if(noAtual.getFilho(i).isLeaf()) {
					if(0 < i && noAtual.getFilho(i-1).getSizeOfChaves() >= t) {
						//passarChaveADireita(noAtual, i-1);
						int chaveDeslocada = substituirChaveADireita(noAtual.getFilho(i-1));
						int chaveDesejada = noAtual.getChave(i-1);
						noAtual.setChave(i-1, chaveDeslocada);
						noAtual.getFilho(i).addChave(chaveDesejada);
						removeChave(chave, noAtual.getFilho(i));
						
					} else if(i < noAtual.getFilho(i).getSizeOfChaves()&& noAtual.getFilho(i+1).getSizeOfChaves() >= t) {
						int chaveDeslocada = substituirChaveAEsquerda(noAtual.getFilho(i+1));
						int chaveDesejada = noAtual.getChave(i);
						noAtual.setChave(i, chaveDeslocada);
						noAtual.getFilho(i).addChave(chaveDesejada);
						removeChave(chave, noAtual.getFilho(i));
						
					}
				} else {
					System.out.println("Recomenta-se que apenas o valor do nó seja excluido.");
				}
			}
		}
	
		return false;
	}
	
	private int substituirChaveADireita(NoB noAtual) {
		int index = noAtual.getSizeOfChaves()-1;
		
		if(noAtual.isLeaf()) {
			int retorno = noAtual.getChave(index);
			noAtual.removeChave(index);
			
			return retorno;
		} else if(noAtual.getFilho(index+1).getSizeOfChaves() >= t) {
			
			return substituirChaveADireita(noAtual.getFilho(index+1));
		} else if(noAtual.getFilho(index).getSizeOfChaves() >= t) {
			int retorno = noAtual.getChave(index+1);
			noAtual.setChave(index, substituirChaveADireita(noAtual.getFilho(index)));
			
			return retorno;
		} else {
			fundirFilhos(noAtual, index);
			
			return substituirChaveADireita(noAtual.getFilho(index));
		}
	}
	
	private int substituirChaveAEsquerda(NoB noAtual) {
		if(noAtual.isLeaf()) {
			int retorno = noAtual.getChave(0);
			noAtual.removeChave(0);
			
			return retorno;
		} else if(noAtual.getFilho(0).getSizeOfChaves() >= t) {
			
			return substituirChaveAEsquerda(noAtual.getFilho(0));
		} else if(noAtual.getFilho(1).getSizeOfChaves() >= t) {
			int retorno = noAtual.getChave(0);
			noAtual.setChave(0, substituirChaveAEsquerda(noAtual.getFilho(1)));
			
			return retorno;
		} else {
			fundirFilhos(noAtual, 0);
			
			return substituirChaveAEsquerda(noAtual.getFilho(0));
		}
	}

	private void fundirFilhos(NoB noPai, int index) {
		NoB noFilhoA = noPai.getFilho(index);
		NoB noFilhoB = noPai.getFilho(index+1);
		NoB novoFilho = new NoB(noFilhoA.isLeaf(), noFilhoA.getFilho(0));
		
		
		for(int i = 0; i < t-1; i++) {
			novoFilho.addChave(noFilhoA.getChave(i));
			novoFilho.setFilho(i+1, noFilhoA.getFilho(i+1));
		}
		novoFilho.addChave(noPai.getChave(index));
		novoFilho.setFilho(t, noFilhoB.getFilho(0));
		for(int i = 0; i < t-1; i++) {
			novoFilho.addChave(noFilhoB.getChave(i));
			novoFilho.setFilho(i+1+t, noFilhoB.getFilho(i+1));
		}
		
		noPai.removeChave(index);
		noPai.setFilho(index, novoFilho);
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
	@Override
	public String toString() {
		String texto = "Arvore (t = "+t+"):\n";
		texto = texto.concat(String.valueOf(root));
		
		return texto;
	}
}