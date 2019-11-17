package mrmathami.thegame.entity.tile;

public final class Road extends AbstractTile {
	private double distance = Double.NaN;
	private int chiDuong;

	public Road(long createdTick, long posX, long posY) {
		super(createdTick, posX, posY, 1L, 1L);
	}
	public Road(long createdTick, long posX, long posY, int chiDuong) {
		super(createdTick, posX, posY, 1L, 1L);
		this.chiDuong= chiDuong;
	}
	public final int getChiDuong() {return chiDuong;}
	public final double getDistance() {
		return distance;
	}

	public final void setDistance(double distance) {
		this.distance = distance;
	}
}
