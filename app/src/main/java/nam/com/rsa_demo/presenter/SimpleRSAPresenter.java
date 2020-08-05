package nam.com.rsa_demo.presenter;

import android.annotation.SuppressLint;
import android.content.Context;

import nam.com.rsa_demo.event.SimpleRSAEvent;
import nam.com.rsa_demo.rsa.CipherRsa;
import nam.com.rsa_demo.rsa.GetKeyRSA;

@SuppressLint("NewApi")
public class SimpleRSAPresenter {
    private SimpleRSAEvent simpleRSAEvent;
    private Context context;
    private byte[] bytesRSA;

    public SimpleRSAPresenter(Context context) {
        this.context = context;
    }

    public void onSimplePresenter(SimpleRSAEvent simpleRSAEvent) {
        this.simpleRSAEvent = simpleRSAEvent;
    }

    public void onDecrypter(String text) throws Exception {
        if (!text.equals("")){
            bytesRSA = CipherRsa.CiperEncrypted(GetKeyRSA.publicKey(context), text);
            simpleRSAEvent.onSimpleRSADecrypterEvent(new String(bytesRSA));
        }else {
            simpleRSAEvent.onEmpty("Original is empty");
        }
    }

    public void onEncrypter() throws Exception {
        if (bytesRSA != null && bytesRSA.length > 0) {
            simpleRSAEvent
                    .onSimpleRSAEncrypterEvent(CipherRsa
                            .CiperDecrypted(GetKeyRSA.privateKey(context), bytesRSA));
        }else {
            simpleRSAEvent.onEmpty("Encrypter fails");
        }
    }
}
