package com.alexaitken.gildedrose.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.AgedBrie;
import com.alexaitken.gildedrose.StandardItem;
import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;

public class AgedBrieTest {

	private AgedBrie crearAgedBrie(
			String descripcion,int diasParaVencer,int calidad){
		try {
			return new AgedBrie(descripcion, diasParaVencer, calidad);
		} catch (EstadoItemInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		return null;
	}

	@Test
	public void testUpdateQualityAgedBrie() {
		/*
		 * El “Aged Brie” incrementa su calidad a medida que envejece. 
		 */
		AgedBrie item=this.crearAgedBrie("Aged Brie", 5, 10);
		item.updateQuality();
		assertEquals("El “Aged Brie” incrementa su calidad a medida que envejece.",
				11,item.getQuality());
	}

	@Test
	public void testUpdateQualityMaxCalidad() {
		/*
		 * El valor de calidad de un producto, nunca supera 50.
		 */
		StandardItem item=this.crearAgedBrie("Aged Brie", 5, 50);
		item.updateQuality();
		assertEquals("El valor de calidad de un producto, nunca supera 50.",
				50,item.getQuality());
	}

}
