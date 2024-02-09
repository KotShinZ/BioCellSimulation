package com.shin.Particle;

import java.util.function.Supplier;

import com.shin.Area.AreaArray;
import com.shin.Area.Interface.ITimeUpdatable;
import com.shin.Baselib.DataClass.vector.Vector3;

public class ParticleListUpdatable extends ParticleList implements ITimeUpdatable {

    public AreaArray areaArray;

    public ParticleListUpdatable(AreaArray areaArray) {
        this.areaArray = areaArray;
    }

    public ParticleListUpdatable(AreaArray areaArray, Particle particle, int num, Supplier<Vector3> pos) {
        super(particle, num, pos);
        this.areaArray = areaArray;
    }

    @Override
    public void Update() {
        Move(null);
    }

}
