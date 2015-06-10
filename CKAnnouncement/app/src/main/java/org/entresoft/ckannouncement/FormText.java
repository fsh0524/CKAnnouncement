package org.entresoft.ckannouncement;

/**
 * Created by fsh0524 on 15/6/10.
 */
public class FormText {

    private String mName;
    private String mValue;

    public FormText(String mName, String mValue) {
        this.mName = mName;
        this.mValue = mValue;
    }

    public String getName() {
        return mName;
    }

    public String getValue() {
        return mValue;
    }
}
