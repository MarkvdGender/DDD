package nl.hu.asd.product.domain.product;

public interface ProductRepository {
    public Product findById(ProductId pID);

    public void save(Product product);
}
