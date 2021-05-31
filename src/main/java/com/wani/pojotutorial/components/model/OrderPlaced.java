package com.wani.pojotutorial.components.model;

import java.io.Serializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderPlaced implements Serializable {

    private OrderInfo orderInfo;

}
