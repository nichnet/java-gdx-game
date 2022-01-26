package com.engine.physics;

import com.engine.assets.graphics.Bounds;
import com.engine.assets.graphics.Vector;
import com.engine.util.Logger;
import com.engine.world.ObjectBase;
import com.engine.world.Tile;
import com.game.Game;

public class Physics {
	
	private static Physics instance;
	
	public void tick() {
		//set this nested for loop up in such way that objects cant be tested against themselves.
		ObjectBase[] objects = Game.getInstance().getCurrentWorld().getAllObjects();
		for(int m = 0; m < objects.length; m++) {			
			for(int n = m + 1; n < objects.length; n++) {
				ObjectBase obj = objects[m];
				ObjectBase other = objects[n];
				
				if(obj.getCollider() == null || other.getCollider() == null) {
					//TODO if there are bounds then there should be collider, otherwise no collider.
					//only check objects with colliders for physics engine.
					continue;
				}
				
/*				if(obj.getId().equals(other.getId())) {
					//skip if its the same object.
					continue;
				}*/
		
				checkCollision(obj, other);	
			}
		}

	}

	private void checkCollision(ObjectBase obj, ObjectBase other) {
		boolean colliding = shapeOverlapSeperatedAxisTheorem(obj, other);
	//	boolean colliding = shapeOverlapDiagonasssssssssssssssslTheorem(obj, other);
		
		obj.getCollider().setCollision(other, colliding);
		other.getCollider().setCollision(obj, colliding);			
	}

	private boolean shapeOverlapSeperatedAxisTheorem(ObjectBase obj1, ObjectBase obj2) {
		ObjectBase b1 = obj1;
		ObjectBase b2 = obj2;
		
		for(int shape = 0; shape < 2; shape++) {

			//swap
			if(shape == 1) {
				b1 = obj2;
				b2 = obj1;
			}

			int b1Size = b1.getBounds().getPoints().length;
			int b2Size = b2.getBounds().getPoints().length;
			
			for(int a = 0; a < b1Size; a++) {
				int b = (a + 1) % b1Size;
				
				//this will give a normal.
				int x1 = b1.getPosition().getXAsPixel() + b1.getAsset().getOffset().getX() + b1.getBounds().getPoints()[b].getX();
				int x2 = b1.getPosition().getXAsPixel() + b1.getAsset().getOffset().getX() + b1.getBounds().getPoints()[a].getX();
				int y1 = b1.getPosition().getYAsPixel() + b1.getAsset().getOffset().getY() + b1.getBounds().getPoints()[b].getY();
				int y2 = b1.getPosition().getYAsPixel() + b1.getAsset().getOffset().getY() + b1.getBounds().getPoints()[a].getY();
				//normal vector to the edge.
				Vector axisProjected = new Vector(-(y1 - y2), x1 - x2);

				//work out min and max extends of the projection lie.
				float minR1 = Integer.MAX_VALUE;
				float maxR1 = Integer.MIN_VALUE;
				
				//iterate the points on the first bounds (poly)
				for (int p = 0; p < b1Size; p++) {
					int xx = b1.getPosition().getXAsPixel() + b1.getAsset().getOffset().getX() + b1.getBounds().getPoints()[p].getX();
					int yy = b1.getPosition().getYAsPixel() + b1.getAsset().getOffset().getY() + b1.getBounds().getPoints()[p].getY();
					
					//calculate dot product, scalar value
					float q = (xx * axisProjected.getX() + yy * axisProjected.getY());
					minR1 = Math.min(minR1, q);
					maxR1 = Math.max(maxR1, q);
				}
				
				float minR2 = Integer.MAX_VALUE;
				float maxR2 = Integer.MIN_VALUE;
				
				for (int p = 0; p < b2Size; p++) {
					int xx = b2.getPosition().getXAsPixel() + b2.getAsset().getOffset().getX() + b2.getBounds().getPoints()[p].getX();
					int yy = b2.getPosition().getYAsPixel() + b2.getAsset().getOffset().getY() + b2.getBounds().getPoints()[p].getY();
					
					//calculate dot product, scalar value
					float q = (xx * axisProjected.getX() + yy * axisProjected.getY());
					minR2 = Math.min(minR2, q);
					maxR2 = Math.max(maxR2, q);
				}
				
				//check for overlap.
				if(!(maxR2 >= minR1 && maxR1 >= minR2)) {
					return false;//there is no overlap
				}
			}
		}
		
		//the shapes must be overlapping
		return true;
	}
	

