package com.iokays.homepage.repository.plus;

import java.io.Serializable;
import java.util.Map;

public interface HomePageRepositoryPlus {
    public abstract Integer deleteById(Serializable id);

    public abstract Integer update(Serializable id, Map<String, Object> map) throws Exception;

}
