package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;

public class AgedBrie extends StandardItem {

	public AgedBrie(String descripcion, int diasParaVencer, int calidad) 
			throws EstadoItemInvalidoException {
		super(descripcion, diasParaVencer, calidad);
	}

	/**
	 * @deprecated
	 */
	public AgedBrie(Item item) 
			throws EstadoItemInvalidoException {
		super(item);
	}
	
	public void updateQuality(){
		super.incrementarCalidad(1);
		super.decrementarDiasFaltantes(1);
	}
	

}