	private boolean shapeOverlapSeperatedAxisTheorem2(ObjectBase obj1, ObjectBase obj2) {
		ObjectBase b1 = obj1;
		ObjectBase b2 = obj2;
		
		for(int shape = 0; shape < 2; shape++) {

			//swap
			if(shape == 1) {
				b1 = obj2;
				b2 = obj1;
			}

			int b1Size = b1.getBounds().getPoints().length;
			int b2Size = b2.getBounds().getPoints().length;
			
			for(int a = 0; a < b1Size; a++) {
				int b = (a + 1) % b1Size;
				
				//this will give a normal.
				int x1 = b1.getPosition().getXAsPixel() + b1.getAsset().getOffset().getX() + b1.getBounds().getPoints()[b].getX();
				int x2 = b1.getPosition().getXAsPixel() + b1.getAsset().getOffset().getX() + b1.getBounds().getPoints()[a].getX();
				int y1 = b1.getPosition().getYAsPixel() + b1.getAsset().getOffset().getY() + b1.getBounds().getPoints()[b].getY();
				int y2 = b1.getPosition().getYAsPixel() + b1.getAsset().getOffset().getY() + b1.getBounds().getPoints()[a].getY();
				//normal vector to the edge.
				Vector axisProjected = new Vector(-(y1 - y2), x1 - x2);

				//work out min and max extends of the projection lie.
				float minR1 = Integer.MAX_VALUE;
				float maxR1 = Integer.MIN_VALUE;
				
				//iterate the points on the first bounds (poly)
				for (int p = 0; p < b1Size; p++) {
					int xx = b1.getPosition().getXAsPixel() + b1.getAsset().getOffset().getX() + b1.getBounds().getPoints()[p].getX();
					int yy = b1.getPosition().getYAsPixel() + b1.getAsset().getOffset().getY() + b1.getBounds().getPoints()[p].getY();
					
					//calculate dot product, scalar value
					float q = (xx * axisProjected.getX() + yy * axisProjected.getY());
					minR1 = Math.min(minR1, q);
					maxR1 = Math.max(maxR1, q);
				}
				
				float minR2 = Integer.MAX_VALUE;
				float maxR2 = Integer.MIN_VALUE;
				
				for (int p = 0; p < b2Size; p++) {
					int xx = b2.getPosition().getXAsPixel() + b2.getAsset().getOffset().getX() + b2.getBounds().getPoints()[p].getX();
					int yy = b2.getPosition().getYAsPixel() + b2.getAsset().getOffset().getY() + b2.getBounds().getPoints()[p].getY();
					
					//calculate dot product, scalar value
					float q = (xx * axisProjected.getX() + yy * axisProjected.getY());
					minR2 = Math.min(minR2, q);
					maxR2 = Math.max(maxR2, q);
				}
				
				//check for overlap.
				if(!(maxR2 >= minR1 && maxR1 >= minR2)) {
					return false;//there is no overlap
				}
			}
		}
		
		//the shapes must be overlapping
		return true;
	}
	
	private boolean shapeOverlapDiagonalTheorem(ObjectBase obj1, ObjectBase obj2) {
		ObjectBase b1 = obj1;
		ObjectBase b2 = obj2;
		
		for(int shape = 0; shape < 2; shape++) {

			//swap
			if(shape == 1) {
				b1 = obj2;
				b2 = obj1;
			}

			int b1Size = b1.getBounds().getPoints().length;
			int b2Size = b2.getBounds().getPoints().length;
			
			for(int p = 0; p < b1Size; p++) {//check diags of poly
				int startX =b1.getPosition().getXAsPixel() + b1.getAsset().getOffset().getX();
				int startY =b1.getPosition().getYAsPixel() + b1.getAsset().getOffset().getY();
				
				Vector lineR1Start = new Vector(startX, startY);
				
				int endX = startX + b1.getBounds().getPoints()[p].getX();
				int endY = startY + b1.getBounds().getPoints()[p].getY();
				
				Vector lineR1End = new Vector(endX, endY);
				
				for(int q = 0; q < b2Size; q++) {//against edges of other 
					int xx = b2.getPosition().getXAsPixel() + b2.getAsset().getOffset().getX() + b2.getBounds().getPoints()[q].getX();
					int yy = b2.getPosition().getYAsPixel() + b2.getAsset().getOffset().getY() + b2.getBounds().getPoints()[q].getY();
					
					Vector lineR2Start = new Vector(xx, yy);
					//neighbouring point, wrapping around to get the full polygon.
					Vector lineR2End = b2.getBounds().getPoints()[(q + 1) % b2Size];
					
					//standard off-the-shelf, "line segment intersection".
					float h = (lineR2End.getX() - lineR2Start.getX()) * (lineR1Start.getY() - lineR1End.getY()) - (lineR1Start.getX() - lineR1End.getX()) * (lineR2End.getY() - lineR2Start.getY());
					float t1  = ((lineR2Start.getY() - lineR2End.getY()) * (lineR1Start.getX() - lineR2Start.getX()) + (lineR2End.getX() - lineR2Start.getX()) * (lineR1Start.getY() - lineR2Start.getY()) / h);
					float t2 = ((lineR1Start.getY() - lineR1End.getY()) * (lineR1Start.getX() - lineR2Start.getX()) + (lineR1End.getX() - lineR1Start.getX()) * (lineR1Start.getY() - lineR2Start.getY()) / h);
					
					
					Vector displacement = Vector.zero();
					if(t1 >= 0.0f && t1 < 1.0f && t2 >= 0.0f && t2 < 1.0f) {
						displacement.setX((int) (displacement.getX() + ((1.0f - t1) * (lineR1End.getX() - lineR1Start.getX()))));
						displacement.setY((int) (displacement.getY() + ((1.0f - t1) * (lineR1End.getY() - lineR1Start.getY()))));
						//the shapes arent overlapping
					//	return true;
					}
				}	

				obj1.getPosition().setX(obj1.getPosition().getXAsPixel() * (shape == 0 ? - 1 : + 1));
				obj1.getPosition().setY(obj1.getPosition().getYAsPixel() * (shape == 0 ? - 1 : + 1));
			}
			
		}
		
		//the shapes arent overlapping
		return false;
	}
	
	public static Physics getInstance() {
		if(instance == null) {
			instance = new Physics();
		}
		
		return instance;
	}
}
