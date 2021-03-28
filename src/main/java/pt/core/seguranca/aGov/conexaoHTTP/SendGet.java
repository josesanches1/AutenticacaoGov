package pt.core.seguranca.aGov.conexaoHTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import pt.core.log.LogAutenticacaoGov;
import pt.core.seguranca.aGov.entity.AGovAtributos;

public class SendGet {

	
	/**
	 * abre um conexão via GET
	 * 
	 * @param endpoint deverá conter os parametros
	 * @return
	 */
	public AGovAtributos get(String endpoint) {
//System.out.println("[GET] com: " + endpoint);
		final String USER_AGENT = "Mozilla/5.0";
		
		try{
			URL url = new URL(endpoint);
			
			HttpURLConnection conHTTP = (HttpURLConnection) url.openConnection();
			conHTTP.setRequestMethod("GET");
			conHTTP.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = conHTTP.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(conHTTP.getInputStream(), "UTF-8"));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				
				in.close();
//System.out.println(response);
				return parseJSON(response.toString());
			}
			//codigo HTTP diferente de 200
			BufferedReader in = new BufferedReader(new InputStreamReader(conHTTP.getErrorStream(), "UTF-8"));
	        StringBuffer result = new StringBuffer();
	        String line = "";
	        while ((line = in.readLine()) != null) {
	            result.append(line);
	        }
	        
	        in.close();
	        LogAutenticacaoGov.info("[mensagem de erro] " + result.toString());
		}catch (MalformedURLException e){
			LogAutenticacaoGov.info("[Conexao POST] Erro: " + e);
		}catch (IOException e){
			LogAutenticacaoGov.info("[Conexao POST] Erro: " + e);
		}
		// deu erro, http != 200 ou exception
		
		return null;
	}
	
	/**
	 * Devolve a entidade com o parse do JSON
	 * 
	 * @param json
	 * @return
	 */
	private AGovAtributos parseJSON(String json) {
		try {
			AGovAtributos aGovResp = new AGovAtributos();
			
			final JSONArray array = new JSONArray(json);
			for (int i=0; i<array.length(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				switch(obj.get("name").toString().toLowerCase()) {
					case "http://interop.gov.pt/mdc/cidadao/nic":
						aGovResp.setIdentificacaoCivil(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/nomeproprio":
						aGovResp.setNomeProprio(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/nomeapelido":
						aGovResp.setNomeApelido(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/datanascimento":
						aGovResp.setDataNascimento(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/nomecompleto":
						aGovResp.setNomeCompleto(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/nif":
						aGovResp.setNIF(obj.getInt("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/niss":
						aGovResp.setNISS(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/nsns":
						aGovResp.setNSNS(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/nacionalidade":
						aGovResp.setNacionalidade(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/idade":
						aGovResp.setIdade(obj.getInt("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/foto":
						aGovResp.setFoto(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/datavalidade":
						aGovResp.setDataValidade(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/dataemissao":
						aGovResp.setDataEmissao(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/codigopostal4":
						aGovResp.setCodigoPostal4(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/codigopostal3":
						aGovResp.setCodigoPostal3(obj.getString("value"));
						break;
					case "http://interop.gov.pt/mdc/cidadao/localidadepostal":
						aGovResp.setLocalidadePostal(obj.getString("value"));
						break;
				}
			}
			return aGovResp;
		}catch(Exception e) {
			LogAutenticacaoGov.info("PARSE JSON ERRO: " + e);
		}
		return null;
	}
}
