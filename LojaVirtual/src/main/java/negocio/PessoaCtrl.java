package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.Fone;
import beans.Pessoa;
import persistencia.FoneDAO;
import persistencia.PessoaDAO;

@ManagedBean
@SessionScoped
public class PessoaCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	public Pessoa pessoa;
	public String filtro = "";
	public List<Pessoa> lista = new ArrayList<>();

	public List<Pessoa> getLista() {
		return lista;
	}

	public void setLista(List<Pessoa> lista) {
		this.lista = lista;
	}

	public Pessoa getPessoa() {
		if (pessoa == null) {
			pessoa = new Pessoa();
		}
		return pessoa;
	}

	public void setProduto(Pessoa pessoa) {
		this.pessoa = pessoa;

	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Pessoa> getListagem() {
		return PessoaDAO.listagem(filtro);
	}

	public String buscar() {
		lista = PessoaDAO.listagem(filtro);
		return "lista_pessoa";

	}

	public String actionGravar() {
		if (pessoa.getId() == 0) {
			PessoaDAO.inserir(pessoa);
			return actionInserir();
		} else {
			PessoaDAO.alterar(pessoa);
			return "lista_pessoa";
		}
	}

	public String actionInserir() {
		pessoa = new Pessoa();
		return "form_pessoa";

	}

	public String actionExcluir(Pessoa p) {
		PessoaDAO.excluir(p);
		return "lista_pessoa";
	}

	public String actionAlterar(Pessoa p) {
		pessoa = p;
		return "form_pessoa";
	}

	public String actionInserirFone() {

		Fone fone = new Fone();
		fone.setPessoa(pessoa);
		pessoa.getFones().add(fone);
		return "form_pessoa";
	}

	public String actionExcluirFone(Fone fone) {
		getPessoa().getFones().remove(fone);
		FoneDAO.excluir(fone);
		return "form_pessoa";

	}

}
