package exercise.find.roots;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.util.TimeUtils;

import java.util.concurrent.TimeUnit;

public class CalculateRootsService extends IntentService {


  public CalculateRootsService() {
    super("CalculateRootsService");
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent == null) return;
    long timeStartMs = System.currentTimeMillis();
    long numberToCalculateRootsFor = intent.getLongExtra("number_for_service", 0);
    if (numberToCalculateRootsFor <= 0) {
      Log.e("CalculateRootsService", "can't calculate roots for non-positive input" + numberToCalculateRootsFor);
      return;
    }
    long firstNum = numberToCalculateRootsFor;
    long secondNum = 1;
    if (numberToCalculateRootsFor == 1){
      firstNum = 1;
      secondNum = 1;
    }
    else{
      for (long i=2; i < Math.sqrt(numberToCalculateRootsFor); i++){
        if (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - timeStartMs) >= 20){
          firstNum = -1;
          secondNum = -1;
          break;
        }
        if (numberToCalculateRootsFor % i == 0){
          firstNum = i;
          secondNum = numberToCalculateRootsFor / i;
          break;
        }
      }
    }
    Intent curIntent = new Intent();
    if (firstNum == -1){
      curIntent.setAction("stopped_calculations");
      curIntent.putExtra("original_number", numberToCalculateRootsFor);
      curIntent.putExtra("time_passed_seconds", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - timeStartMs));
    }
    else{
      curIntent.setAction("found_roots");
      curIntent.putExtra("original_number", numberToCalculateRootsFor);
      curIntent.putExtra("root1", firstNum);
      curIntent.putExtra("root2", secondNum);
      curIntent.putExtra("time_passed_seconds", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - timeStartMs));
//      curIntent.putExtra("time_passed_seconds", System.currentTimeMillis() - timeStartMs);
    }
    sendBroadcast(curIntent);
  }
}