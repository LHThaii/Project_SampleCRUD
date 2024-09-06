package com.example.project_samplecrud.Dto.Respone;


public class ResponeObjectDTO {
    private String status;
    private String message;
    private Object object;

    public ResponeObjectDTO() {
    }

    // Constructor với thông báo
    public ResponeObjectDTO(String message) {
        this.message = message;
    }

    public ResponeObjectDTO(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    // Getter và Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
