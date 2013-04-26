package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.InventoryNullException;
import com.alexaitken.gildedrose.exceptions.InventoryYaAsignadoItemException;
import com.alexaitken.gildedrose.exceptions.MaximoSuperadoCalidadException;
import com.alexaitken.gildedrose.exceptions.ModelException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoDiasVencimientoException;

public class StandardItem implements IItem {

	private Item item=null;
	private BaseInventory inventory=null;

	public StandardItem(String descripcion,int diasParaVencer,int calidad) 
			throws EstadoItemInvalidoException{
		this.item=new Item(descripcion, diasParaVencer, calidad);
		this.testEstado();
	}
	
	public int getQuality() {
		return this.item.getQuality();
	}

	/**
	 * @deprecated
	 */
	public StandardItem(Item item)
		throws EstadoItemInvalidoException{
		this.item=item;
		this.testEstado();
	}

	@Override
	public void updateQuality() {
		if (this.getDiasFaltantes()<=0)
			this.decrementarCalidad(2);
		else{
			this.decrementarCalidad(1);
			this.decrementarDiasFaltantes(1);
		}
	}

	public int getDiasFaltantes() {
		return this.item.getSellIn();
	}

	public int getMaxCalidad(){
		return 50;
	}

	protected void testEstado()
		throws 
			EstadoItemInvalidoException{

		if (this.getDiasFaltantes()<0)
			throw new ValorNegativoDiasVencimientoException();
		if (this.getQuality()<0)
			throw new ValorNegativoCalidadException();
		if (this.getQuality()>this.getMaxCalidad())
			throw new MaximoSuperadoCalidadException();
	}

	protected void decrementarCalidad(int cantidad) {
		if (this.item.getQuality()==0)
			return;
		
		if ((this.item.getQuality()-cantidad)<=0)
			this.item.setQuality(0);
		else
			this.item.setQuality(this.item.getQuality()-cantidad);
	}

	protected void incrementarCalidad(int cantidad) {
		if (this.item.getQuality()==this.getMaxCalidad())
			return;
		this.item.setQuality(this.item.getQuality()+cantidad);
		if (this.item.getQuality()>this.getMaxCalidad())
			this.item.setQuality(this.getMaxCalidad());
	}

	protected void anularCalidad() {
		this.item.setQuality(0);
	}

	protected void decrementarDiasFaltantes(int cantidad) {
		if (this.getDiasFaltantes()==0)
			return;
		if ((this.getDiasFaltantes()-cantidad)<=0)
			this.item.setSellIn(0);
		else
			this.item.setSellIn(this.item.getSellIn()-cantidad);
	}

	@Override
	public void addToInventary(BaseInventory baseInventory) throws ModelException{
		if (baseInventory==null)
			throw new InventoryNullException();
		baseInventory.testAddItem(this);
		this.testAddToInventory(baseInventory);
		baseInventory.doAddItem(this);
		this.doAddToInventory(baseInventory);
	}

	private void doAddToInventory(BaseInventory baseInventory) {
		this.inventory=baseInventory;
	}

	private void testAddToInventory(BaseInventory baseInventory) throws InventoryYaAsignadoItemException{
		if (this.inventory!=null)
			throw new InventoryYaAsignadoItemException();
	}

}
