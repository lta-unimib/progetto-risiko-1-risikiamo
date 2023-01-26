import { mount } from "@vue/test-utils";

import MainMenu from "../../src/components/MainMenu.vue";
import { createRouter, createWebHistory } from "vue-router";

describe("test main menu", () => {
  let wrapper;

  beforeAll(async () => {
    const router = createRouter({
      history: createWebHistory(),
      routes: [
        {
          path: "/",
          name: "home",
          component: MainMenu,
        },
      ],
    });
    router.push("/");
    await router.isReady();

    wrapper = mount(MainMenu, {
      global: {
        plugins: [router],
      },
    });

    wrapper.setData({
      playerName: "player1",
      selectedColor: "red",
      id: "123",
    });
  });

  it("test main menu", () => {
    expect(wrapper.vm).toBeTruthy();
  });
  it("sets playerName and selectedColor when readString is called with valid input", () => {
    const value = "player1";
    const color = "red";
    wrapper.vm.readString(value, color);
    expect(wrapper.vm.playerName).toBe(value);
    expect(wrapper.vm.selectedColor).toBe(color);
  });
  it('alerts "Inserisci un nome valido" when readString is called with empty input', () => {
    const value = "";
    const color = "red";
    global.alert = jest.fn();
    wrapper.vm.readString(value, color);
    expect(global.alert).toHaveBeenCalledWith("Inserisci un nome valido");
  });

  it("creates a player object when readString is called with valid input", () => {
    const value = "player1";
    const color = "red";
    wrapper.vm.readString(value, color);
    expect(wrapper.vm.player).toEqual({ name: value, color });
  });
  it('returns "Territorio non trovato" if error status is 404', () => {
    const error = { response: { status: 404 } };
    expect(wrapper.vm.returnError(error)).toBe("Territorio non trovato");
  });

  it("returns error data if error status is not 404", () => {
    const error = { response: { status: 400, data: "Bad Request" } };
    expect(wrapper.vm.returnError(error)).toBe("Bad Request");
  });

  it("opens a new window with the correct parameters", async () => {
    const message = "Hello World";
    window.open = jest.fn();
    window.open.mockReturnValue({
      document: {
        body: {
          innerHTML: "",
        },
      },
    });

    await wrapper.vm.showMessage(message);
    expect(window.open).toHaveBeenCalledWith(
      "",
      "Message",
      "height=80,width=300"
    );
    expect(window.open().document.body.innerHTML).toBe(message);
  });
});
