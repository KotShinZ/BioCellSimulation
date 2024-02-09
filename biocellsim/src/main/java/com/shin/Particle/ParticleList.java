package com.shin.Particle;

import java.util.ArrayList;
import java.util.function.Supplier;

import javax.management.ValueExp;

import com.shin.Area.AreaArray;
import com.shin.Area.Interface.ITimeUpdatable;
import com.shin.Baselib.DataClass.vector.Vector3;

public class ParticleList {
    public ArrayList<Particle> particles = new ArrayList<>();

    public ParticleList() {
    }

    /** 複製しながらパーティクルを生成 */
    public ParticleList(Particle particle, int num, Supplier<Vector3> pos) {
        AddParticle(particle, num, pos);
    }

    public void AddParticle(Particle particle) {
        particles.add(particle);
    }

    public void AddParticle(Particle particle, int num, Supplier<Vector3> pos) {
        for (int i = 0; i < num; i++) {
            var newP = particle.clone();
            newP.pos = pos.get();
            AddParticle(particle);
        }
    }

    public void Move(AreaArray areaArray) {
        particles.forEach(p -> p.Move(areaArray.GetArea(p.pos)));
    }
}
