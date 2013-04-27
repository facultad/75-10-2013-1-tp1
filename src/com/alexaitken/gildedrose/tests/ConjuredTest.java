package com.alexaitken.gildedrose.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.Conjured;
import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;

public class ConjuredTest {

	private Conjured crearConjured(
			String descripcion,int diasParaVencer,int calidad){
		try {
			return new Conjured(descripcion, diasParaVencer, calidad);
		} catch (EstadoItemInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		return null;
	}

	@Test
	public void testUpdateQuality() {
		/*
		 * Los “Conjured” degradan su calidad 2 veces más rápido que los 
		 * productos normales. (decrementa en 2).
		 */
		Conjured item=this.crearConjured("Test", 3, 10);
		item.updateQuality();
		assertEquals("Los “Conjured” degradan su calidad 2 veces más rápido "+
				"que los productos normales. (decrementa en 2).",
				8,item.getQuality());

	}

}
