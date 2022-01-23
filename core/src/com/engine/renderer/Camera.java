package com.engine.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.engine.assets.graphics.Vector;
import com.engine.world.Entity;
import com.engine.world.Position;

public class Camera {

	private OrthographicCamera cam;
	private Entity attachedToEntity;
	private Vector position;

	public Camera() {
		cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		position = Vector.zero();
	}
	
	public void attachToEntity(Entity entity) {
		this.attachedToEntity = entity;
	}
	
	public void detatchFromEntity() {
		this.attachedToEntity = null;
	}
	
	public void update(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		
		if(attachedToEntity != null) {
			updatePosition(attachedToEntity.getPosition());
			cam.update();
		}
	}
	
	private void updatePosition(Position pos) {
		position.setX(attachedToEntity.getPosition().getXAsPixel());
		position.setY(attachedToEntity.getPosition().getYAsPixel());
		cam.position.set((float)pos.getXAsPixel(), (float) pos.getYAsPixel(), 0);
	}

	public Vector getPosition() {
		return position;
	}
}
