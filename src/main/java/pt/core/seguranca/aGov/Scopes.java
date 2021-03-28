package pt.core.seguranca.aGov;

public enum Scopes {
	
	IdentificacaoCivil(0),
	NomeProprio(1),
	Apelido(2),
	DataNascimento(3),
	NomeCompleto(4),
	IdentificacaoFiscal(5),
	IdentificacaoSegSocial(6),
	IdentificacaoServicoNacionalSaude(7),
	Nacionalidade(8),
	Idade(9),
	Foto(10),
	DataValidade(11),
	DataEmissao(12),
	CodigoPostal4(13),
	CodigoPostal3(14),
	LocalidadePostal(15);
	
	public final int index;
	
	Scopes(int i) { index = i; }
	
    public int getIndex(){return index;}
	
	
	
	private final String[] identificado= {
			"http://interop.gov.pt/MDC/Cidadao/NIC",
			"http://interop.gov.pt/MDC/Cidadao/NomeProprio",
			"http://interop.gov.pt/MDC/Cidadao/NomeApelido",
			"http://interop.gov.pt/MDC/Cidadao/DataNascimento",
			"http://interop.gov.pt/MDC/Cidadao/NomeCompleto",
			"http://interop.gov.pt/MDC/Cidadao/NIF",
			"http://interop.gov.pt/MDC/Cidadao/NISS",
			"http://interop.gov.pt/MDC/Cidadao/NSNS",
			"http://interop.gov.pt/MDC/Cidadao/Nacionalidade",
			"http://interop.gov.pt/MDC/Cidadao/Idade",
			"http://interop.gov.pt/MDC/Cidadao/Foto",
			"http://interop.gov.pt/MDC/Cidadao/DataValidade",
			"http://interop.gov.pt/MDC/Cidadao/DataEmissao",
			"http://interop.gov.pt/MDC/Cidadao/CodigoPostal4",
			"http://interop.gov.pt/MDC/Cidadao/CodigoPostal3",
			"http://interop.gov.pt/MDC/Cidadao/LocalidadePostal"
	};
	
	
	@Override
	public String toString() {
		 return identificado[index];
	}
}
