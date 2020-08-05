package nam.com.rsa_demo.presenter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nam.com.rsa_demo.adapter.SimpleListRSAAdapter;
import nam.com.rsa_demo.event.SimpleEditRSAEvent;
import nam.com.rsa_demo.event.SimpleListRSAEvent;
import nam.com.rsa_demo.model.SimpleRSAModel;
import nam.com.rsa_demo.rsa.CipherRsa;
import nam.com.rsa_demo.rsa.GetKeyRSA;
import nam.com.rsa_demo.util.Base64Controller;
import nam.com.rsa_demo.util.Cache;

import static nam.com.rsa_demo.util.Entity.LIST_RSA;

@SuppressLint("NewApi")
public class SimpleListEditRSAPresenter {

    private Context context;
    private Cache cache;
    private JSONArray jsonArray;
    /*
     * @params value edit rsa activity
     *  */
    private SimpleEditRSAEvent editRSAEvent;
    /*
     * @params value list rsa activity
     *  */
    private SimpleListRSAEvent listRSAEvent;
    private SimpleListRSAAdapter listRSAAdapter;

    public SimpleListEditRSAPresenter(Context context) {
        this.context = context;
        init();
    }

    public void onSimpleEditRSAPresenter(SimpleEditRSAEvent editRSAEvent) {
        this.editRSAEvent = editRSAEvent;
    }

    private void init() {
        cache = Cache.getInstance(context);
    }

    public void getList() throws JSONException {
        String base = cache.getString(LIST_RSA);
        new AsyncGetList(base).execute();

    }
    class AsyncGetList extends AsyncTask<Void,JSONArray,JSONArray>{
        String base;
        ProgressDialog progressDialog;
        public AsyncGetList(String base) {
            this.base = base;
            progressDialog = new ProgressDialog(context);
            progressDialog.show();
        }

        @Override
        protected JSONArray doInBackground(Void... voids) {
            JSONArray jsonArray = new JSONArray();
            if (!base.equals("")) {
                String[] b = base.split("<<->>");
                StringBuilder sb = new StringBuilder();
                for (String a : b) {
//                Log.e("20202020",Base64Controller.DECODE(a).length+"");
                    if (!a.equals("")) {
                        try {
                            sb.append(
                                    CipherRsa.CiperDecrypted(
                                            GetKeyRSA.privateKey(context),
                                            Base64Controller.DECODE(a)
                                    )
                            );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    jsonArray = new JSONArray(sb.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return jsonArray;

            } else {

            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            SimpleListEditRSAPresenter.this.jsonArray = jsonArray;
            if (jsonArray != null && jsonArray.length() > 0){
                if (editRSAEvent != null) {
                    editRSAEvent.onArrayRSA(jsonArray);
                }
                if (listRSAEvent != null) {

                }
                if (listRSAAdapter != null){
                    try {
                        listRSAAdapter.addList(listRSA());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            progressDialog.dismiss();
        }
    }
    public void pushNewArray(JSONArray jsonArray, String text) throws JSONException {
        new AsyncPushNewArray(jsonArray,text).execute();
    }
    class AsyncPushNewArray extends AsyncTask<Void,JSONArray,JSONArray> {
        JSONArray jsonArray;
        String text;

        public AsyncPushNewArray(JSONArray jsonArray, String text) {
            this.jsonArray = jsonArray;
            this.text = text;
        }

        @Override
        protected JSONArray doInBackground(Void... voids) {
            if (!text.equals("")) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("val", text);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
                StringBuilder sb = new StringBuilder(jsonArray.toString());
                StringBuilder baseList = new StringBuilder();
                int size = 1;
                if (sb.length() > 1) {
                    for (int start = 0; start < sb.length(); start += size) {
//                    Log.e("20200202",sb.substring(start, Math.min(sb.length(), start + size)));
                        baseList.append(
                                onBase64Encrypter(
                                        sb.substring(start, Math.min(sb.length(), start + size))
                                )
                        );
                        baseList.append("<<->>");
                    }
                } else {
                    baseList.append(
                            onBase64Encrypter(
                                    sb.toString()
                            )
                    );baseList.append("<<->>");
                }
                return jsonArray;

            } else {

            }
            return jsonArray;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            if (jsonArray != null && jsonArray.length() > 0){
                Log.e("2020020202", jsonArray.toString());
                cache.putString(LIST_RSA, jsonArray.toString());
                editRSAEvent.onPushRSA("Success");
            }else {
                editRSAEvent.onEmpty("Empty");
            }
        }
    }

    /*
     * @params value val
     * @function encode cipher encrypted by public key to base64
     * @return base
     * */
    private String onBase64Encrypter(String val) {
        String base = null;
        try {
            base = Base64Controller.ENCODE(
                    CipherRsa.CiperEncrypted(GetKeyRSA.publicKey(context), val)
            );
//            base = new String(CipherRsa.CiperEncrypted(GetKeyRSA.publicKey(context),val));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base;
    }

    public void onSimpleListRSAPresenterEvent(SimpleListRSAEvent listRSAEvent) {
        this.listRSAEvent = listRSAEvent;
    }

    public List<Object> listRSA() throws JSONException {
        List<Object> list = new ArrayList<>();
        if (jsonArray != null && jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list.add(new SimpleRSAModel(jsonObject.getString("val")));
            }
        }
        return list;
    }

    public void onSimpleListAdapter() throws JSONException {
        listRSAAdapter = new SimpleListRSAAdapter(context);
        listRSAAdapter.addList(listRSA());
    }

    public void setupAdapterList(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(listRSAAdapter);
    }
}
