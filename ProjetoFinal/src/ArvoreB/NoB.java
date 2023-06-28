package ArvoreB;

import java.util.*;
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
	
	public void setChave(int index, int chave) {
		chaves.set(index, chave);
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
	
	
	@Override
	public String toString() {
		String texto = "NoB " + chaves + "\n";
		
		if(!leaf) {
			for(int i = 0; i <= chaves.size(); i++) {
				texto = texto.concat(filhoToString("", filhos.get(i), 1));
			}
		}
		
		return texto;
	}
	
	private String filhoToString(String texto, NoB noAtual, int contador) {
		for(int i = 0; i < contador; i++) {
			texto = texto.concat("   -");
		}
		
		texto = texto.concat("> NoB [");
		for(int i = 0; i < noAtual.getSizeOfChaves(); i++) {
			texto = texto.concat(String.valueOf(noAtual.getChave(i)));
			
			if(i < noAtual.getSizeOfChaves()-1) {
				texto = texto.concat(", ");
			}
		}
		texto = texto.concat("]\n");
		
		if(!noAtual.isLeaf()) {
			for(int i = 0; i <= noAtual.getSizeOfChaves(); i++) {
				texto = filhoToString(texto, noAtual.getFilho(i), contador+1);
			}
		}
		
		return texto;
	}//*/
}