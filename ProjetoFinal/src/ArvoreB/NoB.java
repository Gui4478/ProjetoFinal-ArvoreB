package ArvoreB;

import java.util.ArrayList;
import java.util.List;

public class NoB {
	private List<Integer> chaves = new ArrayList<Integer>();
	private List<NoB> filhos = new ArrayList<NoB>();
	private boolean leaf;
	
	//Construtor
	public NoB(boolean isLeaf) {
		leaf = isLeaf;
		filhos.add(null);
	}

	public NoB(boolean isLeaf, NoB filho) {
		leaf = isLeaf;
		filhos.add(filho);
	}
	
	
	//Lista de Chaves
	public int getSizeOfChaves() {
		return chaves.size();
	}
	
	public int getChave(int index) {
		return chaves.get(index);
	}
	
	public void addChave(Integer chave) {
		int i = 0;
		while(i < chaves.size() && chave > chaves.get(i)) {
			i++;
		}
		
		chaves.add(i, chave);
		filhos.add(i+1, null);
	}
	
	public void removeChave(int index) {
		chaves.remove(index);
		filhos.remove(index+1);
	}
	
	
	//Lista de Filhos
	public NoB getFilho(int index) {
		return filhos.get(index);
	}
	
	public void setFilho(int index, NoB filho) {
		filhos.set(index, filho);
	}
	
	
	//Se é Folha
	public boolean isLeaf() {
		return leaf;
	}
	
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	
	//@Override
	/* toString(int t?) {
	 * 	retorna a chave, o valor e a uma lista de chaves
	 * }
	 */
}