import { createRouter, createWebHistory } from "vue-router";
import HomeVue from "./components/HomeComponent.vue";
import MainVue from "./components/MainMenu.vue";

const routes = [
  {
    name: "main menu",
    component: MainVue,
    path: "/",
    props: true,
  },
  {
    name: "game",
    component: HomeVue,
    path: "/game",
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
