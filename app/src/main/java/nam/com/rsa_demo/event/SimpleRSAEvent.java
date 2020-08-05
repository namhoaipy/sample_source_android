package nam.com.rsa_demo.event;

public interface SimpleRSAEvent {
    void onSimpleRSADecrypterEvent(String text);
    void onSimpleRSAEncrypterEvent(String text);
    void onEmpty(String text);
}
