package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.ModelException;

public interface IItem {
	
	public void updateQuality();

	void addToInventary(BaseInventory baseInventory) throws ModelException;
	
}
