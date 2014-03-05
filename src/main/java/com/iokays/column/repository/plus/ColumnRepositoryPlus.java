package com.iokays.column.repository.plus;

import java.io.Serializable;
import java.util.Map;

public interface ColumnRepositoryPlus {
    public abstract Integer deleteById(Serializable id);

    public abstract Integer update(Serializable id, Map<String, Object> map) throws Exception;
}
