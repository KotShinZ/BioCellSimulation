package com.shin.Particle.Component;

import com.shin.Area.ValueArea;
import com.shin.Baselib.Component.Component;

public abstract class ParticleComponent extends Component {
    public abstract void Move(ValueArea targetArea);
}