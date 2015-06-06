package angel.reynaldo.sensor;

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

public class SensorProximidad extends Activity  implements SensorEventListener{
    //Declaramos el TextView que se utilizara para mostrar los datos obtenidos del sensor
    private TextView sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_proximidad);

        //hacemos la conexion de la parte grafica al codigo
        sp = (TextView) findViewById(R.id.sp);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
       List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);

        for (Sensor sensor: listaSensores) {
          log(sensor.getName());
        }
    }


    //Metodo creado para implementar la escucha del evento iniciar del sensor de proximidad
    public void iniciarSensorp(){
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);

        if (!listaSensores.isEmpty()) {
            Sensor proximitySensor = listaSensores.get(0);
            sensorManager.registerListener(this, proximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);}
    }

    //Metodo creado para implementar la escucha del evento detener del sensor de proximidad
    public  void detenerSensorp(){

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (!listaSensores.isEmpty()) {
            Sensor proximitySensor = listaSensores.get(0);
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(proximitySensor.TYPE_PROXIMITY));
        }

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){

            switch (event.sensor.getType()){

                case Sensor.TYPE_PROXIMITY:
                    log("Proximidad: "+event.values[0]);
                    break;
                default:
                    for (int i=0 ; i<event.values.length ; i++) {
                        log("Temperatura "+i+": "+event.values[i]);
                    }
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    private void log(String string) {
        sp.append(string + "\n");
    }
    //Metodo creado para implementar la escucha del evento limpiar del sensor de proximidad
    public void limpiar(){sp.setText("");}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor_proximidad, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.iniciarp:
                iniciarSensorp();
                return true;
            case R.id.detenerp:
                detenerSensorp();
                return true;
            case R.id.limpiarp:
                limpiar();
                return true;
            default:

        return super.onOptionsItemSelected(item);
    }
    }
}
