package br.com.design.pattern.capitulo02;

public class HttpServlerRequest {
	
	private boolean isTrue;
	
	public HttpServlerRequest(boolean isTrue) {
		this.isTrue = isTrue;
	}

	public boolean isTrue() {
		return this.isTrue;
	}
	
}
