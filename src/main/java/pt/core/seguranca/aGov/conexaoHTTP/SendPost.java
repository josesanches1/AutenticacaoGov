package pt.core.seguranca.aGov.conexaoHTTP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import pt.core.log.LogAutenticacaoGov;
import pt.core.seguranca.aGov.entity.AGovResposta;

public class SendPost {

	
	/**Efetua uma ligação HTTP via POST
	 * 
	 * @param endpoint
	 * @param key
	 * @param json
	 * @return
	 */
	public AGovResposta post(String endpoint, String json) {
//System.out.println(endpoint);
		try{
			URL url = new URL(endpoint);
			HttpURLConnection conHTTP = (HttpURLConnection) url.openConnection();
			conHTTP.setRequestProperty("content-type", "application/json");
			conHTTP.setRequestProperty("accept", "application/json");
			conHTTP.setRequestMethod("POST");
			conHTTP.setDoOutput(true);

			//UTF-8
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conHTTP.getOutputStream(), "UTF-8"));
			bw.write(json);
			bw.flush();
			bw.close();
			
			int responseCode = conHTTP.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(conHTTP.getInputStream(), "UTF-8"));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println("Resposta: ");
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
	        LogAutenticacaoGov.info("[mensagem de erro] " + result.toString() + ", HTTP code: " + responseCode);
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
	private AGovResposta parseJSON(String json) {
		try {
			final JSONObject resp = new JSONObject(json);
			AGovResposta aGovResp = new AGovResposta();
			aGovResp.setToken(resp.getString("token"));
			aGovResp.setAuthenticationContextId(resp.getString("authenticationContextId"));
			return aGovResp;
		}catch(Exception e) {
			LogAutenticacaoGov.info("PARSE JSON ERRO: " + e);
		}
		return null;
	}
}
