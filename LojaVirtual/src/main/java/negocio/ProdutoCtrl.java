package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.*;

import beans.Produto;
import persistencia.ProdutoDAO;

@ManagedBean
@SessionScoped
public class ProdutoCtrl implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Produto produto;
	public String filtro = "";
	public 	List<Produto> lista = new ArrayList<>();

	public List<Produto> getLista() {
		return lista;
	}
	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
		
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro (String filtro) {
		this.filtro = filtro;
	}

	public List<Produto> getListagem() {
		return ProdutoDAO.listagem(filtro);
	}
	
	public String buscar() {
		lista = ProdutoDAO.listagem(filtro);
		return "lista_produto";
		
	}
	
	public String actionGravar() {
		if(produto.getId() == 0) {
			ProdutoDAO.inserir(produto);
			return actionInserir();
		}
		else {
			ProdutoDAO.alterar(produto);
			return "lista_produto";
		}
	}		
		public String actionInserir() {
			produto = new Produto();
			return "form_produto";
			
		}
		public String actionExcluir(Produto p) {
			ProdutoDAO.excluir(p);
			return "lista_produto";
		}
		public String actionAlterar(Produto p) {
			produto = p;
			return "form_produto";
		}
	
}

