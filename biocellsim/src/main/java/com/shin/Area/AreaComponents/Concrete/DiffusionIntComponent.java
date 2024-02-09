package com.shin.Area.AreaComponents.Concrete;

/** 値を拡散させる */
public class DiffusionIntComponent {

    public float D = 0.1f;

    public DiffusionIntComponent() {
    }

    public DiffusionIntComponent(float D) {
        this.D = D;
    }

    /*
     * @Override
     * public void Update(AreaBase other) {
     * int preNum = (int) area().preParam.num; // 前の自分の数
     * int _increase = Utill.GetIntProbability(preNum * D); // 増分
     * var increase = (area().param.num - _increase) < 0 ? area().param.num :
     * _increase;// マイナスにしない
     * 
     * area().param.num -= increase; // 自分の数を減らす
     * other.param.num += increase; // 隣の数を増やす
     * 
     * }
     * 
     * private void Print(AreaBase other) {
     * int preNum = (int) area().preParam.num; // 前の自分の数
     * int _increase = Utill.GetIntProbability(preNum * D); // 増分
     * var increase = (area().param.num - _increase) < 0 ? area().param.num :
     * _increase;// マイナスにしない
     * 
     * if (increase != 0) {
     * System.out.println("Increse : " +
     * String.valueOf(preNum * D) + ", " + String.valueOf(_increase) + ", " +
     * String.valueOf(increase));
     * System.out.println("Area : " + area().param.num);
     * System.out.println("Other : " + other.param.num);
     * ((AreaIntPos) area()).pos.Print();
     * ((AreaIntPos) other).pos.Print();
     * System.out.println("");
     * }
     * }
     */
}
