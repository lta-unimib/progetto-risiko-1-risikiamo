import { mount } from "@vue/test-utils";
import { createRouter, createWebHistory } from "vue-router";

import MainMenu from "../../src/components/HomeComponent.vue";
import App from "../../src/App.vue";

describe("test main menu", () => {
  it("test main menu", async () => {
    const router = createRouter({
      history: createWebHistory(),
      routes: [
        {
          path: "/game",
          name: "home",
          component: MainMenu,
        },
      ],
    });
    router.push("/game?id=1&name=home");
    await router.isReady();
    const wrapper = mount(MainMenu, {
      global: {
        plugins: [router],
      },
    });

    expect(wrapper.html()).toContain("inserisci");
  });
});
