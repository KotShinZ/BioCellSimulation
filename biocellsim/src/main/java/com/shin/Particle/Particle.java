package com.shin.Particle;

import com.shin.Area.ValueArea;
import com.shin.Baselib.Component.ComponentParent;
import com.shin.Baselib.DataClass.vector.Vector3;
import com.shin.Particle.Component.ParticleComponent;

public class Particle extends ComponentParent implements Cloneable {
	public Vector3 pos = new Vector3();
	public int ID = -1;

	public Particle() {

	}

	public Particle(Vector3 pos) {
		this.pos = pos;
	}

	public Particle(Vector3 pos, int ID) {
		this.pos = pos;
		this.ID = ID;
	}

	public void Move() {
		ComponentUpdate(ParticleComponent.class, p -> p.Move(null));
	}

	public void Move(ValueArea area) {
		ComponentUpdate(ParticleComponent.class, p -> p.Move(area));
	}

	public Particle clone() {
		var p = super.clone();
		return (Particle) p;
	}
}
