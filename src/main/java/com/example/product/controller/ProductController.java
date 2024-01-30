package com.example.product.controller;


import com.example.product.entity.Product;
import com.example.product.entity.ProductStatus;
import com.example.product.entity.ProductType;
import com.example.product.model.ProductRequest;
import com.example.product.util.RandomStringGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("products")
public class ProductController {


    private final EntityManager entityManager = Persistence
            .createEntityManagerFactory("ProductPU")
            .createEntityManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts(@QueryParam("type") ProductType productType,
                                     @QueryParam("status") ProductStatus productStatus,
                                     @QueryParam("id") String id){

        List<Product> productsList =
                getProductsByTheseParameters(
                        productType, productStatus, id
                );

        return productsList;
    }

    public List<Product> getProductsByTheseParameters(
            ProductType productType,
            ProductStatus productStatus,
            String id) {

        if(id != null){
            return List.of(findById(id));
        }
        else if(productType != null && productStatus != null){
            return findByStatusAndType(productStatus, productType);
        }
        else if(productType != null){
            return findByType(productType);
        }
        else if(productStatus != null){
            return findByStatus(productStatus);
        }
        return getAllProducts();
    }

    public List<Product> findByStatusAndType(
            ProductStatus productStatus,
            ProductType productType){

        String query = "select pr from Product pr where pr.type = :type and pr.status = :status";

        TypedQuery<Product> productTypedQuery = entityManager.createQuery(query, Product.class);
        productTypedQuery.setParameter("type", productType);
        productTypedQuery.setParameter("status", productStatus);

        return productTypedQuery.getResultList();
    }

    public List<Product> findByStatus(ProductStatus status){
        String query = "select pr from Product pr where pr.status = :status";

        TypedQuery<Product> productTypedQuery = entityManager.createQuery(query, Product.class);
        productTypedQuery.setParameter("status", status);

        return productTypedQuery.getResultList();
    }

    public List<Product> findByType(ProductType type){
        String query = "select pr from Product pr where pr.type = :type";

        TypedQuery<Product> productTypedQuery = entityManager.createQuery(query, Product.class);
        productTypedQuery.setParameter("type", type);

        return productTypedQuery.getResultList();
    }

    public List<Product> getAllProducts(){
        String query = "select pr from Product pr";

        TypedQuery<Product> productTypedQuery = entityManager.createQuery(query, Product.class);

        return productTypedQuery.getResultList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductRequest productRequest){

        Product product = Product.builder()
                .id(RandomStringGenerator.generateRandomString())
                .status(productRequest.status())
                .type(productRequest.type())
                .price(productRequest.price())
                .quantity(productRequest.quantity())
                .name(productRequest.name())
                .build();

        save(product);

        return Response.ok().entity("Added successfully!").build();
    }
    public void save(Product product){

        entityManager.getTransaction().begin();

        entityManager.persist(product);
        entityManager.getTransaction().commit();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)@Produces(MediaType.APPLICATION_JSON)
    public Response updateProductDetails(@PathParam("id") String id, ProductRequest productRequest){

        Product product = findById(id);

        ProductStatus status = productRequest.status();

        if (productRequest.price() < product.getPrice()) status = ProductStatus.ON_SALE;

        if (productRequest.quantity() == 0) status = ProductStatus.OUT_OF_STOCK;

        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setType(productRequest.type());
        product.setStatus(status);
        product.setQuantity(productRequest.quantity());

        saveUpdatedProductDetails(product);

        return Response.ok().entity("Updated successfully!").build();
    }
    public void saveUpdatedProductDetails(Product product){

        entityManager.getTransaction().begin();

        entityManager.merge(product);

        entityManager.getTransaction().commit();

    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") String id){


        Product product = findById(id);

        delete(product);

        return Response.ok().entity("Deleted successfully!").build();
    }
    public void delete(Product product){

        entityManager.getTransaction().begin();

        entityManager.remove(product);

        entityManager.getTransaction().commit();
    }

    public Product findById(String id){

        Product product = entityManager.find(Product.class, id);

        if (product == null){
            throw new BadRequestException("Id is not found");
        }
        return product;
    }

}