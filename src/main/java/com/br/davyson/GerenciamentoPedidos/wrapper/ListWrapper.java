package com.br.davyson.GerenciamentoPedidos.wrapper;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListWrapper<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<T> content = new ArrayList<>();

    public ListWrapper() {
    }

    public ListWrapper(List<T> content) {

        this.content = (content != null) ? new ArrayList<>(content) : new ArrayList<>();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = (content != null) ? new ArrayList<>(content) : new ArrayList<>();
    }
}
