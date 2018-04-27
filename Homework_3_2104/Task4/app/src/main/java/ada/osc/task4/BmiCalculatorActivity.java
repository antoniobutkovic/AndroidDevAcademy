package ada.osc.task4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BmiCalculatorActivity extends AppCompatActivity {

    @BindView(R.id.edittext_bmicalculator_height)
    EditText heightEt;

    @BindView(R.id.edittext_bmicalculator_weight)
    EditText weightEt;

    @BindView(R.id.imageview_bmicalculator_resultimg)
    ImageView resultIv;

    @BindView(R.id.textview_bmicalculator_resulttext)
    TextView resultTv;

    @BindView(R.id.textview_bmicalculator_descriptiontext)
    TextView descriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_bmicalculator_calculate)
    public void onCalculateBtnClicked(){

        Double height = Double.valueOf(heightEt.getText().toString());
        Double weight = Double.valueOf(weightEt.getText().toString());

        if (TextUtils.isEmpty(String.valueOf(height)) || TextUtils.isEmpty(String.valueOf(weight))){
            Toast.makeText(this, getString(R.string.bmicalculator_emptyfields), Toast.LENGTH_SHORT).show();
        }else if(height > 2.5 || height < 0.5){
            Toast.makeText(this, getString(R.string.bmicalculator_wronginputheight), Toast.LENGTH_SHORT).show();
        }else if(weight > 350 || weight < 15){
            Toast.makeText(this, getString(R.string.bmicalculator_wronginputweight), Toast.LENGTH_SHORT).show();
        }else {
            double result = new BMI(height, weight).calculateBmi();
            showResultsToUser(result);
        }
    }

    private void showResultsToUser(Double result) {
        if (result < 18.5){
            resultTv.setText(getString(R.string.bmicalculator_resultpothranjen));
            descriptionTv.setText(R.string.bmicalculator_pothranjendesc);
            resultIv.setImageDrawable(getResources().getDrawable(R.drawable.bmicalculator_pothranjen));
        }else if(result > 18.5 && result < 24.9){
            resultTv.setText(getString(R.string.bmicalculator_resultzdrav));
            descriptionTv.setText(R.string.bmicalculator_zdravdesc);
            resultIv.setImageDrawable(getResources().getDrawable(R.drawable.bmicalculator_zdrav));
        }else if(result > 25 && result < 29.9){
            resultTv.setText(getString(R.string.bmicalculator_resultdebeo));
            descriptionTv.setText(R.string.bmicalculator_debeodesc);
            resultIv.setImageDrawable(getResources().getDrawable(R.drawable.bmicalculator_debeo));
        }else {
            resultTv.setText(getString(R.string.bmicalculator_resultpretio));
            descriptionTv.setText(R.string.bmicalculator_pretiodesc);
            resultIv.setImageDrawable(getResources().getDrawable(R.drawable.bmicalculator_pretio));
        }
    }

}
