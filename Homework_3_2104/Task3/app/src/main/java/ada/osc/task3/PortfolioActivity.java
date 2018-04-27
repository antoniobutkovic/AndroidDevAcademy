package ada.osc.task3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PortfolioActivity extends Activity {

    @BindView(R.id.imageview_portfolio_profileimg)
    CircleImageView profileCiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        ButterKnife.bind(this);


        profileCiv.setImageDrawable(getResources().getDrawable(R.drawable.profile_img));

    }
}
