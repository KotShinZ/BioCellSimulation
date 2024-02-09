package com.shin.Particle.Component;

import com.shin.Area.ValueArea;
import com.shin.Baselib.DataClass.Dimention;
import com.shin.Particle.Particle;

public class MoveRamdom extends ParticleComponent {
    double len;
    Dimention dim;

    public MoveRamdom(double len, Dimention dim) {
        this.len = len;
        this.dim = dim;
    }

    @Override
    public void Move(ValueArea targetArea) {
        for (int i = 0; i < dim.ordinal() - 1; i++) {
            ((Particle) parent).pos.nums.get(i).AddIns(Math.random() > 0.5f ? len : -len);
        }
    }

    public MoveRamdom clone() {
        MoveRamdom s = (MoveRamdom) super.clone();
        s.dim = dim;
        s.len = len;
        return s;
    }
}
