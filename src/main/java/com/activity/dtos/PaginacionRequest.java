package com.activity.dtos;

public class PaginacionRequest {

    private int page = 0; // Valor por defecto
    private int size = 2; // Valor por defecto

    // Getters y Setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
