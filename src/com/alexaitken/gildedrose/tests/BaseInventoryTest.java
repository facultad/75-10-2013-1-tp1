package com.alexaitken.gildedrose.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alexaitken.gildedrose.BaseInventory;
import com.alexaitken.gildedrose.StandardItem;
import com.alexaitken.gildedrose.exceptions.InventoryYaAsignadoItemException;
import com.alexaitken.gildedrose.exceptions.ItemExisteEnInventaryException;

public class BaseInventoryTest {

	@Test
	public void testUpdateQuality() {
		StandardItem item=this.crearUnItem();
		BaseInventory inventory=new BaseInventory();
		inventory.addItem(item);
		inventory.updateQuality();
		assertEquals("Actualización correcta de la calidad de un item", 9,
				item.getQuality());
	}

	@Test
	public void testAddItemYaExisteEnInventory() {
		StandardItem item=this.crearUnItem();
		BaseInventory inventory=new BaseInventory();
		inventory.addItem(item);
		try {
			inventory.addItem(item);
			fail("Item ya existe en inventory.");
		} catch (ItemExisteEnInventaryException e) {
		}
	}

	@Test
	public void testAddItemYaExisteEnOtroInventory() {
		StandardItem item=this.crearUnItem();
		BaseInventory inventory1=new BaseInventory();
		BaseInventory inventory2=new BaseInventory();
		inventory1.addItem(item);
		try {
			inventory2.addItem(item);
			fail("Item ya existe en otro inventory.");
		} catch (InventoryYaAsignadoItemException e) {
		}
	}

	private StandardItem crearUnItem() {
		return new StandardItem("Un item",10,10);
	}
	
	

}
