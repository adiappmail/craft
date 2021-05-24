package com.intuit.craft.config;

import com.intuit.craft.Products;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class ProductsRegistry {

        Map<Products, String> map = new HashMap<>();
}

