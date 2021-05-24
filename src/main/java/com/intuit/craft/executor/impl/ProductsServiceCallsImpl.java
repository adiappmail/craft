package com.intuit.craft.executor.impl;

import com.intuit.craft.Products;
import com.intuit.craft.config.ProductsRegistry;
import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.dto.ProductResponseDto;
import com.intuit.craft.executor.BaseExecutor;
import com.intuit.craft.executor.ProductsServiceCalls;
import com.intuit.craft.external.impl.ProductRestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class ProductsServiceCallsImpl implements ProductsServiceCalls, BaseExecutor {


    ProductsRegistry productsRegistry;
    ProductRestServiceImpl productRestService;

    @Autowired
    public ProductsServiceCallsImpl(ProductsRegistry productsRegistry, ProductRestServiceImpl productRestService) {
        this.productsRegistry = productsRegistry;
        this.productRestService = productRestService;
    }

    @Override
    public ExecutorService taskExecutor(int poolSize) {
        return Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public List<ProductResponseDto> executeCallsToAllProducts(BusinessProfileDto businessProfile) {

        ExecutorService executorService = taskExecutor(productsRegistry.getMap().size());
        List<ProductResponseDto> responseDtos = new ArrayList<>();
        try {
            List<Callable<ProductResponseDto>> callableList = new ArrayList<>();
            for (Map.Entry<Products, String> kv : productsRegistry.getMap().entrySet()) {
                callableList.add(() ->
                        productRestService.callProductService(kv.getValue(),
                                businessProfile, kv.getKey().name()));
            }
            List<Future<ProductResponseDto>> futures = executorService.invokeAll(callableList);
            Thread.sleep(200);
            for (Future<ProductResponseDto> f:  futures) {
                responseDtos.add(f.get());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return responseDtos;
    }
}
