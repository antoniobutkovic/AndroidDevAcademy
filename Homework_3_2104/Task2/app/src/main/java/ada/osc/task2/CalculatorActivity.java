package ada.osc.task2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorActivity extends AppCompatActivity {

    @BindView(R.id.edittext_calculator_input1)
    EditText inputOneEt;

    @BindView(R.id.edittext_calculator_input2)
    EditText inputTwoEt;

    @BindView(R.id.textview_calculator_result)
    TextView resultTv;

    @BindView(R.id.button_calculator_addition)
    Button additionBtn;

    @BindView(R.id.button_calculator_subtraction)
    Button subtractionBtn;

    @BindView(R.id.button_calculator_multiplication)
    Button multiplicationBtn;

    @BindView(R.id.button_calculator_division)
    Button divisionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_calculator_addition)
    public void onAdditionBtnClicked(){
        String inputOne = inputOneEt.getText().toString();
        String inputTwo = inputTwoEt.getText().toString();
        int result = 0;
        try{
            result = Integer.parseInt(inputOne) - Integer.parseInt(inputTwo);
        }catch (ArithmeticException e){
            Toast.makeText(this, getString(R.string.calculator_zeroexception), Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException e){
            Toast.makeText(this, R.string.calculator_emptyfields, Toast.LENGTH_SHORT).show();
        }finally {
            resultTv.setText(String.valueOf(result));
        }
        inputOneEt.setText("");
        inputTwoEt.setText("");
    }

    @OnClick(R.id.button_calculator_subtraction)
    public void onSubtractionBtnClicked(){
        String inputOne = inputOneEt.getText().toString();
        String inputTwo = inputTwoEt.getText().toString();
        int result = 0;
        try{
            result = Integer.parseInt(inputOne) - Integer.parseInt(inputTwo);
        }catch (ArithmeticException e){
            Toast.makeText(this, getString(R.string.calculator_zeroexception), Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException e){
            Toast.makeText(this, R.string.calculator_emptyfields, Toast.LENGTH_SHORT).show();
        }finally {
            resultTv.setText(String.valueOf(result));
        }
        inputOneEt.setText("");
        inputTwoEt.setText("");
    }

    @OnClick(R.id.button_calculator_multiplication)
    public void onMultiplicationBtnClicked(){
        String inputOne = inputOneEt.getText().toString();
        String inputTwo = inputTwoEt.getText().toString();
        int result = 0;
        try{
            result = Integer.parseInt(inputOne) * Integer.parseInt(inputTwo);
        }catch (ArithmeticException e){
            Toast.makeText(this, getString(R.string.calculator_zeroexception), Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException e){
            Toast.makeText(this, R.string.calculator_emptyfields, Toast.LENGTH_SHORT).show();
        }finally {
            resultTv.setText(String.valueOf(result));
        }
        inputOneEt.setText("");
        inputTwoEt.setText("");
    }
    @OnClick(R.id.button_calculator_division)
    public void onDivisionBtnClicked(){

        String inputOne = inputOneEt.getText().toString();
        String inputTwo = inputTwoEt.getText().toString();
        int result = 0;
        try{
            result = Integer.parseInt(inputOne) / Integer.parseInt(inputTwo);
        }catch (ArithmeticException e){
            Toast.makeText(this, getString(R.string.calculator_zeroexception), Toast.LENGTH_SHORT).show();
        }catch (NumberFormatException e){
            Toast.makeText(this, R.string.calculator_emptyfields, Toast.LENGTH_SHORT).show();
        }finally {
            resultTv.setText(String.valueOf(result));
        }
        inputOneEt.setText("");
        inputTwoEt.setText("");
    }
}
