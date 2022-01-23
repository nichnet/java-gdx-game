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
	private ShapeRenderer shapeRenderer;
	
	Texture text;
	public Renderer() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
	}
	
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
		
		//update camera
		Game.getInstance().getCamera().update(batch);
		
		
		
		//render only visible items within range. 
		renderTiles();
		renderItems();
		renderSortedLayer();
		
		batch.end();
		shapeRenderer.end();
	}

	private void renderItems() {
		for(Item item : Game.getInstance().getCurrentWorld().getRenderableItems()) {
			item.render();
		}
	}
	
	private void renderSortedLayer() {
		List<ObjectBase> compiledLayer = new ArrayList<>();
		
		Collections.addAll(compiledLayer, Game.getInstance().getCurrentWorld().getRenderableObjects());
		Collections.addAll(compiledLayer, Game.getInstance().getCurrentWorld().getRenderableEntities());
		Collections.addAll(compiledLayer, Game.getInstance().getCurrentWorld().getRenderableLivingEntities());
		Collections.addAll(compiledLayer, Game.getInstance().getCurrentWorld().getRenderablePlayers());

		ObjectBase[] sorted = compiledLayer.toArray(new ObjectBase[] {});
		
		Arrays.sort(sorted);
		
		for(ObjectBase obj : sorted) {
			obj.render();
		}
	}
	
	private void renderTiles() {
		for(Tile tile : Game.getInstance().getCurrentWorld().getRenderableTiles()) {
			tile.render();
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
		shapeRenderer.setColor(Color.GREEN);
		
		Bounds bounds = obj.getAnimator().getCurrentSprite().getBounds();
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
		shapeRenderer.line(start.getX(), start.getY(), end.getX(), end.getY());			
	}

	public void draw(ObjectBase obj) {
		batch.draw(obj.getAnimator().getCurrentSprite().getTexture(), 
				obj.getPosition().getXAsPixel() + obj.getAsset().getOffsetX(), 
				obj.getPosition().getYAsPixel() + obj.getAsset().getOffsetY());
	}
}
