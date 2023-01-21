import { createRouter, createWebHistory } from "vue-router";
import HomeVue from "./components/HomeComponent.vue";
import NestedRoute from "./components/NestedRoute.vue";
import MainVue from "./components/MainMenu.vue";
import PlayersVue from "./components/PlayersComponent.vue";
import CardsVue from "./components/CardComponent.vue";
import PlayerData from "./components/PlayerData.vue";

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
  },
  {
    name: "nested-route",
    component: NestedRoute,
    path: "/nested-route",
  },
  {
    name: "players",
    component: PlayersVue,
    path: "/players",
    props: true,
  },
  {
    name: "cards",
    component: CardsVue,
    path: "/cards",
  },
  {
    name: "users",
    component: PlayerData,
    path: "/users",
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
