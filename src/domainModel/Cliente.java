package domainModel;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Codigo")
	private int id;
	
	@Column(name="Nome")
	private String nome;
	/*
	@Temporal(TemporalType.DATE)
	@Column
	private Date dataNascimento;
	
	*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	/*
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	*/
}
