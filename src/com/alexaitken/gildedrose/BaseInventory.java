package com.alexaitken.gildedrose;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.alexaitken.gildedrose.exceptions.ItemExisteEnInventaryException;
import com.alexaitken.gildedrose.exceptions.ItemNullException;
import com.alexaitken.gildedrose.exceptions.ModelException;

public class BaseInventory{

	private Collection<IItem> items=new ArrayList<IItem>();

	public void updateQuality(){
		Iterator<IItem> i=this.items.iterator();

		while (i.hasNext()) {
			IItem item = (IItem) i.next();
			item.updateQuality();
		}
	}

	public void addItem(IItem item) throws ModelException{
		if (item==null)
			throw new ItemNullException();
		item.addToInventary(this);
	}

	void doAddItem(IItem item) {
		this.items.add(item);
	}

	void testAddItem(IItem itemToAdd) throws ModelException{
		Iterator<IItem> i=this.items.iterator();

		while (i.hasNext()) {
			IItem item = (IItem) i.next();
			if (item==itemToAdd)
				throw new ItemExisteEnInventaryException();
		}
	}

}
