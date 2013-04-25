package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;

public interface IFactoryMethodItem {
	public IItem crearItem() throws EstadoItemInvalidoException;
}
