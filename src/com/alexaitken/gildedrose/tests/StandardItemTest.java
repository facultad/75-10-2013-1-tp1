package com.alexaitken.gildedrose.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.Item;
import com.alexaitken.gildedrose.StandardItem;
import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.MaximoSuperadoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoDiasVencimientoException;

public class StandardItemTest {
	
	private StandardItem crearStandarItem(
			String descripcion,int diasParaVencer,int calidad){
		try {
			return new StandardItem(descripcion, diasParaVencer, calidad);
		} catch (EstadoItemInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		return null;
	}
	
	@Test
	public void testStandardItemCreation() {
		try {
			new StandardItem("Test", -3, 10);
			fail("Días para vencimiento debe ser mayor o igual a cero.");
		} catch (ValorNegativoDiasVencimientoException e) {
		} catch (Exception e) {
			fail("Días para vencimiento debe ser mayor o igual a cero.");
		}
		
		try {
			new StandardItem("Test", 3, -1);
			fail("La calidad debe ser mayor igual a cero.");
		} catch (ValorNegativoCalidadException e) {
		} catch (Exception e) {
			fail("La calidad debe ser mayor igual a cero.");
		}

		try {
			new StandardItem("Test", 3, 9999);
			fail("La calidad no debe superar el máximo.");
		} catch (MaximoSuperadoCalidadException e) {
		} catch (Exception e) {
			fail("La calidad debe ser mayor igual a cero.");
		}

	}

	@Test
	public void testUpdateQualityVencido() {
		/*
		 * Una vez que paso la fecha de vencimiento (SellIn <= 0) la calidad
		 * (“Quality”) disminuye al doble de la velocidad normal, es decir
		 * decrementa 2.
		 */
		StandardItem item=this.crearStandarItem("Cualquier producto", 0, 2);
		item.updateQuality();
		assertEquals("Una vez que paso la fecha de vencimiento (SellIn <= 0) la calidad "+
			"(“Quality”) disminuye al doble de la velocidad normal, es decir"+
			"decrementa 2.",0, item.getQuality());
	}
}
