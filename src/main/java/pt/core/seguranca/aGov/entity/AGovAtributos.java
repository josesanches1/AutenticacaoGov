package pt.core.seguranca.aGov.entity;

public class AGovAtributos {

	// a classe deverá ser "acertada" conforme as necessidades e configurações
	//NOTA: -> deve estar em sincronização coma class enum ["Scopes"]
	
	private String IdentificacaoCivil = null;	// 0 - Scopes -> http://interop.gov.pt/MDC/Cidadao/NIC
	private String nomeProprio = null;			// 1 - Scopes -> http://interop.gov.pt/MDC/Cidadao/NomeProprio
	private String nomeApelido = null;			// 2 - Scopes -> http://interop.gov.pt/MDC/Cidadao/NomeApelido
	private String dataNascimento = null;		// 3 - Scopes -> http://interop.gov.pt/MDC/Cidadao/DataNascimento
	private String nomeCompleto = null;			// 4 - Scopes -> http://interop.gov.pt/MDC/Cidadao/NomeCompleto
	private int NIF = -1;						// 5 - Scopes -> http://interop.gov.pt/MDC/Cidadao/NIF
	private String NISS = null;					// 6 - Scopes -> http://interop.gov.pt/MDC/Cidadao/NISS
	private String NSNS = null;					// 7 - Scopes -> http://interop.gov.pt/MDC/Cidadao/NSNS
	private String nacionalidade = null;		// 8 - Scopes -> http://interop.gov.pt/MDC/Cidadao/Nacionalidade
	private int idade = -1;						// 9 - Scopes -> http://interop.gov.pt/MDC/Cidadao/Idade
	private String foto = null;					//10 - Scopes -> http://interop.gov.pt/MDC/Cidadao/Foto
	private String dataValidade = null;			//11 - Scopes -> http://interop.gov.pt/MDC/Cidadao/DataValidade
	private String dataEmissao = null;			//12 - Scopes -> http://interop.gov.pt/MDC/Cidadao/DataEmissao
	private String codigoPostal4 = null;		//13 - Scopes -> http://interop.gov.pt/MDC/Cidadao/CodigoPostal4
	private String codigoPostal3 = null;		//14 - Scopes -> http://interop.gov.pt/MDC/Cidadao/CodigoPostal3
	private String localidadePostal = null;		//15 - Scopes -> http://interop.gov.pt/MDC/Cidadao/LocalidadePostal
	public String getIdentificacaoCivil() {
		return IdentificacaoCivil;
	}
	public void setIdentificacaoCivil(String identificacaoCivil) {
		IdentificacaoCivil = identificacaoCivil;
	}
	public String getNomeProprio() {
		return nomeProprio;
	}
	public void setNomeProprio(String nomeProprio) {
		this.nomeProprio = nomeProprio;
	}
	public String getNomeApelido() {
		return nomeApelido;
	}
	public void setNomeApelido(String nomeApelido) {
		this.nomeApelido = nomeApelido;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public int getNIF() {
		return NIF;
	}
	public void setNIF(int nIF) {
		NIF = nIF;
	}
	public String getNISS() {
		return NISS;
	}
	public void setNISS(String nISS) {
		NISS = nISS;
	}
	public String getNSNS() {
		return NSNS;
	}
	public void setNSNS(String nSNS) {
		NSNS = nSNS;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getCodigoPostal4() {
		return codigoPostal4;
	}
	public void setCodigoPostal4(String codigoPostal4) {
		this.codigoPostal4 = codigoPostal4;
	}
	public String getCodigoPostal3() {
		return codigoPostal3;
	}
	public void setCodigoPostal3(String codigoPostal3) {
		this.codigoPostal3 = codigoPostal3;
	}
	public String getLocalidadePostal() {
		return localidadePostal;
	}
	public void setLocalidadePostal(String localidadePostal) {
		this.localidadePostal = localidadePostal;
	}
}