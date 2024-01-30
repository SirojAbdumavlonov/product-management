<!--<template>-->
<!--  <div>-->
<!--    <h2>{{ editing ? 'Edit Product' : 'Add Product' }}</h2>-->
<!--    <form @submit.prevent="submitForm">-->
<!--      <label for="name">Name:</label>-->
<!--      <input v-model="formData.name" type="text" id="name" required />-->
<!--      <label for="quantity">Quantity:</label>-->
<!--      <input v-model="formData.quantity" type="text" id="quantity" required />-->
<!--      <label for="price">Price:</label>-->
<!--      <input v-model="formData.price" type="text" id="price" required />-->
<!--      <label for="status">Status:</label>-->
<!--      <input v-model="formData.status" type="text" id="status" required />-->
<!--      <label for="type">Type:</label>-->
<!--      <input v-model="formData.type" type="text" id="type" required />-->
<!--      <button type="submit">{{ editing ? 'Edit' : 'Add' }} Product</button>-->
<!--    </form>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  props: {-->
<!--    editing: Boolean,-->
<!--    productToEdit: Object,-->
<!--    addProduct: Function,-->
<!--    updateProduct: Function,-->
<!--  },-->
<!--  data() {-->
<!--    return {-->
<!--      formData: {-->
<!--        name: '',-->
<!--        status: '',-->
<!--        type: '',-->
<!--        quantity: 0,-->
<!--        price: 0-->
<!--      },-->
<!--    };-->
<!--  },-->
<!--  watch: {-->
<!--    productToEdit: {-->
<!--      handler(newVal) {-->
<!--        this.formData = { ...newVal };-->
<!--      },-->
<!--      immediate: true,-->
<!--    },-->
<!--  },-->
<!--  methods: {-->
<!--    submitForm() {-->
<!--      if (this.editing) {-->
<!--        this.updateProduct(this.formData);-->
<!--      } else {-->
<!--        this.addProduct(this.formData);-->
<!--      }-->
<!--      this.resetForm();-->
<!--    },-->
<!--    resetForm() {-->
<!--      this.formData = { name: '', status: '', type: '', quantity: 0, price: 0 };-->
<!--    },-->
<!--  },-->
<!--};-->
<!--</script>-->

<!-- ProductForm.vue -->

<template>
  <div>
    <h2>{{ editing ? 'Edit Product' : 'Add Product' }}</h2>
    <form @submit.prevent="submitForm">
      <label for="name">Name:</label>
      <input v-model="formData.name" type="text" id="name" required />
      <label for="quantity">Quantity:</label>
      <input v-model="formData.quantity" type="text" id="quantity" required />
      <label for="price">Price:</label>
      <input v-model="formData.price" type="text" id="price" required />
      <label for="status">Status:</label>
      <input v-model="formData.status" type="text" id="status" required />
      <label for="type">Type:</label>
      <input v-model="formData.type" type="text" id="type" required />
      <button type="submit">{{ editing ? 'Edit' : 'Add' }} Product</button>
      <button type="submit">{{ editing ? 'Edit' : 'Add' }} Product</button>
    </form>
  </div>
</template>

<script>
export default {
  props: {
    editing: Boolean,
    productToEdit: Object,
    addProduct: Function,
    updateProduct: Function,
  },
  data() {
    return {
      formData: {
        name: '',
        status: '',
        type: '',
        quantity: 0,
        price: 0
      },
    };
  },
  watch: {
    productToEdit: {
      handler(newVal) {
        this.formData = { ...newVal };
      },
      immediate: true,
    },
  },
  methods: {
    submitForm() {
      if (this.editing) {
        this.updateProduct(this.formData);
      } else {
        this.addProduct(this.formData);
      }
      this.resetForm();
      this.$router.push({ name: 'home' }); // Redirect to the home view after form submission
    },
    resetForm() {
      this.formData = { name: '', status: '', type: '', quantity: 0, price: 0 };
    },
  },
};
</script>
