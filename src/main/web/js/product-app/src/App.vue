<!-- App.vue -->

<template>
  <div>
    <router-view :products="products" :addProduct="addProduct" :updateProduct="updateProduct" />
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      products: [],
    };
  },
  created() {
    this.fetchProducts();
  },
  methods: {
    fetchProducts() {
      const url = 'http://localhost:8081/product-app/api/products';
      axios.get(url).then((response) => {
        this.products = response.data;
      });
    },
    addProduct(product) {
      // API call to add a product
      axios.post('http://localhost:8081/product-app/api/products', product).then(() => {
        this.fetchProducts();
        this.$router.push({ name: 'home' });
      });
    },
    updateProduct(product) {
      // API call to update a product
      axios.put(`http://localhost:8081/product-app/api/products/${product.id}`, product).then(() => {
        this.fetchProducts();
        this.$router.push({ name: 'home' });
      });
    },
  },
};
</script>
