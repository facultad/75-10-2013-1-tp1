package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.MaximoSuperadoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoDiasVencimientoException;

public class AgedBrie extends StandardItem {

	public AgedBrie(String descripcion, int diasParaVencer, int calidad) 
			throws EstadoItemInvalidoException {
		super(descripcion, diasParaVencer, calidad);
	}

	/**
	 * @throws MaximoSuperadoCalidadException 
	 * @throws ValorNegativoCalidadException 
	 * @throws ValorNegativoDiasVencimientoException 
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
