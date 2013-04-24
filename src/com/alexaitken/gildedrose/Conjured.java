package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.MaximoSuperadoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoDiasVencimientoException;

public class Conjured extends StandardItem {

	public Conjured(String descripcion, int diasParaVencer, int calidad) 
			throws EstadoItemInvalidoException {
		super(descripcion, diasParaVencer, calidad);
	}

}
