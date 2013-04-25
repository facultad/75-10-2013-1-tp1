package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;

public class BackstagePass extends StandardItem {

	public BackstagePass(String descripcion, int diasParaVencer, int calidad) 
			throws EstadoItemInvalidoException {
		super(descripcion, diasParaVencer, calidad);
	}

	/**
	 * @deprecated
	 */
	public BackstagePass(Item item) 
			throws EstadoItemInvalidoException {
		super(item);
	}

	@Override
	public void updateQuality() {
		if (this.getDiasFaltantes()>10)
			this.incrementarCalidad(1);
		else if (this.getDiasFaltantes()>5)
			this.incrementarCalidad(2);
		else if (this.getDiasFaltantes()>0)
			this.incrementarCalidad(3);
		else
			this.anularCalidad();
		this.decrementarDiasFaltantes(1);
	}

}
