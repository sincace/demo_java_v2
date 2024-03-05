package com.dara.demo.codigobarra;

public class CodigoBarra {
	
	private String nombreimagen;
    private String codigobarra;

    public CodigoBarra() {
    	
    
	}

    public CodigoBarra(String nombreimagen, String codigobarra) {
        this.setNombreimagen(nombreimagen);
        this.setCodigobarra(codigobarra);
    }

	public String getNombreimagen() {
		return nombreimagen;
	}

	public void setNombreimagen(String nombreimagen) {
		this.nombreimagen = nombreimagen;
	}

	public String getCodigobarra() {
		return codigobarra;
	}

	public void setCodigobarra(String codigobarra) {
		this.codigobarra = codigobarra;
	}

}
