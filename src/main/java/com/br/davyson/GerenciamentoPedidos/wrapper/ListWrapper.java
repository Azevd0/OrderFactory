package com.br.davyson.GerenciamentoPedidos.wrapper;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ListWrapper<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<T> content;

    public ListWrapper() {
    }

    public ListWrapper(List<T> content) {
        this.content = content;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
