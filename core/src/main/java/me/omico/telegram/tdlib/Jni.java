package me.omico.telegram.tdlib;

public class Jni {

    static {
        System.loadLibrary("TdJNI");
    }

    public static native void init();

    public static native int createNativeClient();
}
