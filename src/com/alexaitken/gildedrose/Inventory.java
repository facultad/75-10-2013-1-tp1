package com.alexaitken.gildedrose;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.MaximoSuperadoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoDiasVencimientoException;

import sun.text.normalizer.UProperty;

public class Inventory {

	private Collection<IItem> items=new ArrayList<IItem>();

	/**
	 * @throws MaximoSuperadoCalidadException 
	 * @throws ValorNegativoCalidadException 
	 * @throws ValorNegativoDiasVencimientoException 
	 * @deprecated
	 */
	public Inventory(Item[] items) 
			throws EstadoItemInvalidoException {
		super();
		for(int i=0;i<items.length;i++){
			IFactoryMethodItem factoryMethod;
			factoryMethod=new FactoryMethodItem(items[i]);
			this.add(factoryMethod.crearItem());
		}
	}
	
	public static Inventory createDefaultInventory() 
			throws EstadoItemInvalidoException{
		Inventory inventory=new Inventory();
		inventory.add(new StandardItem("+5 Dexterity Vest", 10, 20));
		inventory.add(new AgedBrie("Aged Brie", 2, 0));
		inventory.add(new StandardItem("Elixir of the Mongoose", 5, 7));
		inventory.add(new Sulfura("Sulfuras, Hand of Ragnaros"));
		inventory.add(new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		inventory.add(new Conjured("Conjured Mana Cake", 3, 6));
		return inventory;
	}

	public Inventory() {
		super();
	}
	
	public void add(IItem item){
		this.items.add(item);
	}

	public void updateQuality(){
		Iterator<IItem> i=this.items.iterator();

		while (i.hasNext()) {
			IItem item = (IItem) i.next();
			item.updateQuality();
		}
	}
	
}