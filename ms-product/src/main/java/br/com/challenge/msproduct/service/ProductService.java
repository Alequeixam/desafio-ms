package br.com.challenge.msproduct.service;

import br.com.challenge.msproduct.exception.ResourceNotFoundException;
import br.com.challenge.msproduct.repository.ProductRepository;
import br.com.challenge.msproduct.entity.Product;
import br.com.challenge.msproduct.payload.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ModelMapper mapper;

    public ProductService(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;

        this.mapper = mapper;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);

        Product createdProduct = productRepository.save(product);

        return mapToDTO(createdProduct);
    }

    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> mapToDTO(product)).toList();
    }

    public ProductDTO findProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", productId));
        return mapToDTO(product);
    }

    public List<ProductDTO> findAllProductsPaginated(int page, int linesPerPage, String orderBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending()
                : Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(page, linesPerPage, sort);

        Page<Product> products = productRepository.findAll(pageable);

        List<Product> productList = products.getContent();

        return productList.stream().map(product -> mapToDTO(product)).toList();
    }

    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", productId));

        product.setId(productId);
        product.setDate(productDTO.getDate());
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setImgUrl(productDTO.getImgUrl());
        product.setPrice(productDTO.getPrice());
        product.setCategories(productDTO.getCategories());
        Product updatedProduct = productRepository.save(product);

        return mapToDTO(updatedProduct);
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);
    }




    private ProductDTO mapToDTO(Product product) {
        return mapper.map(product, ProductDTO.class);
    }
    private Product mapToEntity(ProductDTO productDTO) {
        return mapper.map(productDTO, Product.class);
    }
}
