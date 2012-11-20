/**
 * Models a bike shop with basic buy and sell functions
 * 
 * @author Markus Iser
 * @version 1.0
 */
class BikeShop {
	Bike[] stock;
	int nofBikes; 
		
	public BikeShop() {
		stock = new Bike[10];
		for (int i = 0; i < 10; i++) {
			stock[i] = null;
		}
		nofBikes = 0;
	}
	
	/**
	 * adds a new bike to the stock
	 */
	public void buy(Bike bike) {
		stock[nofBikes] = bike;
		nofBikes++;
		if (nofBikes == stock.length) {
			Bike[] newStock = new Bike[stock.length + 5];
			for (int i = 0; i < stock.length; i++) {
				newStock[i] = stock[i];
			}
			this.stock = newStock;
		}
	}
	
	/**
	 * removes the bike at position k from the stock
	 */
	public void sell(int k) {
		if (k < nofBikes) {
			nofBikes--;
			stock[k] = stock[nofBikes];
		}
		if (nofBikes < stock.length - 5) {
			Bike[] newStock = new Bike[stock.length - 5];
			for (int i = 0; i < newStock.length; i++) {
				newStock[i] = stock[i];
			}
			this.stock = newStock;
		}
	}
}
