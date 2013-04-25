package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;

public class Conjured extends StandardItem {

	public Conjured(String descripcion, int diasParaVencer, int calidad) 
			throws EstadoItemInvalidoException {
		super(descripcion, diasParaVencer, calidad);
	}

	@Override
	public void updateQuality() {
		this.decrementarCalidad(2);
		this.decrementarDiasFaltantes(1);
	}

}
