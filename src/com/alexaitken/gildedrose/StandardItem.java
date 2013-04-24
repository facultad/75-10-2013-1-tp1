package com.alexaitken.gildedrose;

import com.alexaitken.gildedrose.exceptions.EstadoItemInvalidoException;
import com.alexaitken.gildedrose.exceptions.MaximoSuperadoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoCalidadException;
import com.alexaitken.gildedrose.exceptions.ValorNegativoDiasVencimientoException;

public class StandardItem implements IItem {

	private Item item;

	public StandardItem(String descripcion,int diasParaVencer,int calidad) 
			throws EstadoItemInvalidoException{
		this.item=new Item(descripcion, diasParaVencer, calidad);
		this.testEstado();
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

	public int getQuality() {
		// TODO Auto-generated method stub
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
		if (this.item.getSellIn()<=0)
			this.item.setQuality(this.item.getQuality()-2);
		else{
			this.item.setQuality(this.item.getQuality()-1);
			this.item.setSellIn(this.item.getSellIn()-1);
		}
	}

	public void incrementarCalidad(int cantidad) {
		if (this.item.getQuality()==this.getMaxCalidad())
			return;
		this.item.setQuality(this.item.getQuality()+cantidad);
		if (this.item.getQuality()>this.getMaxCalidad())
			this.item.setQuality(this.getMaxCalidad());
	}

	public void decrementarDiasFaltantes(int cantidad) {
		this.item.setSellIn(this.item.getSellIn()-cantidad);
	}

	public int getDiasFaltantes() {
		return this.item.getSellIn();
	}

	public void anularCalidad() {
		this.item.setQuality(0);
	}

	public int getMaxCalidad(){
		return 50;
	}
}
