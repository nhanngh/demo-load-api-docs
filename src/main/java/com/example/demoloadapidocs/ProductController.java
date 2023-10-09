package com.example.demoloadapidocs;

import com.example.demoloadapidocs.model.CreateProductTypeRequest;
import com.example.demoloadapidocs.model.Document;
import com.example.demoloadapidocs.model.Product;
import com.example.demoloadapidocs.model.ProductTypesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductApi {
  @Override
  public ResponseEntity<Void> _createProductType(CreateProductTypeRequest createProductTypeRequest) {
    return null;
  }

  @Override
  public ResponseEntity<List<Document>> _getDocumentOfProduct(Long productId) {
    return null;
  }

  @Override
  public ResponseEntity<Product> _getProductByProductId(Long productId) {
    return ResponseEntity.ok(new Product().id(1).description("apple"));
  }

  @Override
  public ResponseEntity<ProductTypesResponse> _getProductTypes(String name, Integer page, Integer size, String sort) {
    return null;
  }

  @Override
  public ResponseEntity<Void> _updateProductType(String productTypeId, CreateProductTypeRequest createProductTypeRequest) {
    return null;
  }
}
