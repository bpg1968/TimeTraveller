package ca.bgiroux.android.timetraveller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int thousands;
    int hundreds;
    int tens;
    int units;
    int distance;

    private class Row {
        private double speed;
        private double time;
        private int hours;
        private int minutes;
        private int seconds;

        public Row(int speed) {
            this.speed = speed;
            calculateTime(0);
        }

        public double getSpeed() {
            return this.speed;
        }

        public double getTime() {
            return this.time;
        }

        public int getHours() {
            return this.hours;
        }

        public int getMinutes() {
            return this.minutes;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public void calculateTime(int distance) {
            this.time = distance / this.speed;
            this.hours = (int) this.time;
            double m = (this.time - this.hours) * 60;
            this.minutes = (int) m;
            double s = (m - this.minutes) * 60;
            this.seconds = (int) s;
        }
    }

    Row[] r = new Row[19];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.thousands = 0;
        Log.d("MainActivity", "Thousands initialized at " + this.thousands);
        this.hundreds = 0;
        Log.d("MainActivity", "Hundreds initialized at " + this.hundreds);
        this.tens = 0;
        Log.d("MainActivity", "Tens initialized at " + this.tens);
        this.units = 0;
        Log.d("MainActivity", "Units initialized at " + this.units);
        this.distance = 0;
        Log.d("MainActivity", "Distance initialized at " + this.distance);

        for (int i = 0; i < 19; i++) {
            this.r[i] = new Row(i * 10 + 10);
            Log.d("MainActivity", "r[" + i + "].speed = " + r[i].getSpeed()
                    + "; r[" + i + "].time = " + r[i].getTime());
        }

        setContentView(R.layout.activity_main);
    }

    public void increaseUnits(View view) {
        if (this.units == 9) {
            this.units = 0;
            Log.d("MainActivity", "Units rolled over to " + this.units);
        } else {
            this.units++;
            Log.d("MainActivity", "Units increased to " + this.units);
        }
        displayUnits();
        calculateDistance();
        Log.d("MainActivity", "Distance increased to " + this.distance);
        displayTimes();
    }

    public void increaseTens(View view) {
        if (this.tens == 9) {
            this.tens = 0;
            Log.d("MainActivity", "Tens rolled over to " + this.tens);
        } else {
            this.tens++;
            Log.d("MainActivity", "Tens increased to " + this.tens);
        }
        displayTens();
        calculateDistance();
        Log.d("MainActivity", "Distance increased to " + this.distance);
        displayTimes();
    }

    public void increaseHundreds(View view) {
        if (this.hundreds == 9) {
            this.hundreds = 0;
            Log.d("MainActivity", "Hundreds rolled over to " + this.hundreds);
        } else {
            this.hundreds++;
            Log.d("MainActivity", "Hundreds increased to " + this.hundreds);
        }
        displayHundreds();
        calculateDistance();
        Log.d("MainActivity", "Distance increased to " + this.distance);
        displayTimes();
    }

    public void increaseThousands(View view) {
        if (this.thousands == 9) {
            this.thousands = 0;
        } else {
            this.thousands++;
            Log.d("MainActivity", "Thousands increased to " + this.thousands);
        }
        displayThousands();
        calculateDistance();
        Log.d("MainActivity", "Distance increased to " + this.distance);
        displayTimes();
    }

    public void decreaseThousands(View view) {
        if (this.thousands == 0) {
            this.thousands = 9;
            Log.d("MainActivity", "Thousands rolled over to " + this.thousands);
        } else {
            this.thousands--;
            Log.d("MainActivity", "Thousands decreased to " + this.thousands);
        }
        displayThousands();
        calculateDistance();
        Log.d("MainActivity", "Distance decreased to " + this.distance);
        displayTimes();
    }

    public void decreaseHundreds(View view) {
        if (this.hundreds == 0) {
            this.hundreds = 9;
            Log.d("MainActivity", "Hundreds rolled over to " + this.hundreds);
        } else {
            this.hundreds--;
            Log.d("MainActivity", "Hundreds decreased to " + this.hundreds);
        }
        displayHundreds();
        calculateDistance();
        Log.d("MainActivity", "Distance decreased to " + this.distance);
        displayTimes();
    }

    public void decreaseTens(View view) {
        if (this.tens == 0) {
            this.tens = 9;
            Log.d("MainActivity", "Tens rolled over to " + this.tens);
        } else {
            this.tens--;
            Log.d("MainActivity", "Tens decreased to " + this.tens);
        }
        displayTens();
        calculateDistance();
        Log.d("MainActivity", "Distance decreased to " + this.distance);
        displayTimes();
    }

    public void decreaseUnits(View view) {
        if (this.units == 0) {
            this.units = 9;
            Log.d("MainActivity", "Units rolled over to " + this.tens);
        } else {
            this.units--;
            Log.d("MainActivity", "Units decreased to " + this.units);
        }
        displayUnits();
        calculateDistance();
        Log.d("MainActivity", "Distance increased to " + this.distance);
        displayTimes();
    }

    public void calculateDistance() {
        this.distance
                = this.thousands * 1000
                + this.hundreds * 100
                + this.tens * 10
                + this.units;
    }

    public void displayThousands() {
        TextView v = (TextView) findViewById(R.id.thousands);
        if (v != null) v.setText(String.valueOf(this.thousands));
    }

    public void displayHundreds() {
        TextView v = (TextView) findViewById(R.id.hundreds);
        if (v != null) v.setText(String.valueOf(this.hundreds));
    }

    public void displayTens() {
        TextView v = (TextView) findViewById(R.id.tens);
        if (v != null) v.setText(String.valueOf(this.tens));
    }

    public void displayUnits() {
        TextView v = (TextView) findViewById(R.id.units);
        if (v != null) v.setText(String.valueOf(this.units));
    }

    public void displayTimes() {
        TextView v;
        r[0].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time0);
        if (v != null)
            v.setText(r[0].getHours() + "h " + r[0].getMinutes() + "m " + r[0].getSeconds() + "s");

        r[1].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time1);
        if (v != null)
            v.setText(r[1].getHours() + "h " + r[1].getMinutes() + "m " + r[1].getSeconds() + "s");

        r[2].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time2);
        if (v != null)
            v.setText(r[2].getHours() + "h " + r[2].getMinutes() + "m " + r[2].getSeconds() + "s");

        r[3].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time3);
        if (v != null)
            v.setText(r[3].getHours() + "h " + r[3].getMinutes() + "m " + r[3].getSeconds() + "s");

        r[4].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time4);
        if (v != null)
            v.setText(r[4].getHours() + "h " + r[4].getMinutes() + "m " + r[4].getSeconds() + "s");

        r[5].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time5);
        if (v != null)
            v.setText(r[5].getHours() + "h " + r[5].getMinutes() + "m " + r[5].getSeconds() + "s");

        r[6].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time6);
        if (v != null)
            v.setText(r[6].getHours() + "h " + r[6].getMinutes() + "m " + r[6].getSeconds() + "s");

        r[7].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time7);
        if (v != null)
            v.setText(r[7].getHours() + "h " + r[7].getMinutes() + "m " + r[7].getSeconds() + "s");

        r[8].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time8);
        if (v != null)
            v.setText(r[8].getHours() + "h " + r[8].getMinutes() + "m " + r[8].getSeconds() + "s");

        r[9].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time9);
        if (v != null)
            v.setText(r[9].getHours() + "h " + r[9].getMinutes() + "m " + r[9].getSeconds() + "s");

        r[10].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time10);
        if (v != null)
            v.setText(r[10].getHours() + "h " + r[10].getMinutes() + "m " + r[10].getSeconds() + "s");

        r[11].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time11);
        if (v != null)
            v.setText(r[11].getHours() + "h " + r[11].getMinutes() + "m " + r[11].getSeconds() + "s");

        r[12].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time12);
        if (v != null)
            v.setText(r[12].getHours() + "h " + r[12].getMinutes() + "m " + r[12].getSeconds() + "s");

        r[13].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time13);
        if (v != null)
            v.setText(r[13].getHours() + "h " + r[13].getMinutes() + "m " + r[13].getSeconds() + "s");

        r[14].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time14);
        if (v != null)
            v.setText(r[14].getHours() + "h " + r[14].getMinutes() + "m " + r[14].getSeconds() + "s");

        r[15].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time15);
        if (v != null)
            v.setText(r[15].getHours() + "h " + r[15].getMinutes() + "m " + r[15].getSeconds() + "s");

        r[16].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time16);
        if (v != null)
            v.setText(r[16].getHours() + "h " + r[16].getMinutes() + "m " + r[16].getSeconds() + "s");

        r[17].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time17);
        if (v != null)
            v.setText(r[17].getHours() + "h " + r[17].getMinutes() + "m " + r[17].getSeconds() + "s");

        r[18].calculateTime(this.distance);
        v = (TextView) findViewById(R.id.time18);
        if (v != null)
            v.setText(r[18].getHours() + "h " + r[18].getMinutes() + "m " + r[18].getSeconds() + "s");
    }
}