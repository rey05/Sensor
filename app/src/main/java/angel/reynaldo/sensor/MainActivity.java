package angel.reynaldo.sensor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public  void b1 (View v){
        Intent intent1 = new Intent(MainActivity.this, SensorProximidad.class);
        startActivity(intent1);
    }

    public void b2 (View v) {
        Intent intent2 = new Intent(MainActivity.this, Senaceler.class);
        startActivity(intent2);
    }

    public void b3 (View v){
        Intent intent3 = new Intent(MainActivity.this, Senl.class);
        startActivity(intent3);
    }

    public void salir(View view){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.salir:
                finish();
                break;

            case R.id.SenProx:
                Intent a = new Intent("angel.reynaldo.sensor.SensorProximidad");
                startActivity(a);
                break;

            case R.id.Sena:
                Intent b = new Intent("angel.reynaldo.sensor.Senaceler");
                startActivity(b);
                break;

            case R.id.Senl:
                Intent c = new Intent("angel.reynaldo.sensor.Senl");
                startActivity(c);
                break;
        }
        return  false;
    }
}
