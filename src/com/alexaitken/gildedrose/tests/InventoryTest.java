package com.alexaitken.gildedrose.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.Inventory;
import com.alexaitken.gildedrose.Item;
import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.MaximoSuperadoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorCalidadInvalidoException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoDiasVencimientoException;

public class InventoryTest {

	private Item item;
	private Item[] items=new Item[1];
	private Inventory inventory;
	
	public Inventory crearInventory(Item[] items){
		try {
			return new Inventory(items);
		} catch (EstadoItemInvalidoException e) {
			e.printStackTrace();
			fail();
		}
		return null;
	}

	@Test
	public void testUpdateQualityVencido() {
		/*
		 * Una vez que paso la fecha de vencimiento (SellIn <= 0) la calidad
		 * (“Quality”) disminuye al doble de la velocidad normal, es decir
		 * decrementa 2.
		 */
		item=new Item("Cualquier producto", 0, 2);
		items[0]=item;
		inventory=this.crearInventory(items);
		inventory.updateQuality();
		assertEquals("Una vez que paso la fecha de vencimiento (SellIn <= 0) la calidad "+
			"(“Quality”) disminuye al doble de la velocidad normal, es decir"+
			"decrementa 2.",0, item.getQuality());
	}

	@Test
	public void testUpdateQualityNoNegativo() {
		/*
		 * La calidad de un producto, nunca es un número negativo.
		 */
		item=new Item("Cualquier producto", 0, 2);
		items[0]=item;
		inventory=this.crearInventory(items);
		inventory.updateQuality();
		assertTrue("La calidad de un producto, nunca es un número negativo.",
				item.getQuality()>=0);
	}

	@Test
	public void testUpdateQualityAgedBrie() {
		/*
		 * El “Aged Brie” incrementa su calidad a medida que envejece. 
		 */
		item=new Item("Aged Brie", 5, 10);
		items[0]=item;
		inventory=this.crearInventory(items);
		inventory.updateQuality();
		assertEquals("El “Aged Brie” incrementa su calidad a medida que envejece.",
				11,item.getQuality());
	}

	
	@Test
	public void testUpdateQualityMaxCalidad() {
		/*
		 * El valor de calidad de un producto, nunca supera 50.
		 */
		item=new Item("Aged Brie", 5, 50);
		items[0]=item;
		inventory=this.crearInventory(items);
		inventory.updateQuality();
		assertEquals("El valor de calidad de un producto, nunca supera 50.",
				50,item.getQuality());
	}
	
	@Test
	public void testUpdateQualitySulfuras() {
		/*
		 * Los “Sulfuras”, siendo un producto legendario, no vencen nunca 
		 * (“SellIn” es 0 siempre), y mantienen constante su calidad.
		 */
		item=new Item("Sulfuras, Hand of Ragnaros",0, 80);
		items[0]=item;
		inventory=this.crearInventory(items);
		inventory.updateQuality();
		assertEquals("Los “Sulfuras”, siendo un producto legendario, no vencen nunca"+ 
				"(“SellIn” es 0 siempre), y mantienen constante su calidad.",
				80,item.getQuality());
		assertEquals("Los “Sulfuras”, siendo un producto legendario, no vencen nunca"+ 
				"(“SellIn” es 0 siempre), y mantienen constante su calidad.",
				0,item.getSellIn());
		
		/*
		 * Los “Sulfuras”, al ser legendarios, tiene una calidad constante de 80.
		 */
		try{
			item=new Item("Sulfuras, Hand of Ragnaros",0, 40);
			items[0]=item;
			inventory=new Inventory(items);
			fail("Los “Sulfuras”, al ser legendarios, tiene una calidad constante de 80.");
		}
		catch(ValorCalidadInvalidoException e){
		}
		catch (Exception e) {
			fail("Los “Sulfuras”, al ser legendarios, tiene una calidad constante de 80.");
		}
	}

	@Test
	public void testUpdateQualityBackstagePasses() {
		/*
		 * "Backstage passes", al igual que los “Aged Brie”, incrementan su 
		 * calidad a medida que la fecha de vencimiento se acerca, pero:
		 */

		/*
		 * - Su calidad aumenta en 1 cuando faltan más de 10 días o menos.
		 */
		item=new Item("Backstage passes to a TAFKAL80ETC concert",20, 10);
		items[0]=item;
		inventory=this.crearInventory(items);
		inventory.updateQuality();
		assertEquals("Backstage passes, su calidad aumenta en 1 cuando faltan"+
				" más de 10 días o menos.",11,item.getQuality());

		/*
		 * - Su calidad aumenta en 2 cuando faltan 10 días o menos.
		 */
		item=new Item("Backstage passes to a TAFKAL80ETC concert",10, 10);
		items[0]=item;
		inventory=this.crearInventory(items);
		inventory.updateQuality();
		assertEquals("Backstage passes, su calidad aumenta en 2 cuando "+
				"faltan 10 días o menos.",12,item.getQuality());

		/*
		 * - Su calidad aumenta en 3 cuando faltan 5 días o menos.
		 */
		item=new Item("Backstage passes to a TAFKAL80ETC concert",5, 10);
		items[0]=item;
		inventory=this.crearInventory(items);
		inventory.updateQuality();
		assertEquals("Backstage passes, su calidad aumenta en 3 cuando "+
				"faltan 5 días o menos.",13,item.getQuality());

		/*
		 * - Su calidad se cae a 0 luego del concierto (“SellIn” <= 0).
		 */
		item=new Item("Backstage passes to a TAFKAL80ETC concert",0, 10);
		items[0]=item;
		inventory=this.crearInventory(items);
		inventory.updateQuality();
		assertEquals("Backstage passes, su calidad se cae a 0 luego del "+
				"concierto (“SellIn” <= 0).",0,item.getQuality());
	}

}
