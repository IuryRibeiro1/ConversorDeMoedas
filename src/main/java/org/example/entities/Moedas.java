package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Moedas {
    private String base_code;
    private String target_code;
    private float conversion_rate;
    private float conversion_result;
    private List<List<String>> supported_codes;

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public float getConversion_rate() {
        return conversion_rate;
    }

    public void setConversion_rate(float conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public String getTarget_code() {
        return target_code;
    }

    public void setTarget_code(String target_code) {
        this.target_code = target_code;
    }

    public float getConversion_result() {
        return conversion_result;
    }

    public void setConversion_result(float conversion_result) {
        this.conversion_result = conversion_result;
    }

    public List<List<String>> getSupported_codes() {
        return supported_codes;
    }

    public void setSupported_codes(List<List<String>> supported_codes) {
        this.supported_codes = supported_codes;
    }


    @Override
    public String toString() {
        return
                "Moeda: " + base_code +
                ", Moeda convertida: " + target_code +
                ", Taxa de conversão:  " + conversion_rate +
                ", Resultado da conversão: " + conversion_result;
    }
}
