import { createRouter, createWebHistory } from 'vue-router'
import HomeVue from './components/HomeComponent.vue'
import NestedRoute from "./components/NestedRoute.vue"

const routes = [
    {
        name: "home",
        component: HomeVue,
        path: "/"
    },
    {
        name: "nested-route",
        component: NestedRoute,
        path: "/nested-route"
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;