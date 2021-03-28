package pt.core.seguranca.aGov.entity;

public class AGovResposta {

	private String token = null;
	private String authenticationContextId = null;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAuthenticationContextId() {
		return authenticationContextId;
	}
	public void setAuthenticationContextId(String authenticationContextId) {
		this.authenticationContextId = authenticationContextId;
	}

	
}
