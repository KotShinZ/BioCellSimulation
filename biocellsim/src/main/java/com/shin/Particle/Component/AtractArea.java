package com.shin.Particle.Component;

import com.shin.Area.AreaArray;
import com.shin.Area.ValueArea;
import com.shin.Particle.Particle;
import com.shin.Area.Area;

public class AtractArea extends ParticleComponent {
    public AreaArray array;

    public double len = 1;
    public double threshold = 0.2f;

    public AtractArea(AreaArray array) {
        this.array = array;
    }

    public AtractArea(AreaArray array, double len, double threshold) {
        this.array = array;
        this.len = len;
        this.threshold = threshold;
    }

    public AtractArea(AreaArray array, double threshold) {
        this.array = array;
        this.threshold = threshold;
    }

    @Override
    public void Move(ValueArea targetArea) {
        var max = GetMaxArea(targetArea);
        ((Particle) parent).pos.Add(max.position.get().Sub(targetArea.position.get()).Mul(len));
    }

    // すべての方向で最大のエリアを求める
    public ValueArea GetMaxArea(ValueArea targetArea) {
        var areas = array.GetAllNextArea(targetArea);
        ValueArea max = null;
        for (Area area : areas) {
            ValueArea v = (ValueArea) area;

            if (v.get() < threshold)
                continue; // 閾値を設定

            if (max == null || max.get() < v.get()) {
                max = v;
            }
        }
        return max;
    }

    public AtractArea clone() {
        AtractArea s = (AtractArea) super.clone();
        s.array = array;
        s.len = len;
        s.threshold = threshold;
        return s;
    }
}
