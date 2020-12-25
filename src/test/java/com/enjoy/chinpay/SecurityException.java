// 
// Decompiled by Procyon v0.5.36
// 

package com.enjoy.chinpay;

public class SecurityException extends Exception
{
    private static final long serialVersionUID = 1L;
    private String errCode;
    
    public SecurityException() {
    }
    
    public SecurityException(final String errCode) {
        this.errCode = errCode;
    }
    
    public String getErrCode() {
        return this.errCode;
    }
    
    public void setErrCode(final String errCode) {
        this.errCode = errCode;
    }
}
