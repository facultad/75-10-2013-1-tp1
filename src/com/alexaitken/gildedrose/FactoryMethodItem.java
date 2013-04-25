package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;

/**
 * @deprecated
 */
public class FactoryMethodItem implements IFactoryMethodItem {

	private Item item;
	
	public FactoryMethodItem(Item item){
		this.item=item;
	}
	
	@Override
	public IItem crearItem() throws EstadoItemInvalidoException {
		if (item.getName() == "Aged Brie")
			return new AgedBrie(item);
		if (item.getName() == "Backstage passes to a TAFKAL80ETC concert")
			return new BackstagePass(item);
		if (item.getName() == "Sulfuras, Hand of Ragnaros")
			return new Sulfura(item);
		return new StandardItem(item);
	}

}
