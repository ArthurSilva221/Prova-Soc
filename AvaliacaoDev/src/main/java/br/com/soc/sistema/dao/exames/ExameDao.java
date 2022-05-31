package br.com.soc.sistema.dao.exames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameVo;

public class ExameDao extends Dao {
	
	public void insertExame(ExameVo exameVo){
		StringBuilder query = new StringBuilder("INSERT INTO exame (nm_exame) values (?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, exameVo.getNome());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertFuncionario(ExameVo exameVo){
		StringBuilder query = new StringBuilder("INSERT INTO funcionario (nm_funcionario) values (?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, exameVo.getNome());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertReali(ExameVo exameVo){
		StringBuilder query = new StringBuilder("INSERT INTO realifunc (id_funcionario, id_exame, dt_realizacao) values (?, ?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			ps.setString(1, exameVo.getRowid());
			ps.setString(2, exameVo.getNome());
			ps.setString(3, exameVo.getData());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ExameVo> findAllRealiFunc(){
		StringBuilder query = new StringBuilder("SELECT R.id_funcionario, R.id_exame, R.dt_realizacao FROM realifunc "
				+ "R INNER JOIN exame E ON R.id_exame = E.rowid "
				+ "INNER JOIN funcionario F ON R.id_funcionario = F.id_funcionario");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			ExameVo vo =  null;
			List<ExameVo> exames = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameVo();
				vo.setRowid(rs.getString("id_funcionario"));
				vo.setNome(rs.getString("id_exame"));
				vo.setData(rs.getString("dt_realizacao"));
				
				exames.add(vo);
			}
			return exames;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	public List<ExameVo> findAllFuncionarios(){
		StringBuilder query = new StringBuilder("SELECT id_funcionario, nm_funcionario FROM funcionario");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			ExameVo vo =  null;
			List<ExameVo> exames = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameVo();
				vo.setRowid(rs.getString("id_funcionario"));
				vo.setNome(rs.getString("nm_funcionario"));	
				
				exames.add(vo);
			}
			return exames;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	public List<ExameVo> findAllExames(){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_exame nome FROM exame");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			ExameVo vo =  null;
			List<ExameVo> exames = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));	
				
				exames.add(vo);
			}
			return exames;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	public List<ExameVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_exame nome FROM exame ")
								.append("WHERE lower(nm_exame) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				List<ExameVo> exames = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
					
					exames.add(vo);
				}
				return exames;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	public List<ExameVo> findAllByNomeFu(String nome){
		StringBuilder query = new StringBuilder("SELECT id_funcionario id, nm_funcionario nome FROM funcionario ")
								.append("WHERE lower(nm_funcionario) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				List<ExameVo> exames = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
					
					exames.add(vo);
				}
				return exames;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	public ExameVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_exame nome FROM exame ")
								.append("WHERE rowid = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				
				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public ExameVo findByCodigoFu(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT id_funcionario id, nm_funcionario nome FROM funcionario ")
								.append("WHERE id_funcionario = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				
				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));	
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public List<ExameVo> findAllByCodigoReali(String nome){
		StringBuilder query = new StringBuilder("SELECT R.id_funcionario, R.id_exame, R.dt_realizacao FROM realifunc R "
				+ "INNER JOIN funcionario F ON R.id_funcionario = F.id_funcionario")
								.append("WHERE lower(R.id_funcionario) like lower(?) ");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				List<ExameVo> exames = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id_funcionario"));
					vo.setNome(rs.getString("id_exame"));
					vo.setData(rs.getString("dt_realizacao"));
					
					exames.add(vo);
				}
				return exames;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	public List<ExameVo> findAllByCodigoRealiExame(String nome){
		StringBuilder query = new StringBuilder("SELECT R.id_funcionario, R.id_exame, R.dt_realizacao FROM realifunc R "
				+ "INNER JOIN exame E ON R.id_exame = E.id_exame")
								.append("WHERE lower(R.id_exame) like lower(?) ");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameVo vo =  null;
				List<ExameVo> exames = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExameVo();
					vo.setRowid(rs.getString("id_funcionario"));
					vo.setNome(rs.getString("id_exame"));
					vo.setData(rs.getString("dt_realizacao"));
					
					exames.add(vo);
				}
				return exames;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	
	public ExameVo alterar(Integer codigo, String codigo_n){
		StringBuilder query = new StringBuilder("UPDATE exame SET nm_exame = ? WHERE rowid = ?");
		
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
				
				ps.setString(1, codigo_n);
				ps.setInt(2, codigo);
				
				ps.executeUpdate();
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}		
			return null;	
	}
	
	public ExameVo alterarFuncionario(Integer codigo, String codigo_n){
		StringBuilder query = new StringBuilder("UPDATE funcionario SET nm_funcionario = ? WHERE id_funcionario = ?");
		
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
				
				ps.setString(1, codigo_n);
				ps.setInt(2, codigo);
				
				ps.executeUpdate();
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}		
			return null;	
	}
	
	public ExameVo alterarReali(Integer codigo, String codigo_n, String codigo_d){
		StringBuilder query = new StringBuilder("UPDATE realifunc SET id_exame = ?, dt_realizacao = ? WHERE id_funcionario = ?");
		
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
				
				ps.setString(1, codigo_n);
				ps.setString(2, codigo_d);
				ps.setInt(3, codigo);
				
				ps.executeUpdate();
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}		
			return null;	
	}
	
	public ExameVo excluiExame(Integer codigo){
		String delete = "DELETE FROM exame WHERE rowid = ?";
		try(Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(delete.toString())){
			
			ps.setInt(1, codigo);
			
			ps.executeUpdate();
			con.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ExameVo excluiFuncionario(Integer codigo){
		String delete = "DELETE FROM funcionario F WHERE F.id_funcionario LIKE '%" + codigo +"%'";
		try(Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(delete.toString())){
			
			ps.executeUpdate();
			con.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ExameVo excluiReali(Integer codigo, Integer codigo_n){
		String delete = "DELETE FROM realifunc WHERE id_funcionario = ? AND id_exame = ?";
		
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(delete.toString())){
				
				ps.setInt(1, codigo);
				ps.setInt(2, codigo_n);
				
				ps.executeUpdate();
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}		
			return null;	
	}
}