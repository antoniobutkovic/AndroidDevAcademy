package ada.osc.task4;

import android.util.Log;

/**
 * Created by Toni on 4/26/2018.
 */

public class BMI {

    double height;
    double weight;

    public BMI(){

    }

    public BMI(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public double calculateBmi(){
        return weight / (Math.pow(height, 2));
    }

}
