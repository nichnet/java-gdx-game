package com.engine.assets.graphics;

import com.engine.util.Logger;

public class Bounds {

	private Vector[] points;
	
	public Bounds(int[] points) {
		//ensure that the points array is even, as each vector will be (x, y)
		int length = points.length / 2;
		if(length % 2 != 0) {
			//not even, one must be missing so just add a 0.. 
			length++;
		}
		
		this.points = new Vector[length];

		for(int j = 0, i = 0; i < points.length; j++, i+= 2) {
			int x = points[i];
			int y = points[i+1];
			this.points[j] = new Vector(x, y);
		}
	}
	
	public Vector[] getPoints() {
		return points;
	}
	
	@Override
	public String toString() {
		return "TODO bounds";//TODO bounds class
	}
}
