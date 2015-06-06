package angel.reynaldo.sensor;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.ActionBarActivity;
import java.util.List;


public class Senaceler extends Activity implements SensorEventListener{
    //Declaramos el TextView que se utilizara para mostrar los datos obtenidos del sensor
    private TextView sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senaceler);

        //hacemos la conexion de la parte grafica al codigo
        sa = (TextView) findViewById(R.id.sa);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        for (Sensor sensor: listaSensores) {
            log(sensor.getName());
        }
    }


//Metodo creado para implementar la escucha del evento iniciar del acelerometro
    public void inacel(){
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (!listaSensores.isEmpty()) {
            Sensor proximitySensor = listaSensores.get(0);
            sensorManager.registerListener(this, proximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);}
    }

    //Metodo creado para implementar la escucha del evento detener del acelerometro  y evitar que la aplicacion consuma recursos
    public void detacel(){

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (!listaSensores.isEmpty()) {
            Sensor proximitySensor = listaSensores.get(0);
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(proximitySensor.TYPE_ACCELEROMETER));
        }
    }

    //Metodo creado para implementar la escucha del evento limpiar del acelerometro
    public void limpiar(){sa.setText("");}

    private void log(String string) {sa.append(string + "\n");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_senaceler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {

            case R.id.inacel:
                inacel();
                return true;
            case R.id.detacel:
                detacel();
                return true;
            case R.id.limpiar:
                limpiar();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        synchronized (this){
            switch (event.sensor.getType()){

                case Sensor.TYPE_ACCELEROMETER:
                    log("Acelerómetro X: "+event.values[0]);
                    log("Acelerómetro Y: "+event.values[1]);
                    log("Acelerómetro Z: "+event.values[2]);
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
