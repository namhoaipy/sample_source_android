package nam.com.rsa_demo.event;

import org.json.JSONArray;

public interface SimpleEditRSAEvent {
    void onEmptyRSA(JSONArray jsonArray);
    void onArrayRSA(JSONArray jsonArray);
    void onPushRSA(String result);
    void onEmpty(String result);
}
