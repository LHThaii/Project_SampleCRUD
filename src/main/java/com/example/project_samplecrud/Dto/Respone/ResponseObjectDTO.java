package com.example.project_samplecrud.Dto.Respone;


public class ResponseObjectDTO {
    private String status;
    private String message;
    private Object object;

    public ResponseObjectDTO() {
    }
    public ResponseObjectDTO(String message) {
        this.message = message;
    }

    public ResponseObjectDTO(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
