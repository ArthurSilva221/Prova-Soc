package br.com.soc.sistema.vo;

public class ExameVo {
	private String rowid;
	private String nome;
	private String data;
	
	public ExameVo() {}
		
	public ExameVo(String rowid, String nome, String data) {
		this.rowid = rowid;
		this.nome = nome;
		this.data = data;
	}

	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ExameVo [rowid=" + rowid + ", nome=" + nome + ", data="+ data +"]";
	}
}
