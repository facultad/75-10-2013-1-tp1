package com.alexaitken.gildedrose.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.AgedBrie;
import com.alexaitken.gildedrose.BackstagePass;
import com.alexaitken.gildedrose.FactoryMethodItem;
import com.alexaitken.gildedrose.IFactoryMethodItem;
import com.alexaitken.gildedrose.Item;
import com.alexaitken.gildedrose.StandardItem;
import com.alexaitken.gildedrose.Sulfura;
import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;

public class FactoryMethodItemTest {

	@Test
	public void testAgedBrie() {
		Item item=new Item("Aged Brie", 10, 10);
		IFactoryMethodItem factoryMethod=new FactoryMethodItem(item);
		try {
			assertTrue(factoryMethod.crearItem() instanceof AgedBrie);
		} catch (EstadoItemInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testBackstagePass() {
		Item item=new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);
		IFactoryMethodItem factoryMethod=new FactoryMethodItem(item);
		try {
			assertTrue(factoryMethod.crearItem() instanceof BackstagePass);
		} catch (EstadoItemInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSulfura() {
		Item item=new Item("Sulfuras, Hand of Ragnaros", 0, 80);
		IFactoryMethodItem factoryMethod=new FactoryMethodItem(item);
		try {
			assertTrue(factoryMethod.crearItem() instanceof Sulfura);
		} catch (EstadoItemInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testOther() {
		Item item=new Item("Cualquier otro tipo", 10, 10);
		IFactoryMethodItem factoryMethod=new FactoryMethodItem(item);
		try {
			assertTrue(factoryMethod.crearItem() instanceof StandardItem);
		} catch (EstadoItemInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

}
