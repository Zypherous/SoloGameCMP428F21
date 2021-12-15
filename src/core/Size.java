package core;

import io.Persistable;

public class Size implements Persistable {
	private int width;
	private int height;
	public Size(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "Size [width=" + width + ", height=" + height + "]";
	}
	
	public void setSize(int w, int h) {
		this.height = h;
		this.width = w;
	}
	public static Size copyOf(Size size) {
		return new Size(size.getWidth(), size.getHeight());
	}
	@Override
	public String serialize() {
		return String.format("%d|%d",width, height);
	}
	@Override
	public void applySerializedData(String serializedData) {
		String tokens[] = serializedData.split("\\|");
		width = Integer.parseInt(tokens[0]);
		height = Integer.parseInt(tokens[1]);
	}
}
	