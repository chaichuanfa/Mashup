package com.felix.model.base.juhe;

import java.util.List;

/**
 * Created by chaichuanfa on 2019/1/17
 */
public class JuHeApiResult<D> {

    private int stat;

    private List<D> data;

    public JuHeApiResult(int stat, List<D> data) {
        this.stat = stat;
        this.data = data;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public List<D> getData() {
        return data;
    }

    public void setData(List<D> data) {
        this.data = data;
    }
}
