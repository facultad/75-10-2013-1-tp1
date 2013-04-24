package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.MaximoSuperadoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoDiasVencimientoException;

public class BackstagePass extends StandardItem {

	public BackstagePass(String descripcion, int diasParaVencer, int calidad) 
			throws EstadoItemInvalidoException {
		super(descripcion, diasParaVencer, calidad);
	}

	/**
	 * @throws MaximoSuperadoCalidadException 
	 * @throws ValorNegativoCalidadException 
	 * @throws ValorNegativoDiasVencimientoException 
	 * @deprecated
	 */
	public BackstagePass(Item item) 
			throws EstadoItemInvalidoException {
		super(item);
	}

	@Override
	public void updateQuality() {
		// TODO Auto-generated method stub
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
