package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.exames.ExameDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.vo.ExameVo;

public class ExameBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameDao dao;
	
	public ExameBusiness() {
		this.dao = new ExameDao();
	}
	
	public List<ExameVo> trazerTodosOsExames(){
		return dao.findAllExames();
	}
	
	public List<ExameVo> trazerTodosOsFuncionarios(){
		return dao.findAllFuncionarios();
	}
	
	public List<ExameVo> trazerTodosOsRealiFunc(){
		return dao.findAllRealiFunc();
	}
	
	public void salvarExame(ExameVo exameVo) {
		try {
			if(exameVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			dao.insertExame(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	public void salvarFuncionario(ExameVo exameVo) {
		try {
			if(exameVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			dao.insertFuncionario(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	public void salvarReali(ExameVo exameVo) {
		try {
			if(exameVo.getRowid().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			dao.insertReali(exameVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}	
	
	public List<ExameVo> filtrarExames(ExameFilter filter){
		List<ExameVo> exames = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exames.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				exames.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}
		
		return exames;
	}
	
	public List<ExameVo> filtrarFuncionario(ExameFilter filter){
		List<ExameVo> exames = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exames.add(dao.findByCodigoFu(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				exames.addAll(dao.findAllByNomeFu(filter.getValorBusca()));
			break;
		}
		
		return exames;
	}
	
	public List<ExameVo> filtrarReali(ExameFilter filter){
		List<ExameVo> exames = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					exames.addAll(dao.findAllByCodigoReali(filter.getValorBusca()));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				try {
					exames.addAll(dao.findAllByCodigoRealiExame(filter.getValorBusca()));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
		}
		
		return exames;
	}
	
	
	public ExameVo buscarExamePor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException("Deu pau");
		}
	}
	
	public ExameVo editarExame(String codigo, String codigo_n) {
		try {
			Integer cod = Integer.parseInt(codigo);
			String cod_n = codigo_n;
			return dao.alterar(cod, cod_n);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a alteração do registro");
		}
	}
	
	public ExameVo editarFuncionario(String codigo, String codigo_n) {
		try {
			Integer cod = Integer.parseInt(codigo);
			String cod_n = codigo_n;
			return dao.alterarFuncionario(cod, cod_n);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a alteração do registro");
		}
	}
	
	public ExameVo editarReali(String codigo, String codigo_n, String codigo_d) {
		try {
			Integer cod = Integer.parseInt(codigo);
			String cod_n = codigo_n;
			String cod_d = codigo_d;
			return dao.alterarReali(cod, cod_n, cod_d);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a alteração do registro");
		}
	}
	
	public ExameVo removerExame(String codigo) {
		try {
			
			Integer cod = Integer.parseInt(codigo);
			return dao.excluiExame(cod);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusão do registro");
		}
	}
	
	public ExameVo removerFuncionario(String codigo) {
		try {
			
			Integer cod = Integer.parseInt(codigo);
			return dao.excluiFuncionario(cod);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusão do registro");
		}
	}
	
	
	public ExameVo removerReali(String codigo, String codigo_n) {
		try {
			Integer cod = Integer.parseInt(codigo);
			Integer cod_n = Integer.parseInt(codigo_n);
			return dao.excluiReali(cod, cod_n);

		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a alteração do registro");
		}
	}
}
