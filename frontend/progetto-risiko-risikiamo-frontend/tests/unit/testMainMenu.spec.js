import { mount } from "@vue/test-utils";
import { createRouter, createWebHistory } from "vue-router";

import HomeComponent from "../../src/components/HomeComponent.vue";

describe("test main menu", () => {
  it("test main menu", async () => {
    const router = createRouter({
      history: createWebHistory(),
      routes: [
        {
          path: "/game",
          name: "home",
          component: HomeComponent,
        },
      ],
    });
    router.push("/game?id=1&name=home");
    await router.isReady();
    const wrapper = mount(HomeComponent, {
      global: {
        plugins: [router],
      },
    });

    expect(wrapper.html()).toContain("inserisci");
  });
});
