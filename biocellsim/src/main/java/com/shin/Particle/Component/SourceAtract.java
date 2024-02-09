package com.shin.Particle.Component;

import com.shin.Area.ValueArea;

public class SourceAtract extends ParticleComponent {
    public double amount = 0.1f;

    public SourceAtract(double amount) {
        this.amount = amount;
    }

    @Override
    public void Move(ValueArea targetArea) {
        targetArea.addNext(amount);
    }

    public SourceAtract clone() {
        SourceAtract s = (SourceAtract) super.clone();
        s.amount = amount;
        return s;
    }
}
