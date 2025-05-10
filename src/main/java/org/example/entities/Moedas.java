package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Currency;
import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Moedas {
    private String base_code;
    private String target_code;
    private Double conversion_rate;
    private Double conversion_result;

    // Getters e Setters
    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public Double getConversion_rate() {
        return conversion_rate;
    }

    public void setConversion_rate(Double conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public String getTarget_code() {
        return target_code;
    }

    public void setTarget_code(String target_code) {
        this.target_code = target_code;
    }

    public Double getConversion_result() {
        return conversion_result;
    }

    public void setConversion_result(Double conversion_result) {
        this.conversion_result = conversion_result;
    }
}
