import { createRouter, createWebHistory } from 'vue-router'
import HomeVue from './components/HomeComponent.vue'

const routes = [
    {
        name: "home",
        component: HomeVue,
        path: "/"
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;