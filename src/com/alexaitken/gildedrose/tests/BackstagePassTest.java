package com.alexaitken.gildedrose.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.BackstagePass;
import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;

public class BackstagePassTest {

	private BackstagePass crearBackstagePass(
			String descripcion,int diasParaVencer,int calidad){
		try {
			return new BackstagePass(descripcion, diasParaVencer, calidad);
		} catch (EstadoItemInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		return null;
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
		BackstagePass item=this.crearBackstagePass("Backstage passes to a TAFKAL80ETC concert",20, 10);
		item.updateQuality();
		assertEquals("Backstage passes, su calidad aumenta en 1 cuando faltan"+
				" más de 10 días o menos.",11,item.getQuality());

		/*
		 * - Su calidad aumenta en 2 cuando faltan 10 días o menos.
		 */
		item=this.crearBackstagePass("Backstage passes to a TAFKAL80ETC concert",10, 10);
		item.updateQuality();
		assertEquals("Backstage passes, su calidad aumenta en 2 cuando "+
				"faltan 10 días o menos.",12,item.getQuality());

		/*
		 * - Su calidad aumenta en 3 cuando faltan 5 días o menos.
		 */
		item=this.crearBackstagePass("Backstage passes to a TAFKAL80ETC concert",5, 10);
		item.updateQuality();
		assertEquals("Backstage passes, su calidad aumenta en 3 cuando "+
				"faltan 5 días o menos.",13,item.getQuality());

		/*
		 * - Su calidad se cae a 0 luego del concierto (“SellIn” <= 0).
		 */
		item=this.crearBackstagePass("Backstage passes to a TAFKAL80ETC concert",0, 10);
		item.updateQuality();
		assertEquals("Backstage passes, su calidad se cae a 0 luego del "+
				"concierto (“SellIn” <= 0).",0,item.getQuality());
	}

}
