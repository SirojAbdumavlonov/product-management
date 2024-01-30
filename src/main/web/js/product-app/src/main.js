import { createApp } from 'vue';
import App from './App.vue';
import ProductList from './components/ProductList.vue';
import ProductForm from './components/ProductForm.vue';
import NotFoundView from "@/components/NotFoundView.vue";
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    { path: '/', component: ProductList, name: 'home' },
    { path: '/add', component: ProductForm, name: 'add' },
    { path: '/edit/:id', component: ProductForm, name: 'edit' },
    { path: '/:catchAll(.*)', component: NotFoundView, name: 'not-found' },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);

app.use(router);

app.mount('#app');
