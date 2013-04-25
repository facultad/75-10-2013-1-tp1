package com.alexaitken.gildedrose.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.Item;
import com.alexaitken.gildedrose.Sulfura;
import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.ValorCalidadInvalidoException;

public class SulfuraTest {
	
	private Sulfura crearSulfura(String descripcion){
		try {
			return new Sulfura(descripcion);
		} catch (EstadoItemInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		return null;
	}

	@Test
	public void testUpdateQualitySulfuras() {
		/*
		 * Los “Sulfuras”, siendo un producto legendario, no vencen nunca 
		 * (“SellIn” es 0 siempre), y mantienen constante su calidad.
		 */
		Sulfura item=this.crearSulfura("Sulfuras, Hand of Ragnaros");
		item.updateQuality();
		assertEquals("Los “Sulfuras”, siendo un producto legendario, no vencen nunca"+ 
				"(“SellIn” es 0 siempre), y mantienen constante su calidad.",
				80,item.getQuality());
		assertEquals("Los “Sulfuras”, siendo un producto legendario, no vencen nunca"+ 
				"(“SellIn” es 0 siempre), y mantienen constante su calidad.",
				0,item.getDiasFaltantes());
		
		/*
		 * Los “Sulfuras”, al ser legendarios, tiene una calidad constante de 80.
		 */
		try{
			new Sulfura(new Item("Sulfuras, Hand of Ragnaros",0, 40));
			fail("Los “Sulfuras”, al ser legendarios, tiene una calidad constante de 80.");
		}
		catch(ValorCalidadInvalidoException e){
		}
		catch (Exception e) {
			fail("Los “Sulfuras”, al ser legendarios, tiene una calidad constante de 80.");
		}
	}

}
