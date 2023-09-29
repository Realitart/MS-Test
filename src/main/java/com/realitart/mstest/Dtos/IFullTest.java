package com.realitart.mstest.Dtos;

import java.util.Date;

public interface IFullTest {
    public Date getCreated();  // Debe coincidir con el tipo de dato en la consulta SQL
    public Integer getScore();  // Debe coincidir con el tipo de dato en la consulta SQL
    public String getName();
}


