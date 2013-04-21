package com.alexaitken.gildedrose.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.Inventory;
import com.alexaitken.gildedrose.Item;

public class InventoryTest {

	@Test
	public void testUpdateQuality() {
		
		Item item;
		Item[] items;
		Inventory inventory;

		/*
		 * Una vez que paso la fecha de vencimiento (SellIn <= 0) la calidad
		 * (“Quality”) disminuye al doble de la velocidad normal, es decir
		 * decrementa 2.
		 */
		item=new Item("Cualquier producto", 0, 2);
		items=new Item[1];
		items[0]=item;
		inventory=new Inventory(items);
		inventory.updateQuality();
		assertEquals("Una vez que paso la fecha de vencimiento (SellIn <= 0) la calidad "+
			"(“Quality”) disminuye al doble de la velocidad normal, es decir"+
			"decrementa 2.",0, item.getQuality());
		
		/*
		 * La calidad de un producto, nunca es un número negativo.
		 */
		item=new Item("Cualquier producto", 0, 2);
		items[0]=item;
		inventory=new Inventory(items);
		inventory.updateQuality();
		assertTrue("La calidad de un producto, nunca es un número negativo.",
				item.getQuality()>=0);
		
		/*
		 * El “Aged Brie” incrementa su calidad a medida que envejece. 
		 */
		item=new Item("Aged Brie", 5, 10);
		items[0]=item;
		inventory=new Inventory(items);
		inventory.updateQuality();
		assertEquals("El “Aged Brie” incrementa su calidad a medida que envejece.",
				11,item.getQuality());
		
		/*
		 * El valor de calidad de un producto, nunca supera 50.
		 */
		item=new Item("Aged Brie", 5, 50);
		items[0]=item;
		inventory=new Inventory(items);
		inventory.updateQuality();
		assertEquals("El valor de calidad de un producto, nunca supera 50.",
				50,item.getQuality());
		
		/*
		 * Los “Sulfuras”, siendo un producto legendario, no vencen nunca 
		 * (“SellIn” es 0 siempre), y mantienen constante su calidad.
		 */
		item=new Item("Sulfuras, Hand of Ragnaros",0, 35);
		items[0]=item;
		inventory=new Inventory(items);
		inventory.updateQuality();
		assertEquals("Los “Sulfuras”, siendo un producto legendario, no vencen nunca"+ 
				"(“SellIn” es 0 siempre), y mantienen constante su calidad.",
				35,item.getQuality());
		assertEquals("Los “Sulfuras”, siendo un producto legendario, no vencen nunca"+ 
				"(“SellIn” es 0 siempre), y mantienen constante su calidad.",
				0,item.getSellIn());
		
		/*
		 * "Backstage passes", al igual que los “Aged Brie”, incrementan su 
		 * calidad a medida que la fecha de vencimiento se acerca, pero:
		 */

		/*
		 * - Su calidad aumenta en 1 cuando faltan más de 10 días o menos.
		 */
		item=new Item("Backstage passes to a TAFKAL80ETC concert",20, 10);
		items[0]=item;
		inventory=new Inventory(items);
		inventory.updateQuality();
		assertEquals("Backstage passes, ",
				11,item.getQuality());

		/*
		 * - Su calidad aumenta en 2 cuando faltan 10 días o menos.
		 */
		item=new Item("Backstage passes to a TAFKAL80ETC concert",10, 10);
		items[0]=item;
		inventory=new Inventory(items);
		inventory.updateQuality();
		assertEquals("Backstage passes, ",
				12,item.getQuality());

		/*
		 * - Su calidad aumenta en 3 cuando faltan 5 días o menos.
		 */
		item=new Item("Backstage passes to a TAFKAL80ETC concert",5, 10);
		items[0]=item;
		inventory=new Inventory(items);
		inventory.updateQuality();
		assertEquals("Backstage passes, ",
				13,item.getQuality());

		/*
		 * - Su calidad se cae a 0 luego del concierto (“SellIn” <= 0).
		 */
		item=new Item("Backstage passes to a TAFKAL80ETC concert",0, 10);
		items[0]=item;
		inventory=new Inventory(items);
		inventory.updateQuality();
		assertEquals("Backstage passes, ",
				0,item.getQuality());

		/*
		 * Los “Sulfuras”, al ser legendarios, tiene una calidad constante de 80.
		 */
 
	}

}
