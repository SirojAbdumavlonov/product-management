<template>
  <div>
    <h2>Product List</h2>
    <div>
      <label>Filter by Type:</label>
      <select v-model="selectedType" @change="filterProducts">
        <option value="">All Types</option>
        <option v-for="type in productTypes" :key="type" :value="type">{{ type }}</option>
      </select>
    </div>
    <div>
      <label>Filter by Status:</label>
      <select v-model="selectedStatus" @change="filterProducts">
        <option value="">All Statuses</option>
        <option v-for="status in productStatuses" :key="status" :value="status">{{ status }}</option>
      </select>
    </div>
    <button @click="addNewProduct">Add Product</button>
    <ul>
      <li v-for="product in filteredProducts" :key="product.id">
        {{ product.name }} - {{ product.status }} - {{ product.type }} - {{ product.quantity }} - {{ product.price }}
        <button @click="editProduct(product)">Edit</button>
        <button @click="deleteProduct(product.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: {
    products: Array,
    editProduct: Function,
    deleteProduct: Function,
    addProduct: Function,
  },
  data() {
    return {
      selectedType: '', // Selected product type for filtering
      selectedStatus: '', // Selected product status for filtering
    };
  },
  computed: {
    // Get unique product types from the products
    productTypes() {
      return [...new Set(this.products.map((product) => product.type))];
    },
    // Get unique product statuses from the products
    productStatuses() {
      return [...new Set(this.products.map((product) => product.status))];
    },
    // Filter products based on selected type and status
    filteredProducts() {
      let filtered = this.products;

      if (this.selectedType) {
        filtered = filtered.filter((product) => product.type === this.selectedType);
      }

      if (this.selectedStatus) {
        filtered = filtered.filter((product) => product.status === this.selectedStatus);
      }

      return filtered;
    },
  },
  methods: {
    addNewProduct() {
      this.addProduct();
    },
    filterProducts() {
      // You can add additional logic here if needed
    },
  },
};
</script>
