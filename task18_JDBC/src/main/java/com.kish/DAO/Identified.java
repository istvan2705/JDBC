package com.kish.DAO;

import java.io.Serializable;

public interface Identified<ID extends Serializable> {
    ID getId();
}


