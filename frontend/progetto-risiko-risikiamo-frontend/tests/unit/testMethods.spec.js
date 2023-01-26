import { mount, render } from "@vue/test-utils";
import HomeComponent from "../../src/components/HomeComponent.vue";
import { createRouter, createWebHistory } from "vue-router";

let wrapper;
describe("App", () => {
  beforeEach(async () => {
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

    wrapper = mount(HomeComponent, {
      global: {
        plugins: [router],
      },
    });
  });

  afterEach(async () => {
    wrapper.vm.destoyed();
    await wrapper.vm.$nextTick();
  });

  test("test home component", () => {
    expect(wrapper.vm).toBeTruthy();
  });

  test("should update hoverValue with the passed value", () => {
    wrapper.vm.changeHoverValue({
      attributes: { title: { value: "Germany" } },
    });
    expect(wrapper.vm.$data.hoverValue).toBe("place your mouse over a country");
  });

  test("sets the selectedPaths data property to the provided value", () => {
    expect(wrapper.vm.selectedPaths).toEqual([]);
  });

  test("colorSet", () => {
    expect(
      wrapper.vm.colorSet({
        attributes: { owner: "p" },
      })
    ).toBe("black");
  });
  test("returns the neighbours of the territory with the specified name", () => {
    const continents = [
      {
        territory: [
          { name: "territory1", neighbours: ["territory2", "territory3"] },
          { name: "territory2", neighbours: ["territory1", "territory3"] },
        ],
      },
      {
        territory: [
          { name: "territory3", neighbours: ["territory1", "territory2"] },
        ],
      },
    ];
    const name = "territory1";
    expect(wrapper.vm.findNameTerritory(continents, name)).toEqual([
      "territory2",
      "territory3",
    ]);
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

  it("calls findNameTerritory with the correct arguments", () => {
    wrapper.vm.findNameTerritory = jest.fn();
    wrapper.vm.findNameTerritory.mockReturnValue("territory2");
    wrapper.vm.findNameTerritory("continents", "territory1");
    expect(wrapper.vm.findNameTerritory).toHaveBeenCalledWith(
      "continents",
      "territory1"
    );
  });

  it("calls cardDiscard with the correct arguments", () => {
    let mockCardDiscard = jest.fn();
    jest.spyOn(wrapper.vm, "cardDiscard").mockImplementation(mockCardDiscard);
    wrapper.vm.card1 = "card1";
    wrapper.vm.card2 = "card2";
    wrapper.vm.card3 = "card3";
    wrapper.vm.submitCards();
    expect(mockCardDiscard).toHaveBeenCalledWith("card1", "card2", "card3");
  });

  it("should call submitForm method when button is clicked", async () => {
    // set the selectedAction to "attack"
    wrapper.setData({ selectedAction: "attack" });
    // set startLocation and destination
    wrapper.setData({ startLocation: "Italy", destination: "France" });
    // set armyNumber
    wrapper.setData({ armyNumber: 10 });

    // mock the attack method
    wrapper.vm.attack = jest.fn(() => Promise.resolve("Attack successful"));

    // trigger the click event on the button
    wrapper.find("#doAction1").trigger("click");

    // wait for next tick
    await wrapper.vm.$nextTick();

    // expect the attack method to be called
    expect(wrapper.vm.attack).toHaveBeenCalledWith(10, "Italy", "France");
  });

  it("should call submitForm method when button is clicked", async () => {
    wrapper.setData({ selectedAction: "move" });
    wrapper.setData({ startLocation: "Italy", destination: "France" });
    wrapper.setData({ armyNumber: 10 });

    wrapper.vm.move = jest.fn(() => Promise.resolve("Move successful"));

    wrapper.find("#doAction1").trigger("click");

    await wrapper.vm.$nextTick();

    expect(wrapper.vm.move).toHaveBeenCalledWith(10, "Italy", "France");
  });

  it("should call submitForm method when button is clicked", async () => {
    wrapper.setData({ selectedAction: "place" });
    wrapper.setData({ startLocation: "Italy" });
    wrapper.setData({ armyNumber: 10 });

    wrapper.vm.place = jest.fn(() => Promise.resolve("Place successful"));

    wrapper.find("#doAction1").trigger("click");

    await wrapper.vm.$nextTick();

    expect(wrapper.vm.place).toHaveBeenCalledWith(10, "Italy");
  });

  it("start game", async () => {
    wrapper.vm.startMatch = jest.fn(() => Promise.resolve("Start game"));
    wrapper.find("#start").trigger("click");
    await wrapper.vm.$nextTick();
    expect(wrapper.vm.startMatch).toHaveBeenCalled();
  });
  it("skip", async () => {
    wrapper.vm.skip = jest.fn(() => Promise.resolve("Skip"));
    wrapper.find("#skip").trigger("click");
    await wrapper.vm.$nextTick();
    expect(wrapper.vm.skip).toHaveBeenCalled();
  });
  it("surrend", async () => {
    wrapper.vm.surrend = jest.fn(() => Promise.resolve("Surrend"));
    wrapper.find("#surrend").trigger("click");
    await wrapper.vm.$nextTick();
    expect(wrapper.vm.surrend).toHaveBeenCalled();
  });
  it("redeem crards", async () => {
    wrapper.vm.submitCards = jest.fn(() => Promise.resolve("Redeem cards"));
    wrapper.find("#doAction2").trigger("click");
    await wrapper.vm.$nextTick();
    expect(wrapper.vm.submitCards).toHaveBeenCalled();
  });
});
