package com.maaz.SpringBoot_EcomerceApp.model;

public class SuccessResponse {
    private boolean success;
    private boolean productDeleted;

    // ✅ Constructor
    public SuccessResponse(boolean success, boolean productDeleted) {
        this.success = success;
        this.productDeleted = productDeleted;
    }

    // ✅ Getters & Setters (Needed for JSON serialization)
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isProductDeleted() {
        return productDeleted;
    }

    public void setProductDeleted(boolean productDeleted) {
        this.productDeleted = productDeleted;
    }
}