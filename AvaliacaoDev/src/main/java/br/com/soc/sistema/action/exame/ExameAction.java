package br.com.soc.sistema.action.exame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExames;
import br.com.soc.sistema.vo.ExameVo;



public class ExameAction extends Action {
	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness business = new ExameBusiness();
	private ExameFilter filtrar = new ExameFilter();
	private ExameVo exameVo = new ExameVo();
	
	public String todos() {
		exames.addAll(business.trazerTodosOsExames());	

		return SUCCESS;
	}
	
	public String funcionario() {
		exames.addAll(business.trazerTodosOsFuncionarios());
		
		return FUNCIONARIO;
	}
	
	public String realifunc() {
		exames.addAll(business.trazerTodosOsRealiFunc());
		
		return REALIFUNC;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		exames = business.filtrarExames(filtrar);
		
		return SUCCESS;
		
		
	}
	
	
	public String filtrarfu() {
		if(filtrar.isNullOpcoesCombo())
			return FUNCIONARIO;
		
		exames = business.filtrarFuncionario(filtrar);
		
		return FUNCIONARIO;
	}
	
	public String filtrarReali() {
		if(filtrar.isNullOpcoesCombo())
			return REALIFUNC;
		
		exames = business.filtrarReali(filtrar);
		
		return REALIFUNC;
	}
	
	public String novo() {
		if(exameVo.getNome() == null)
			return INPUT;
		
		business.salvarExame(exameVo);
		
		return REDIRECT;
	}
	
	public String novofun() {
		if(exameVo.getNome() == null)
			return INPUTF;
		
		business.salvarFuncionario(exameVo);
		
		return FUNCIONARIO;
	}
	
	public String novoreali() {
		if(exameVo.getNome() == null)
			return INPUTR;
		
		business.salvarReali(exameVo);
		
		return REALIFUNC;
	}
	
	public String editar() {
		return UPDATE;
	}
	
	public String editarfu() {
		return UPDATEF;
	}
	
	public String editarre() {
		return UPDATER;
	}
	
	public String edit() {
		if(exameVo.getRowid() == null)
			return REDIRECT;
		
		business.editarExame(exameVo.getRowid(), exameVo.getNome());
		
		return SUCCESS;
	}
	
	public String editf() {
		if(exameVo.getRowid() == null)
			return REDIRECT;
		
		business.editarFuncionario(exameVo.getRowid(), exameVo.getNome());
		
		return SUCCESS;
	}
	
	public String editr() {
		if(exameVo.getRowid() == null)
			return REDIRECT;
		
		business.editarReali(exameVo.getRowid(), exameVo.getNome(), exameVo.getData());
		
		return SUCCESS;
	}
	
	public String remover() {
		if(exameVo.getRowid() == null)
			return REDIRECT;
		
		business.removerExame(exameVo.getRowid());
		
		return REDIRECT;
	}
	
	public String remoFu() {
		if(exameVo.getRowid() == null)
			return FUNCIONARIO;
		
		business.removerFuncionario(exameVo.getRowid());

		return FUNCIONARIO;
	}
	
	
	public String remoRe() {
		if(exameVo.getRowid() == null)
			return REALIFUNC;
		
		business.removerReali(exameVo.getRowid(), exameVo.getNome());
		
		return REALIFUNC;
	}

	public List<OpcoesComboBuscarExames> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarExames.values());
	}
	
	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}

	public ExameFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}
}
