package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.ModelException;

public class Inventory extends BaseInventory{

	/**
	 * @deprecated
	 */
	public Inventory(Item[] items) 
			throws EstadoItemInvalidoException, ModelException{
		super();
		for(int i=0;i<items.length;i++){
			IFactoryMethodItem factoryMethod;
			factoryMethod=new FactoryMethodItem(items[i]);
			this.addItem(factoryMethod.crearItem());
		}
	}
	
	public Inventory() {
		super();
	}
	
	public static Inventory createDefaultInventory() 
			throws EstadoItemInvalidoException, ModelException{
		Inventory inventory=new Inventory();
		inventory.addItem(new StandardItem("+5 Dexterity Vest", 10, 20));
		inventory.addItem(new AgedBrie("Aged Brie", 2, 0));
		inventory.addItem(new StandardItem("Elixir of the Mongoose", 5, 7));
		inventory.addItem(new Sulfura("Sulfuras, Hand of Ragnaros"));
		inventory.addItem(new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		inventory.addItem(new Conjured("Conjured Mana Cake", 3, 6));
		return inventory;
	}

}