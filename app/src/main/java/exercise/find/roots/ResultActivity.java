package exercise.find.roots;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        long original_number = intent.getLongExtra("original_number", 0);
        long root1 = intent.getLongExtra("root1", 0);
        long root2 = intent.getLongExtra("root2", 0);
        long passedTime = intent.getLongExtra("time_passed_seconds", 0);

        TextView originalNumberView = findViewById(R.id.original_number);
        TextView root1View = findViewById(R.id.root1);
        TextView root2View = findViewById(R.id.root2);
        TextView passedTimeView = findViewById(R.id.passedTimeText);

        String original = getString(R.string.original_number)+String.valueOf(original_number);
        originalNumberView.setText(original);
        String root1_text =  getString(R.string.root1)+String.valueOf(root1);
        root1View.setText(root1_text);
        String root2_text = getString(R.string.root2)+String.valueOf(root2);
        root2View.setText(root2_text);
        String passedTime_text = getString(R.string.calculation_time)+String.valueOf(passedTime);
        passedTimeView.setText(passedTime_text);
    }
}
