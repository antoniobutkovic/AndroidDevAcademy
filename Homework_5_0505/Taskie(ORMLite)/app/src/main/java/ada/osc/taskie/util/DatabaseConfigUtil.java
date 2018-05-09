package ada.osc.taskie.util;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import ada.osc.taskie.R;
import ada.osc.taskie.model.Task;

/**
 * Created by Toni on 5/9/2018.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil{

    public static final Class<?>[] classes = new Class[]{Task.class};


    public static void main(String[] args) throws IOException, SQLException {

        writeConfigFile("ormlite_config", classes);
    }
}
