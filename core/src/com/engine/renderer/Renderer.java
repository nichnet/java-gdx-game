package com.engine.renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.engine.assets.graphics.Bounds;
import com.engine.assets.graphics.Vector;
import com.engine.assets.model.AssetBase;
import com.engine.util.Logger;
import com.engine.world.Item;
import com.engine.world.ObjectBase;
import com.engine.world.PlayerCharacter;
import com.engine.world.Position;
import com.engine.world.Tile;
import com.engine.world.World;
import com.game.Game;

public class Renderer {

	private static Renderer instance;
	
	private SpriteBatch batch;
	private ShapeRenderer lineRenderer;
	
	Texture text;
	public Renderer() {
		batch = new SpriteBatch();
		lineRenderer = new ShapeRenderer();
}
	
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		
		
		batch.begin();
	
		//update camera
		Game.getInstance().getCamera().update(batch);
		
	
		//render only visible items within range. 
		renderTiles(false);
		renderItems(false);
		renderSortedLayer(false);

		batch.end();
		
		
		if(Game.getInstance().isDebugModeActive()) {
			lineRenderer.begin(ShapeRenderer.ShapeType.Line);
			lineRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			
			//render only visible items within range. 
			renderTiles(true);
			renderItems(true);
			renderSortedLayer(true);			

			lineRenderer.end();
		}
	}

	private void renderItems(boolean debug) {
		for(Item item : Game.getInstance().getCurrentWorld().getRenderableItems()) {
			if(debug) {
				item.renderDebug();
			} else {
				item.render();				
			}
		}
	}
	
	private void renderSortedLayer(boolean debug) {
		List<ObjectBase> compiledLayer = new ArrayList<>();
		
		Collections.addAll(compiledLayer, Game.getInstance().getCurrentWorld().getRenderableObjects());
		Collections.addAll(compiledLayer, Game.getInstance().getCurrentWorld().getRenderableEntities());
		Collections.addAll(compiledLayer, Game.getInstance().getCurrentWorld().getRenderableLivingEntities());
		Collections.addAll(compiledLayer, Game.getInstance().getCurrentWorld().getRenderablePlayers());

		ObjectBase[] sorted = compiledLayer.toArray(new ObjectBase[] {});
		
		Arrays.sort(sorted);
		
		for(ObjectBase obj : sorted) {
			if(debug) {
				obj.renderDebug();
			} else {
				obj.render();				
			}
		}
	}
	
	private void renderTiles(boolean debug) {
		for(Tile tile : Game.getInstance().getCurrentWorld().getRenderableTiles()) {
			if(debug) {
				tile.renderDebug();
			} else {
				tile.render();				
			}
		}
	}
	
	public void dispose() {
		batch.dispose();
		
		//dispose all textures.
		//img.dispose();
	}
	
	public static Renderer getInstance() {
		if(instance == null) {
			instance = new Renderer();
		}
		
		return instance;
	}
	
	public void drawShape(ObjectBase obj) {
		lineRenderer.setColor(Color.GREEN);
		/*if(obj.getCollider().isColliding()) {
			lineRenderer.setColor(Color.RED);
		} else {
			lineRenderer.setColor(Color.GREEN);
		}
		*/
		drawOutline(obj);
	}
	
	
	private void drawOutline(ObjectBase obj) {
		Bounds bounds = obj.getBounds();
		
		if(bounds == null) {
			return;
		}
		
		Position position = obj.getPosition();

		int offsetX = obj.getAsset().getOffsetX();
		int offsetY = obj.getAsset().getOffsetY();
		
		Vector first = null;
		Vector previous = null;
		
		if(bounds.getPoints().length > 0) {
			for(Vector point : bounds.getPoints()) {
				int endX = position.getXAsPixel() + point.getX() + offsetX;
				int endY = position.getYAsPixel() + point.getY() + offsetY;
				Vector end = new Vector(endX, endY);
				
				
				if(previous != null) {
					drawLine(previous, end);
				} else {
					//set the first point.
					first = end;
				}
				
				previous = end;
			}
	
			//draw a line from last to first.
			drawLine(previous, first);
		}
	}
	
	private void drawLine(Vector start, Vector end) {
		lineRenderer.line(start.getX(), start.getY(), end.getX(), end.getY());			
	}

	public void draw(ObjectBase obj) {

		if(!batch.isDrawing()) {
			return;
		}
		batch.draw(obj.getAnimator().getCurrentSprite().getTexture(), 
				obj.getPosition().getXAsPixel() + obj.getAsset().getOffsetX(), 
				obj.getPosition().getYAsPixel() + obj.getAsset().getOffsetY());
	}
}
