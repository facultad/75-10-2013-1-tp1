package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.MaximoSuperadoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorCalidadInvalidoException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoDiasVencimientoException;
import com.alexaitken.gildedrose.exceptions.ValorVencimientoNoNuloException;

public class Sulfura extends StandardItem {

	public Sulfura(String descripcion) 
			throws EstadoItemInvalidoException {
		super(descripcion, 0,80);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws MaximoSuperadoCalidadException 
	 * @throws ValorNegativoCalidadException 
	 * @throws ValorNegativoDiasVencimientoException 
	 * @deprecated
	 */
	public Sulfura(Item item) 
			throws EstadoItemInvalidoException {
		super(item);
	}

	@Override
	protected void testEstado() throws EstadoItemInvalidoException {
		if (this.getDiasFaltantes()!=0)
			throw new ValorVencimientoNoNuloException();
		if (this.getQuality()!=this.getMaxCalidad())
			throw new ValorCalidadInvalidoException();
	}

	@Override
	public void updateQuality() {
		// No actualiza nada, sus propiedades se conservan.
	}
	
	public int getMaxCalidad(){
		return 80;
	}

}
